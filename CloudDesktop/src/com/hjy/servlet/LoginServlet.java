package com.hjy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hjy.monitor.Client2VirtualOS;
import com.hjy.monitor.ClientInfo;
import com.hjy.tool.IpInfo;
import com.hjy.tool.XMLDriver;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleProcess(request, response);
	}

	@SuppressWarnings("unchecked")
	private void handleProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		System.out.println("正在登录");
		String username = null;
		String password = null;
		username = request.getParameter("userName");
		password = request.getParameter("password");
		HttpSession session = request.getSession();
		// 判断登录是否合法
		if (!isLegal(username, password)) {
			session.setAttribute("isLogin", "false");
			session.setAttribute("username", null);
			// response.sendRedirect("LoginFailed.jsp");
			out.write("LoginFailed");
			return;
		}

		// 将username存入session
		session.setAttribute("username", username);
		session.setAttribute("isLogin", "true");
		ServletContext application = this.getServletContext();
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClientInfo(username, password, IpInfo.getIpAddr(request),
				Client2VirtualOS.getVirtualOS(username));
		ArrayList<ClientInfo> userList; // 存放已登录的用户列表
		if (application.getAttribute("userList") == null) {
			userList = new ArrayList<ClientInfo>();
			userList.add(clientInfo);
			application.setAttribute("userList", userList);
			application.setAttribute(clientInfo.getUsername(), "exists");
		} else {
			// 防止重复登录.
			if (application.getAttribute(clientInfo.getUsername()) == null) {
				userList = (ArrayList<ClientInfo>) application
						.getAttribute("userList");
				userList.add(clientInfo);
				application.setAttribute("userList", userList);
				application.setAttribute(clientInfo.getUsername(), "exists");
			}
		}
		out.write("SuccessedLogin \n");
		out.write(Client2VirtualOS.getVirtualOS(username) + "\n");
		out.write(IpInfo.getHostIp() + "\n");
		
	}

	/**
	 * 根据用户名、密码以及ClientInfo.xml文件，检查用户登录是否合法。
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	private boolean isLegal(String userName, String password) {
		if (userName == null)
			return false;
		boolean isUserNameLegal = false;
		int pos = 0;
		Document document = XMLDriver.initialXML(LoginServlet.class
				.getClassLoader().getResource("") + "\\XML\\ClientInfo.xml");
		Element root = document.getDocumentElement();
		NodeList clientList = root.getElementsByTagName("cloud");
		// 查找用户名是否合法.
		for (int i = 0; i < clientList.getLength(); i++) {
			if (clientList.item(i).getChildNodes().item(1).getTextContent()
					.equals(userName)) {
				isUserNameLegal = true;
				pos = i;
				break;
			}
		}
		if (!isUserNameLegal) // 用户名不合法
			return false;
		if (clientList.item(pos).getChildNodes().item(3).getTextContent()
				.equals(password))
			return true;
		else
			// 密码不合法
			return false;
	}
}
