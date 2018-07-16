package org.tech.java.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileCopy {
	
	public static void fileCopy(String source, String target) throws IOException {
		try (InputStream is = new FileInputStream(source)) {
			try (OutputStream os = new FileOutputStream(target)) {
				byte[] buffer = new byte[4096];
				int index;
				while ((index = is.read(buffer)) != -1) {
					os.write(buffer, 0, index);
				}
			}
		}
	}

	public static void fileCopyByNIO(String source, String target) throws IOException {
		try (FileInputStream is = new FileInputStream(source)) {
			try (FileOutputStream os = new FileOutputStream(target)) {
				FileChannel inChannel = is.getChannel();
				FileChannel outChannel = os.getChannel();
				ByteBuffer buffer = ByteBuffer.allocate(4096);
				while (inChannel.read(buffer) != -1) {
					buffer.flip();
					outChannel.write(buffer);
					buffer.clear();
				}
			}
		}
	}
}
