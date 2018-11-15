package org.tech.java.compress.lz4;

import net.jpountz.lz4.*;

import java.io.*;

/**
 *原始大小：2081966
 压缩后大小：2029586
 压缩时间：601
 解压缩时间：7
 */
public class Lz4Test {

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
        LZ4Factory factory = LZ4Factory.fastestInstance();
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        LZ4Compressor compressor = factory.fastCompressor();
        LZ4BlockOutputStream compressedOutput = new LZ4BlockOutputStream(
                byteOutput, 2048, compressor);
        compressedOutput.write(srcBytes);
        compressedOutput.close();
        return byteOutput.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) throws IOException {
        LZ4Factory factory = LZ4Factory.fastestInstance();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        LZ4FastDecompressor decompresser = factory.fastDecompressor();
        LZ4BlockInputStream lzis = new LZ4BlockInputStream(
                new ByteArrayInputStream(bytes), decompresser);
        int count;
        byte[] buffer = new byte[2048];
        while ((count = lzis.read(buffer)) != -1) {
            baos.write(buffer, 0, count);
        }
        lzis.close();
        return baos.toByteArray();
    }



}
