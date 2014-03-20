package com.hjy.monitor;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 此类用来处理在线用户
 * @author JustYoung
 *
 */
public class OnlineClients {

	static public String showOnlineClients(ServletContext application,
			HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		ArrayList<ClientInfo> userList = (ArrayList<ClientInfo>) application
				.getAttribute("userList");
		if (userList == null)
			return null;
		StringBuilder result = new StringBuilder("");
		for (ClientInfo i : userList) {
			result.append("<tr>");
			result.append(String.format(
					"<td>%s</td><td>%s</td><td>%s</td><td>在线</td>",
					i.getUsername(), i.getUserIP(), i.getVirtualOS()));
			result.append("</tr>");
		}
		return result.toString();
	}
}
