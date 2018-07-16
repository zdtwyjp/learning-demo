package org.tech.java.nio;

import java.nio.ByteBuffer;

import sun.nio.ch.DirectBuffer;

/**
 * http://blog.csdn.net/xieyuooo/article/details/7547435
 * 
 * 测试内存回收
 * 
 * @author chit
 *
 */
public class TestDirectByteBuffer {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 100);
		System.out.println("start");
		sleep(10000);
		clean(buffer);
		System.out.println("end");
		sleep(10000);
	}
	
	public static void clean(ByteBuffer buffer) {
		if (buffer.isDirect()) {
			DirectBuffer db = (DirectBuffer)buffer;
			db.cleaner().clean();
		}
	}
	
	public static void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
