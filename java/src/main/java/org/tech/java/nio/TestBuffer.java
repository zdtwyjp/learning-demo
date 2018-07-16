package org.tech.java.nio;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * http://blog.csdn.net/wuxianglong/article/details/6612246
 * 
 * 测试缓冲区
 * 
 */
public class TestBuffer {
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fis = new FileInputStream("d:\\test.txt");

		FileChannel channel = fis.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		output("init", buffer);
		
		channel.read(buffer);
		
		output("read()", buffer);
		
		buffer.flip();
		
		output("flip()", buffer);
		
		while (buffer.remaining() > 0) {
			byte b = buffer.get();
		}
		
		output("remaining()", buffer);
		
		buffer.clear();
		
		output("clear", buffer);
	}

	
	public static void output(String step, Buffer buffer) {
		System.out.print(step + ":");
		System.out.print("capacity:"+buffer.capacity()+",");
		System.out.print("position:"+buffer.position()+",");
		System.out.print("limit:"+buffer.limit());
		System.out.println();
	}
}
