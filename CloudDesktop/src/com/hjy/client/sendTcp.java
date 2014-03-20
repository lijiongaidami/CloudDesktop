package com.hjy.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * 通过此类向客户端发送TCP报文，报文内容是本机的IP
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
	 * 向客户端发送本机--服务器的IP地址
	 * @param ip
	 * @throws IOException
	 */
	public static void send(String ip) throws IOException{
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("对方已接收消息");
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
