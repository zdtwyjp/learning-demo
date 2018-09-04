package org.tech.java.util;

/**
 * 验证IP是否属于指定网段
 */
public class IpUtil {

	/**
	 * [验证IP是否属于某个IP段]
	 * @param ip 所验证的IP号码
	 * @param ipSection ipSection IP段格式：192.168.0.0/24或192.168.0.0/255.255.255.0或192.168.0.0-192.168.255.255
	 * @return
	 */
	public static boolean ipExistsInRange(String ip, String ipSection) {
		ipSection = ipSection.trim();
		ip = ip.trim();
		// 处理192.168.0.0/24或192.168.0.0/255.255.255.0情况
		int index = ipSection.indexOf("/");
		if (index > 0) {
			String beginIp = ipSection.substring(0, index);
			String endStr = ipSection.substring(index+1);
			if (endStr.contains(".")) {
				int intMask = getMaskInt(endStr);
				if (intMask < 0) {
					return false;
				} else {
					ipSection = beginIp + "/" + intMask;
				}
			}
			return isInRange(ip, ipSection);
		}
		// 处理192.168.0.0-192.168.3.255情况
		int idx = ipSection.indexOf('-');
		if (idx > 0) {
			String beginIP = ipSection.substring(0, idx);
			String endIP = ipSection.substring(idx + 1);
			return getIp2long(beginIP) <= getIp2long(ip) && getIp2long(ip) <= getIp2long(endIP);
		}
		// 处理单IP
		if (ip.equals(ipSection)) {
			return true;
		}
		return false;
	}
	
	public static long getIp2long(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip2long = 0L;
		for (int i = 0; i < 4; ++i) {
			ip2long = ip2long << 8 | Integer.parseInt(ips[i]);
		}
		return ip2long;
	}

	public static long getIp2long2(String ip) {
		ip = ip.trim();
		String[] ips = ip.split("\\.");
		long ip1 = Integer.parseInt(ips[0]);
		long ip2 = Integer.parseInt(ips[1]);
		long ip3 = Integer.parseInt(ips[2]);
		long ip4 = Integer.parseInt(ips[3]);
		long ip2long = 1L * ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;
		return ip2long;
	}
	
	/** 
     * 功能：判断一个IP是不是在一个网段下的 
     * 格式：isInRange("192.168.8.3", "192.168.9.10/22"); 
     */  
    public static boolean isInRange(String ip, String cidr) {    
        String[] ips = ip.split("\\.");    
        int ipAddr = (Integer.parseInt(ips[0]) << 24)    
                | (Integer.parseInt(ips[1]) << 16)    
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);    
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));    
        int mask = 0xFFFFFFFF << (32 - type);    
        String cidrIp = cidr.replaceAll("/.*", "");    
        String[] cidrIps = cidrIp.split("\\.");    
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)    
                | (Integer.parseInt(cidrIps[1]) << 16)    
                | (Integer.parseInt(cidrIps[2]) << 8)    
                | Integer.parseInt(cidrIps[3]);    
    
        return (ipAddr & mask) == (cidrIpAddr & mask);    
    }  
	
	public static int getMaskInt(String mask) {
    	if ("128.0.0.0".equals(mask)) return 1;  
    	if ("192.0.0.0".equals(mask)) return 2;
    	if ("224.0.0.0".equals(mask)) return 3;
    	if ("240.0.0.0".equals(mask)) return 4;
    	if ("248.0.0.0".equals(mask)) return 5;
    	if ("252.0.0.0".equals(mask)) return 6;
    	if ("254.0.0.0".equals(mask)) return 7;
    	if ("255.0.0.0".equals(mask)) return 8;
    	if ("255.128.0.0".equals(mask)) return 9;
    	if ("255.192.0.0".equals(mask)) return 10;
    	if ("255.224.0.0".equals(mask)) return 11;
    	if ("255.240.0.0".equals(mask)) return 12;
    	if ("255.248.0.0".equals(mask)) return 13;
    	if ("255.252.0.0".equals(mask)) return 14;
    	if ("255.254.0.0".equals(mask)) return 15;
    	if ("255.255.0.0".equals(mask)) return 16;
    	if ("255.255.128.0".equals(mask)) return 17;
    	if ("255.255.192.0".equals(mask)) return 18;
    	if ("255.255.224.0".equals(mask)) return 19;
    	if ("255.255.240.0".equals(mask)) return 20;
    	if ("255.255.248.0".equals(mask)) return 21;
    	if ("255.255.252.0".equals(mask)) return 22;
    	if ("255.255.254.0".equals(mask)) return 23;
    	if ("255.255.255.0".equals(mask)) return 24;
    	if ("255.255.255.128".equals(mask)) return 25;
    	if ("255.255.255.192".equals(mask)) return 26;
    	if ("255.255.255.224".equals(mask)) return 27;
    	if ("255.255.255.240".equals(mask)) return 28;
    	if ("255.255.255.248".equals(mask)) return 29;
    	if ("255.255.255.252".equals(mask)) return 30;
    	if ("255.255.255.254".equals(mask)) return 31;
    	if ("255.255.255.255".equals(mask)) return 32;
    	return -1;
    }

	public static void main(String[] args) {
		// 10.10.10.116 是否属于固定格式的IP段10.10.1.00-10.10.255.255
		String ip = "192.168.0.5 ";
		String ipSection = "192.168.0.0-192.255.255.255";
		System.out.println(ipExistsInRange(ip, ipSection));
		ipSection = "192.168.0.0/24";
		System.out.println(ipExistsInRange(ip, ipSection));
		ipSection = "192.168.0.0/255.255.255.0";
		System.out.println(ipExistsInRange(ip, ipSection));
		
		boolean exists = ipExistsInRange(ip, ipSection);
		System.out.println(exists);
		System.out.println(getIp2long(ip));
		System.out.println(getIp2long2(ip));
	}

}
