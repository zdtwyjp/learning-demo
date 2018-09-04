package org.tech.java.nio;

import sun.nio.ch.DirectBuffer;

import java.nio.ByteBuffer;

public class DirectBufferClean {

	public static void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 500);
		System.out.println("start");
		sleep(10000);
		System.gc();
		clean(buffer);
		System.out.println("end");
		sleep(10000);
	}

	public static void clean(final ByteBuffer byteBuffer) {
		if (byteBuffer.isDirect()) {
			((DirectBuffer) byteBuffer).cleaner().clean();
		}
	}
}
