package com.hjy.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * �ڴ����м���UDP���ݱ�
 * 
 * @author JustYoung
 * 
 */
public class listenUdp {

	public static void run() throws SocketException {
		DatagramSocket ds = new DatagramSocket(8081);
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		String result = null;
		while (true) {
			// ����
			try {
				ds.receive(dp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ds.close();
			}
			if (dp != null) {
				result = new String(dp.getData(), 0, dp.getLength());
				System.out.println(result);
				// ��������ǵĿͻ�����ͨ��TCP���������ǵ�IP.
				if (result.equals("CloudDesktop")) {
					System.out.println(dp.getAddress().getHostAddress());
					System.out.println(dp.getPort());
					sendTcp tcp = new sendTcp(dp.getAddress().getHostAddress());
					tcp.start();
				}
			}
		}
	}
}
