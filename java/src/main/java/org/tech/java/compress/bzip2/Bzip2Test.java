package org.tech.java.compress.bzip2;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

import java.io.*;

/**
 * 原始大小：2081966
 压缩后大小：803441
 压缩时间：579
 解压缩时间：337

 */
public class Bzip2Test {
    public static void main(String[] args) throws Exception {
        byte[] content = getFile();
        System.out.println("原始大小："+content.length);
        long b = System.currentTimeMillis();
        byte[] compressContent = compress(content);
        System.out.println("压缩后大小："+compressContent.length);
        long e = System.currentTimeMillis();
        System.out.println("压缩时间："+(e-b));
        byte[] deCompressContent = uncompress(compressContent);
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

    public static byte[] compress(byte srcBytes[]) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BZip2CompressorOutputStream bcos = new BZip2CompressorOutputStream(out);
        bcos.write(srcBytes);
        bcos.close();
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            BZip2CompressorInputStream ungzip = new BZip2CompressorInputStream(
                    in);
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
