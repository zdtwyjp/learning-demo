package org.tech.java.socket.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Client {
	static private Random r = new Random();

	public static void main(String[] args) {
		try {
			// 1.�����ͻ���Socket��ָ����������ַ�Ͷ˿ں�
			Socket socket = new Socket("127.0.0.1", 8888);
			// 2.��ȡ������������������������Ϣ
			OutputStream os = socket.getOutputStream();// �ֽ������
			// ת��Ϊ��ӡ��
			PrintWriter pw = new PrintWriter(os);

			long name = Math.abs(r.nextLong());
			long pass = Math.abs(r.nextLong());

			pw.write("�û�����" + name + "�����룺" + pass);
			pw.flush();// ˢ�»��棬��������������Ϣ
			// �ر������
			socket.shutdownOutput();
			// 3.��ȡ��������������ȡ�������˵���Ӧ��Ϣ
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("���ǿͻ��ˣ��������˷��ص���Ϣ�ǣ�" + info);
			}
			// 4.�ر���Դ
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
}