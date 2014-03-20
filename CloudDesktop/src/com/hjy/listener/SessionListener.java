package com.hjy.listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hjy.monitor.ClientInfo;

/**
 * ����session��session����ʱ�����û��������б���ɾ��
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
		System.out.println("���session");
		ServletContext application = arg0.getSession().getServletContext();
		HttpSession session = arg0.getSession();
		String username = (String)session.getAttribute("username");
		if (username != null)
			application.removeAttribute(username);
		@SuppressWarnings("unchecked")
		ArrayList<ClientInfo> clientList = (ArrayList<ClientInfo>)application.getAttribute("userList");
		if (clientList == null)
			return;
		// ����ɾ��clientList
		for (int i = 0; i < clientList.size(); i++){
			if (clientList.get(i).getUsername().equals(username)){
				clientList.remove(i);
				break;
			}
		}
		
	}

}
