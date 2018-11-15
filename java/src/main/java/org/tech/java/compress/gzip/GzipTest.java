package org.tech.java.compress.gzip;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 原始大小：2081966
 压缩后大小：902383
 压缩时间：70
 解压缩时间：12
 */
public class GzipTest {

    public static void main(String[] args) throws Exception {
        byte[] content = getFile();
        System.out.println("原始大小："+content.length);
        long b = System.currentTimeMillis();
        byte[] compressContent = compress(content);
        System.out.println("压缩后大小："+compressContent.length);
        long e = System.currentTimeMillis();
        System.out.println("压缩时间："+(e-b));
        byte[] deCompressContent = uncompress(compressContent);
        System.out.println("解压缩大小："+deCompressContent.length);
        long e2 = System.currentTimeMillis();
        System.out.println("解压缩时间："+(e2-e));
    }

    public static byte[] getFile() throws Exception {
        File file = new File("E:/ti.json");
        Long len = file.length();
        byte[] content  = new byte[len.intValue()];
        FileInputStream is = new FileInputStream(file);
        is.read(content);
        is.close();
        return content;
    }

    public static byte[] compress(byte srcBytes[]) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(srcBytes);
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[2048];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

}
