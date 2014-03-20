package com.hjy.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * ͨ��������ͻ��˷���TCP���ģ����������Ǳ�����IP
 * @author JustYoung
 *
 */
public class sendTcp extends Thread{

	private String ip = null;
	private static int port = 8081;
		
	public sendTcp(String IP){
		this.ip = IP;
	}
	
	/**
	 * ��ͻ��˷��ͱ���--��������IP��ַ
	 * @param ip
	 * @throws IOException
	 */
	public static void send(String ip) throws IOException{
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�Է��ѽ�����Ϣ");
			return;
		}
		PrintWriter out = new PrintWriter(socket.getOutputStream(),false);
		out.print("welcome");
		out.flush();
		socket.close();
	}
	
	public void run(){
		try {
			send(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
