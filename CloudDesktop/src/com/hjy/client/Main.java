package com.hjy.client;

import java.net.SocketException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			listenUdp.run();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
