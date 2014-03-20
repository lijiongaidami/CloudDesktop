package com.hjy.tool;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过此类获得IP
 * @author JustYoung
 *
 */
public class IpInfo {
	
	/**
	 * 根据request返回客户的IP值
	 * @param request
	 * @return
	 */
	private static String hostIp = null;
	
	/**
	 * 返回客户端的IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	/**
	 * 返回服务器的IP
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getHostIp() throws UnknownHostException{
		if (hostIp != null)
			return hostIp;
		Enumeration<NetworkInterface> e = null;
		try {
			e = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String ip = null;
		while (e.hasMoreElements()){
			Enumeration<InetAddress> ee = e.nextElement().getInetAddresses();
			while (ee.hasMoreElements()){
				ip = ee.nextElement().getHostAddress();
				if (ip.contains(":") || ip.equals("127.0.0.1"))
					continue;
				hostIp = ip;
				return ip;
			}
		}
		return hostIp;
		
	}
}
