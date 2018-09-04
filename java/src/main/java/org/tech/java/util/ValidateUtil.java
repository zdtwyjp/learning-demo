package org.tech.java.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class ValidateUtil {
	
	/**
	 * IPv4 正则表达式<p>
	 * [1-255].[1-255].[1-255].[1-255]
	 */
	private final static String ipv4Regex = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
	
	/**
	 * IPv6 正则表达式<p>
	 * 支持各种缩写方式
	 */
	private final static String ipv6Regex = "^([\\da-fA-F]{1,4}:){6}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^::([\\da-fA-F]{1,4}:){0,4}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:):([\\da-fA-F]{1,4}:){0,3}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){2}:([\\da-fA-F]{1,4}:){0,2}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){3}:([\\da-fA-F]{1,4}:){0,1}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){4}:((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$|^:((:[\\da-fA-F]{1,4}){1,6}|:)$|^[\\da-fA-F]{1,4}:((:[\\da-fA-F]{1,4}){1,5}|:)$|^([\\da-fA-F]{1,4}:){2}((:[\\da-fA-F]{1,4}){1,4}|:)$|^([\\da-fA-F]{1,4}:){3}((:[\\da-fA-F]{1,4}){1,3}|:)$|^([\\da-fA-F]{1,4}:){4}((:[\\da-fA-F]{1,4}){1,2}|:)$|^([\\da-fA-F]{1,4}:){5}:([\\da-fA-F]{1,4})?$|^([\\da-fA-F]{1,4}:){6}:$";
	
	/**
	 * 邮箱正则表达式
	 */
	private final static String emailRegex = "^(?!(?:(?:\\x22?\\x5C[\\x00-\\x7E]\\x22?)|(?:\\x22?[^\\x5C\\x22]\\x22?)){255,})(?!(?:(?:\\x22?\\x5C[\\x00-\\x7E]\\x22?)|(?:\\x22?[^\\x5C\\x22]\\x22?)){65,}@)(?:(?:[\\x21\\x23-\\x27\\x2A\\x2B\\x2D\\x2F-\\x39\\x3D\\x3F\\x5E-\\x7E]+)|(?:\\x22(?:[\\x01-\\x08\\x0B\\x0C\\x0E-\\x1F\\x21\\x23-\\x5B\\x5D-\\x7F]|(?:\\x5C[\\x00-\\x7F]))*\\x22))(?:\\.(?:(?:[\\x21\\x23-\\x27\\x2A\\x2B\\x2D\\x2F-\\x39\\x3D\\x3F\\x5E-\\x7E]+)|(?:\\x22(?:[\\x01-\\x08\\x0B\\x0C\\x0E-\\x1F\\x21\\x23-\\x5B\\x5D-\\x7F]|(?:\\x5C[\\x00-\\x7F]))*\\x22)))*@(?:(?:(?!.*[^.]{64,})(?:(?:(?:xn--)?[a-z0-9]+(?:-[a-z0-9]+)*\\.){1,126}){1,}(?:(?:[a-z][a-z0-9]*)|(?:(?:xn--)[a-z0-9]+))(?:-[a-z0-9]+)*)|(?:\\[(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9][:\\]]){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?)))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))\\]))$";
	
	/**
	 * 密码正则表达式<p>
	 * 长度限制5-17
	 */
	private final static String passwordRegex = "^[a-zA-Z]w{5,17}$";
	
	/**
	 * 正整数正则表达式
	 */
	private final static String integerRegex = "^\\d+$";
	
	/**
	 * 域名正则表达式<p>
	 * 支持带协议和不带协议的域名
	 */
	private final static String domainRegex = "^(((http[s]?|ftp)[:]//)?)([0-9a-zA-Z][0-9a-zA-Z-]{0,62}\\.)+([0-9a-zA-Z][0-9a-zA-Z-]{0,62})\\.?$";
	
	/**
	 * 日期正则表达式（中国）<p>
	 * 年/月/日、年-月-日、年.月.日（支持润年自动判断）
	 */
	private final static String chinaDateRegex = "(^((\\d*(0?[048]|[2468][048]|[13579][26])00|\\d*(0?[48]|[2468][048]|[13579][26]))-0?2-29)$|^[1-9]\\d*-((0?[13578]|10|12)-(3[01]|[12]\\d|0?[1-9])|(0?[469]|11)-(30|[12]\\d|0?[1-9])|(0?2)-(2[0-8]|1\\d|0?[1-9]))$)|(^((\\d*(0?[048]|[2468][048]|[13579][26])00|\\d*(0?[48]|[2468][048]|[13579][26]))\\/0?2\\/29)$|^[1-9]\\d*\\/((0?[13578]|10|12)\\/(3[01]|[12]\\d|0?[1-9])|(0?[469]|11)\\/(30|[12]\\d|0?[1-9])|(0?2)\\/(2[0-8]|1\\d|0?[1-9]))$)|(^((\\d*(0?[048]|[2468][048]|[13579][26])00|\\d*(0?[48]|[2468][048]|[13579][26]))\\.0?2\\.29)$|^[1-9]\\d*\\.((0?[13578]|10|12)\\.(3[01]|[12]\\d|0?[1-9])|(0?[469]|11)\\.(30|[12]\\d|0?[1-9])|(0?2)\\.(2[0-8]|1\\d|0?[1-9]))$)";
	
	/**
	 * 日期正则表达式（欧洲）<p>
	 * 月/日/年（支持润年自动判断）
	 */
	private final static String europeDateRegex = "^(0?2\\/29\\/(\\d*(0?[048]|[2468][048]|[13579][26])00|\\d*(0?[48]|[2468][048]|[13579][26])))$|^((0?[13578]|10|12)\\/(3[01]|[12]\\d|0?[1-9])|(0?[469]|11)\\/(30|[12]\\d|0?[1-9])|(0?2)\\/(2[0-8]|1\\d|0?[1-9]))\\/[1-9]\\d*$";
	
	/**
	 * 时间正则表达式<p>
	 * [0-23]:[0-59]:[0-59] [am|pm|AM|PM] | [0-23]:[0-59] [am|pm|AM|PM]
	 */
	private final static String timeRegex = "^([01]?\\d|2[123]):([0-4]?\\d|5\\d)(:([0-4]?\\d|5\\d)(\\.\\d+|-?0\\.\\d*[1-9]\\d*)? )?(am|AM|pm|PM)?$";
	
	private final static String hexRegex = "^([a-fA-F0-9][a-fA-F0-9]\\s)*[a-fA-F0-9][a-fA-F0-9]$";
	/**
     * 防止被实例化
     */
    private ValidateUtil() {
    }
	
	private static boolean regularString(String str, String regex) {
		Pattern pat = Pattern.compile(regex);
		Matcher mat = pat.matcher(str);
		return mat.find();
	}
	
	
	/**
	 * validate IPv4 format<p>
	 * [1-255].[1-255].[1-255].[1-255]
	 * @param str
	 * @return boolean
	 */
	public static boolean ipv4(String str){
		return regularString(str, ipv4Regex);
	}
	
	/**
	 * validate integer format
	 * @param str
	 * @return boolean
	 */
	public static boolean integer(String str){
		return regularString(str, integerRegex);
	}
	
	/**
	 * validate IPv6 format<p>
	 * Support for way
	 * @param str
	 * @return boolean
	 */
	public static boolean ipv6(String str){
		return regularString(str, ipv6Regex);
	}
	
	/**
	 * validate time format<p>
	 * [0-23]:[0-59]:[0-59] [am|pm|AM|PM] | [0-23]:[0-59] [am|pm|AM|PM]
	 * @param str
	 * @return boolean
	 */
	public static boolean time(String str){
		return regularString(str, timeRegex);
	}
	
	/**
	 * validate dateChina format<p>
	 * Year/Month/Day、Year-Month-Day、Year.Month.Day（Support the leap year judgment）
	 * @param str
	 * @return boolean
	 */
	public static boolean dateChina(String str){
		return regularString(str, chinaDateRegex);
	}
	
	/**
	 * validate dateEurope format<p>
	 * Month/Day/Year（Support the leap year judgment）
	 * @param str
	 * @return boolean
	 */
	public static boolean dateEurope(String str) {
		return regularString(str, europeDateRegex);
	}
	
	/**
	 * validate start_date ,end_date
	 * end_date must after start_date
	 * @return boolean
	 */
	public static boolean dateEnd_Start(String start,String end) {
		int intStart = Integer.parseInt(start);
		int intEnd = Integer.parseInt(end);
		return intEnd >= intStart;
	}
	
	/**
	 * validate email address format
	 * @param str str
	 * @return boolean
	 */
	public static boolean email(String str) {
		return regularString(str, emailRegex);
	}
	
	/**
	 * validates url format
	 * Abbreviation support agreement
	 * @param str str
	 * @return boolean
	 */
	public static boolean url(String str) {
		return regularString(str, domainRegex);
	}
	
	/**
	 * validates password format
	 * limitation of length 5 - 17
	 * @param str str
	 * @return boolean
	 */
	public static boolean password(String str) {
		return regularString(str, passwordRegex);
	}
	
	public static boolean hex(String str) {
		return regularString(str, hexRegex);
	}
	
	public static boolean domain(String str) {
		return regularString(str, domainRegex);
	}
	
	/**
	 * validates string minlength
	 * @param str str
	 * @param min min
	 * @return boolean
	 */
	public static boolean minlength(String str, int min) {
		return str.length() > min;
	}

	/**
	 * validates string maxlength
	 * @param str str
	 * @param max  max
	 * @return boolean
	 */
	public static boolean maxlength(String str, int max) {
		return str.length() <= max;
	}
	
	/**
	 * validates string length range
	 * @param str str
	 * @param max max
	 * @param min min
	 * @return boolean
	 */
	public static boolean rangelength(String str, int max, int min) {
		return str.length() >= min && str.length() <= max;
	}

	private static boolean isEmpty(String str) {
		return (str == null || "".equals(str.trim()));
	}
	
	public static boolean isValidIpSession(String ipSession) {
		
		if (isEmpty(ipSession)) {
			return false;
		}
		try {
			String[] ips = ipSession.split("<->");
			if (ips.length != 2) {
				return false;
			}
			for (String ip : ips) {
				if (!isValidIpV4(ip)) {
					return false;
				}
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isValidTcpSession(String tcpSession) {
		
		if (isEmpty(tcpSession)) {
			return false;
		}
		try {
			String[] ipPorts = tcpSession.split("<->");
			if (ipPorts.length != 2) {
				return false;
			}
			for (String ipPort : ipPorts) {
				String[] ipPortArr = ipPort.split(":");
				if (!ipPortArr[0].startsWith("[") || !ipPortArr[0].endsWith("]")) {
					return false;
				}
				if (!isValidIpV4(ipPortArr[0].substring(1, ipPortArr[0].length() - 1))) {
					Integer.parseInt(ipPortArr[1]);
					return false;
				}
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isValidIpV4(String ip) {
		
		if (isEmpty(ip)) {
			return false;
		}
		try {
			String[] numArr = ip.split("\\.");
			for (String num : numArr) {
				if (Integer.parseInt(num) > 255) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		
		
		return true;
	}

	public static void main(String[] args) {
		System.out.print(isValidIpV4("192.168.2.2"));
	}
}
