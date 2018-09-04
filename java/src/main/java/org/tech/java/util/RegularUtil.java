package org.tech.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具类
 */
public class RegularUtil {
	
	private static boolean matches(String rexp, String str) {
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(str);
		return mat.matches();
	}

	/**
	 * 是否为IP
	 * @param addr
	 * @return boolean   
	 */
	public static boolean isIP(String addr) {
		if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
			return false;
		}
		String rexp = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
		return matches(rexp, addr);
	}
	
	/**
	 * 是否为MD5
	 * @param md5
	 * @return boolean   
	 */
	public static boolean isMD5(String md5) {
		if (md5==null || "".equals(md5) || md5.length() != 32) {
			return false;
		}
		String rexp = "[a-fA-F0-9]{32}";
		return matches(rexp, md5);
	}
	
	/**
	 * 是否为SSL证书
	 * @param ssl
	 * @return boolean   
	 */
	public static boolean isSSL(String ssl) {
		String rexp1 = "[a-fA-F0-9]{32}";
		String rexp2 = "[a-fA-F0-9]{40}";
		String rexp3 = "([a-fA-F0-9]{2}[:\\s]){19}([a-fA-F0-9]{2})";
		return matches(rexp1, ssl) || matches(rexp2, ssl) || matches(rexp3, ssl);
	}

	/**
	 * 是否为域名
	 * @param domain
	 * @return boolean   
	 */
	public static boolean isDomain(String domain) {
		if (domain==null || "".equals(domain)) {
			return false;
		}
//		String rexp = "(([\\w|\\*]+[\\-]?[\\w|\\*]+)+\\.){1,3}[\\w|\\*]+";
		String rexp = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
		return matches(rexp, domain);
	}

	public static void main(String[] args) {
		System.out.println(isIP("192.168.3.3"));
		System.out.println(isIP("192.168.3"));
	}
}
