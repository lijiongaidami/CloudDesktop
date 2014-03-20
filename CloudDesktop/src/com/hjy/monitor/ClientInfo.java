package com.hjy.monitor;

public class ClientInfo {
	private String username;
	private String password;
	private String userIP;
	private String virtualOS;

	public void setClientInfo(String username, String password, String userIP,
			String virtualOS) {
		this.username = username;
		this.password = password;
		this.userIP = userIP;
		this.virtualOS = virtualOS;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getUserIP(){
		return this.userIP;
	}
	
	public String getVirtualOS(){
		return this.virtualOS;
	}
}
