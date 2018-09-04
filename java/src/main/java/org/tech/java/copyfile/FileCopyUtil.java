package org.tech.java.copyfile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 测试文件拷贝效率
 *
 *  FileChannel.transformTo(transferFrom) > FileChannel DirectBuffer > FileChannel Non DirectBuffer > BufferedStram > FileChannel.map
 *
 */
public class FileCopyUtil {
    /**
     * also tested for 16, 32, 64, 128, 256 and 1024
     */
    private static final int BUFFER_SIZE_KB = 64;
    private static final int BUFFER_SIZE = BUFFER_SIZE_KB * 1024;

    /**
     * 使用NIO 堆内方式拷贝文件
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void copyFileByFileChannelNonDirectBuffer(File srcFile, File dstFile) throws IOException {
        long startTime = System.nanoTime();
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dstFile);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while(inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (in != null) {
                in.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (out != null) {
                out.close();
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("copyFileByFileChannelNonDirectBuffer Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        }
    }

    /**
     * 使用NIO 堆外方式拷贝文件
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void copyFileByFileChannelDirectBuffer(File srcFile, File dstFile) throws IOException {
        long startTime = System.nanoTime();
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dstFile);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        try {
            ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);
            while(inChannel.read(buffer) != -1) {
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (in != null) {
                in.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (out != null) {
                out.close();
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("copyFileByFileChannelDirectBuffer Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        }
    }

    /**
     * 使用NIO transferTo拷贝文件
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void copyFileByFileChannelTransferTo(File srcFile, File dstFile) throws IOException {
        long startTime = System.nanoTime();
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dstFile);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        try {
            long size = inChannel.size();
            inChannel.transferTo(0, size, outChannel);
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (in != null) {
                in.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (out != null) {
                out.close();
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("copyFileByFileChannelTransferTo Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        }
    }

    /**
     * 使用NIO transferFrom拷贝文件
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void copyFileByFileChannelTransferFrom(File srcFile, File dstFile) throws IOException {
        long startTime = System.nanoTime();
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dstFile);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        try {
            long size = inChannel.size();
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (in != null) {
                in.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (out != null) {
                out.close();
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("copyFileByFileChannelTransferFrom Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        }
    }

    /**
     * 使用NIO map拷贝文件
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void copyFileByFileChannelMap(File srcFile, File dstFile) throws IOException {
        long startTime = System.nanoTime();
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dstFile);
        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();
        try {
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,
                    0, inChannel.size());
            outChannel.write(buffer);
            buffer.clear();
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (in != null) {
                in.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
            if (out != null) {
                out.close();
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("copyFileByFileChannelMap Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        }
    }

    /**
     *  采用传统IO 拷贝文件
     * @param srcFile
     * @param dstFile
     * @throws IOException
     */
    public static void copyFileByBufferedStream(File srcFile, File dstFile) throws IOException {
        long startTime = System.nanoTime();
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dstFile));
        try {
            byte[] byteArray = new byte[BUFFER_SIZE];
            int bytesCount;
            while ((bytesCount = in.read(byteArray)) != -1) {
                out.write(byteArray, 0, bytesCount);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("copyFileByBufferedStream Elapsed Time is " + (elapsedTime / 1000000000.0) + " seconds");
        }
    }

}
