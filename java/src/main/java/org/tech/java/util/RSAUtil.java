package org.tech.java.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
  
  
/** 
 * RSA加密解密 
 * 此工具类能使用指定的字符串，每次生成相同的公钥和私钥且在linux和windows密钥也相同；相同的原文和密钥生成的密文相同 
 */  
public class RSAUtil {  
    private static final String ALGORITHM_RSA = "RSA";  
    private static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";  
    private static final int KEY_SIZE = 1024;  
    private static final String PUBLIC_KEY = "RSAPublicKey";  
    private static final String PRIVATE_KEY = "RSAPrivateKey";  
    private static final String TRANSFORMATION = "RSA/None/NoPadding";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
      
    /** 
     * 解密 
     * 用私钥解密，解密字符串，返回字符串 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decryptByPrivateKey(String data, String key) throws Exception {  
        return new String(decryptByPrivateKey(Base64Util.decodeToByte(data), key));  
    }  
      
    /** 
     * 加密 
     * 用公钥加密，加密字符串，返回用base64加密后的字符串 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByPublicKey(String data, String key) throws Exception {  
        return encryptByBytePublicKey(data.getBytes(), key);  
    }

    /** 
     * 加密 
     * 用公钥加密，加密byte数组，返回用base64加密后的字符串 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptByBytePublicKey(byte[] data, String key) throws Exception {  
        return Base64Util.encodeByte(encryptByPublicKey(data, key));  
    }  
      
    /** 
     * 解密 
     * 用私钥解密 
     * @param encryptedData
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String key) throws Exception {
        byte[] keyBytes = Base64Util.decodeToByte(key);//对私钥解密  
        /*取得私钥*/  
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);  
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);  
        /*对数据解密*/  
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());//相同的原文、公钥能生成相同的密文。如果使用Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());//相同的原文、公钥生成的密文不同  
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
//        return cipher.doFinal(data);
    }  
      
    /** 
     * 加密 
     * 用公钥加密 
     * @param data 
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {  
        byte[] keyBytes = Base64Util.decodeToByte(key);//对公钥解密  
        /*取得公钥*/  
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);  
        Key publicKey = keyFactory.generatePublic(x509KeySpec);  
        /*对数据加密*/  
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, new BouncyCastleProvider());//相同的原文、公钥能生成相同的密文。如果使用Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());//相同的原文、公钥生成的密文不同  
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
//        return cipher.doFinal(data);
    }  
      
    /** 
     * 取得私钥 
     * @param keyMap 
     * @return 
     */  
    public static String getPrivateKey(Map<String, Object> keyMap) {  
        Key key = (Key) keyMap.get(PRIVATE_KEY);  
        return Base64Util.encodeByte(key.getEncoded());  
    }  
      
    /** 
     * 取得公钥 
     * @param keyMap 
     * @return 
     */  
    public static String getPublicKey(Map<String, Object> keyMap) {  
        Key key = (Key) keyMap.get(PUBLIC_KEY);  
        return Base64Util.encodeByte(key.getEncoded());  
    }  
      
    /** 
     * 初始化公钥和私钥 
     * @param seed 
     * @return 
     * @throws Exception 
     */  
    public static Map<String, Object> initKey(String seed) throws Exception {  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);  
        SecureRandom random = SecureRandom.getInstance(ALGORITHM_SHA1PRNG);//如果使用SecureRandom random = new SecureRandom();//windows和linux默认不同，导致两个平台生成的公钥和私钥不同  
        random.setSeed(seed.getBytes());//使用种子则生成相同的公钥和私钥  
        keyPairGen.initialize(KEY_SIZE, random);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();//公钥  
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();//私钥  
        
        Map<String, Object> keyMap = new HashMap<String, Object>(2);  
        
        keyMap.put(PUBLIC_KEY, publicKey);  
        keyMap.put(PRIVATE_KEY, privateKey);  
        return keyMap;  
    }  
      
    /** 
     * 使用示例 
     * @param args 
     * @throws Exception 
     */  
    public static void main(String[] args) throws Exception {  
        String source = "test 1521527311000";//原文
        String seed = "~!@#$%^&*()_+qwertyuiop[]asdfghjkl;'zxcvbnm,.";//种子
        
        System.out.println("原文：\n" + source);  
        Map<String, Object> keyMap = RSAUtil.initKey(seed);//初始化密钥
        String publicKey = RSAUtil.getPublicKey(keyMap);//公钥
        String privateKey = RSAUtil.getPrivateKey(keyMap);//私钥
        System.out.println("公钥：\n" + publicKey);
        System.out.println("私钥：\n" + privateKey);

//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJrWQMuitTvEMxpEE6P+nmGtfRdRAkl7HzRV8WUsZET6rx7i7AnvmS6MKXuNbDxFv1pt5MgAzBUbjiqMEj8WVaUbl+IDuVqgkxhrvj/7PeQELDJtQYlVBPxok7Z+goKME/cfHklVksEnxWVgcdKl/HS0D0qrkUVjBdX8a1F7Vk7QIDAQAB";
//        String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMmtZAy6K1O8QzGkQTo/6eYa19F1ECSXsfNFXxZSxkRPqvHuLsCe+ZLowpe41sPEW/Wm3kyADMFRuOKowSPxZVpRuX4gO5WqCTGGu+P/s95AQsMm1BiVUE/GiTtn6CgowT9x8eSVWSwSfFZWBx0qX8dLQPSquRRWMF1fxrUXtWTtAgMBAAECgYEArJ8T9uzWrwqCPRhJmHEiZAlv1uGrGpcZAo/LLtFU0R2bxQ5IyCiKmFtQTAszEEefKqlUtwgXYtTvOlqVTo2NYHdkZ868s6HrdI2XraG8cvRouXDNeyH+1u6wHe8NKPYuBJaSxOyG/iUQTBeoiXZPZseor6M1mXInHRhkUXF/NQECQQDoj7qf9F9Q00Xlz3zbkGkwHwEbGGCyOsFMizHS6Dn2dVu1yatCH2liFhAZepIKQEHcaNb4gdXIOjcg+/ew0tBBAkEA3gDTlTbLhyqjB33ChcTiqknt6gWMx+YA1eZzD87gFYEVwT53DoY2fzXHqIcLLby7DlfJ0OoloiTnOkNMrNRprQJBAL3GzBxLZpymS5vFPbZOmgfSxSw/MGlA2QyFahsZtnMQQc6Oy1oiF/Ua2/hutU34McaTJxmTmcjS+LnJ484/ugECQCs7CfiylOubV+fri2e1MVZr0xKRRhrfJSaCG8HmTSii3HOnEFQZup8G//xHX9vmNp7SZZBzKCTs/sIPvOyi6HkCQQC0GZpezY0XhqoqKJ+cSQ2JT2tZSMKnmfWyuZTn/JpYjgWBriDPLXy8BaA1Z9oP0E2ttsgfeugStihJnFYdz0Pe";
        String encodedStr = RSAUtil.encryptByPublicKey(source, publicKey);//加密  
        System.out.println("密文：\n" + encodedStr);  
        String decodedStr = RSAUtil.decryptByPrivateKey(encodedStr, privateKey);//解密  
        System.out.println("解密后的结果：\n" + decodedStr);  
    }  
}  
