package com.hjy.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hjy.monitor.ClientInfo;

/**
 * 监听session，session销毁时，把用户从在线列表中删除
 * @author JustYoung
 *
 */
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("清除session");
		ServletContext application = arg0.getSession().getServletContext();
		HttpSession session = arg0.getSession();
		String username = (String)session.getAttribute("username");
		if (username != null)
			application.removeAttribute(username);
		@SuppressWarnings("unchecked")
		ArrayList<ClientInfo> clientList = (ArrayList<ClientInfo>)application.getAttribute("userList");
		if (clientList == null)
			return;
		// 查找删除clientList
		for (int i = 0; i < clientList.size(); i++){
			if (clientList.get(i).getUsername().equals(username)){
				clientList.remove(i);
				break;
			}
		}
		
	}

}
