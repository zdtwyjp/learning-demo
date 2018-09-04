package org.tech.java.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
	static int[] messages = {1,3,5,7};
	public static void main(String[] args) throws Exception {
		read();
		write();
	}

	public static void read() throws Exception {
		FileInputStream fis = new FileInputStream("d:\\test.txt");

		FileChannel channel = fis.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		channel.read(buffer);

		buffer.flip();

		while (buffer.remaining() > 0) {
			byte b = buffer.get();
			System.out.println((char) b);
		}
		fis.close();
	}

	public static void write() throws Exception{
		FileOutputStream fis = new FileOutputStream("d:\\testWrite.txt");
		
		FileChannel fc = fis.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		
		for (int i=0; i<messages.length; ++i) {
			buffer.putInt(i, messages[i]);
		}
		
		buffer.flip();
		
		fc.write(buffer);
		
		fis.close();
		
	}
}
