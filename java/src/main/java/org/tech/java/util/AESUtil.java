package org.tech.java.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密解密工具
 */
public class AESUtil {
	/**
	 * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
	 */
	private static String key = "lo2iecf3vbgjkl9q";
	private static String ivParameter = "adfertuq148mbet0";

	/**
	 * 加密
	 * @param sSrc
	 * @return
	 * @throws Exception String   
	 */
	public static String encrypt(String sSrc) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
//		String result = Base64Utils.encodeToString(encrypted);
		String result = Base64Util.encodeByte(encrypted);
		return result;

	}

	/**
	 * 解密
	 * @param sSrc
	 * @return
	 * @throws Exception String   
	 */
	public static String decrypt(String sSrc) throws Exception {
		byte[] raw = key.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		// 先用base64解密
//		byte[] encrypted1 = Base64Utils.decodeFromString(sSrc);
		byte[] encrypted1 = Base64Util.decodeToByte(sSrc);
		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original, "utf-8");
		return originalString;
	}

	public static void main(String[] args) throws Exception {
		// 需要加密的字串
		String cSrc = "http://baike.xsoftlab.net/view/1066.html$$true";
		System.out.println(cSrc + "  长度为" + cSrc.length());
		// 加密
		long lStart = System.currentTimeMillis();
		String enString = AESUtil.encrypt(cSrc);
		System.out.println("加密后的字串是：" + enString + "长度为" + enString.length());

		long lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("加密耗时：" + lUseTime + "毫秒");
		// 解密
		lStart = System.currentTimeMillis();
		String DeString = AESUtil.decrypt(enString);
		System.out.println("解密后的字串是：" + DeString);
		lUseTime = System.currentTimeMillis() - lStart;
		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}
}