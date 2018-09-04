package org.tech.java.util;

import java.math.BigDecimal;

/**
 * Title: 格式化单位 kb/M/G/Tb
 * Description: TestDemo
 */
public class FlowFormatUtil {

    /**
     * MethodsTitle: 格式转化工具类
     */
    public static String getFromartSize(double size){
        double kiloByte = size / 1024;
        if (kiloByte < 1) {  
            return size+"B";
        }
        //计算kiloByte
        double megaByte = kiloByte / 1024;  
        if (megaByte < 1) {  
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));  
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";  
        }
        //计算megaByte
        double gigaByte = megaByte / 1024;  
        if (gigaByte < 1) {  
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));  
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";  
        }
        //计算gigaByte
        double teraBytes = gigaByte / 1024;  
        if (teraBytes < 1) {  
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));  
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";  
        }
        //计算TB
        BigDecimal result4 = new BigDecimal(teraBytes);  
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"; 
    }

    public static void main(String[] args) {
        double size=Double.valueOf("100000");
        String fmort=getFromartSize(size);
        System.out.println("Fmort size is : "+fmort);
    }
}
