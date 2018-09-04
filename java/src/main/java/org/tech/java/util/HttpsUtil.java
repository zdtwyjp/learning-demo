package org.tech.java.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpsUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

    private static class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public static HttpsURLConnection getHttpsURLConnection(String uri) throws IOException {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
        } catch (KeyManagementException e) {
        	logger.error(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
        	logger.error(e.getMessage(), e);
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();
        
        URL url = new URL(uri);
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        httpsConn.setDoInput(true);
        httpsConn.setDoOutput(true);
        return httpsConn;
    }
    
    public static String request(String url, String data) {
    	BufferedReader in = null;
        try {
            HttpsURLConnection conn = getHttpsURLConnection(url);
            conn.setConnectTimeout(130000);// 连接超时，毫秒
            conn.setReadTimeout(130000);// 连接超时，毫秒
            conn.setDoOutput(true);// 设置允许输出
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            conn.setRequestProperty("Charset", "UTF-8");
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Type", "application/json");
            if (data != null && data.trim().length() > 0) {
                logger.info("param:{}", data);
                byte[] bt = data.getBytes();
                conn.setRequestProperty("Content-Length", String.valueOf(bt.length));
                OutputStream os = conn.getOutputStream();
            	os.write(bt);
            	os.flush();
                os.close();
            }

            // 服务器返回的响应码
            int code = conn.getResponseCode();
            logger.info("ResponseCode:"+code);
            if (code == 200) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line = null;
                String responseData = "";
                while ((line = in.readLine()) != null) {
                    responseData += line;
                }
                return responseData;
            } else {
            	logger.error("请求失败，响应码："+code);
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        } finally {
        	if (in != null) {
        		try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return null;
	}
}