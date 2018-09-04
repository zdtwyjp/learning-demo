package org.tech.java.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {
	
	public static void fileWrite(File file, String content) throws IOException {
		if (content == null) {
			return;
		}
		FileOutputStream out = new FileOutputStream(file);
		FileChannel outChannel = out.getChannel();
		ByteBuffer buffer = null;
		try {
			buffer = ByteBuffer.allocateDirect(content.length());
			buffer.put(content.getBytes());
			buffer.flip();
			outChannel.write(buffer);
		} finally {
			if (buffer != null) {
				buffer.clear();
			}
			if (outChannel != null) {
				outChannel.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static void fileWrite(File file, byte[] content) throws IOException {
		if (content == null) {
			return;
		}
		FileOutputStream out = null;
		FileChannel outChannel = null;
		ByteBuffer buffer = null;
		try {
			out = new FileOutputStream(file);
			outChannel = out.getChannel();
			buffer = ByteBuffer.allocateDirect(content.length);
			buffer.put(content);
			buffer.flip();
			outChannel.write(buffer);
		} finally {
			if (buffer != null) {
				buffer.clear();
			}
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
				}
				outChannel = null;
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
				out = null;
			}
		}
	}
	public static byte[] fileContent(File file) throws IOException {
		if (!file.exists()) {
			return null;
		}
		byte[] bytes = null;
		FileInputStream in = new FileInputStream(file);
		FileChannel inChannel = in.getChannel();
		try {
			long size = inChannel.size();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) size);
			inChannel.read(byteBuffer);
			bytes = byteBuffer.array();
		} finally {
			if (inChannel != null) {
				inChannel.close();
			}
			if (in != null) {
				in.close();
			}
		}
		return bytes;
	}
	
	public static void fileCopy(File srcFile, File dstFile) throws IOException {
		if (srcFile == null || dstFile == null) {
			throw new IllegalArgumentException("非法的参数。");
		}
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("源文件不存在。");
		}
		File parentDstFile = dstFile.getParentFile();
		if (!parentDstFile.exists()) {
			parentDstFile.mkdirs();
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(dstFile);
		FileChannel inChannel = in.getChannel();
		FileChannel outChannel = out.getChannel();
		try {
			ByteBuffer buffer = ByteBuffer.allocate(4096);
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
		}
	}
	
	public static void main(String[] args) throws Exception {
		File srcFile = new File("D:/soft/Google Chrome.zip");
		File dstFile = new File("D:/soft/Google Chrome_1.zip");
		fileCopy(srcFile, dstFile);
	}

}
