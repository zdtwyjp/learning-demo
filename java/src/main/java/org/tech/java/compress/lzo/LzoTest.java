package org.tech.java.compress.lzo;

import org.anarres.lzo.*;

import java.io.*;

/**
 *原始大小：2081966
 压缩后大小：1912062
 压缩时间：156
 解压缩时间：16
 */
public class LzoTest {

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
        LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(
                LzoAlgorithm.LZO1X, null);

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        LzoOutputStream cs = new LzoOutputStream(os, compressor);
        cs.write(srcBytes);
        cs.close();

        return os.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) throws IOException {
        LzoDecompressor decompressor = LzoLibrary.getInstance()
                .newDecompressor(LzoAlgorithm.LZO1X, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        LzoInputStream us = new LzoInputStream(is, decompressor);
        int count;
        byte[] buffer = new byte[2048];
        while ((count = us.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        return baos.toByteArray();
    }


}
