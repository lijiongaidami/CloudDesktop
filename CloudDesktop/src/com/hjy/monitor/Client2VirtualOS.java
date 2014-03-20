package com.hjy.monitor;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hjy.tool.XMLDriver;

/**
 * 通过Client2VirtualOS.xml文件，实现通过用户名得到服务器虚拟账户名称及虚拟账户登录密码.
 * 
 * @author JustYoung
 * 
 */
public class Client2VirtualOS {
	static String xmlPath = Client2VirtualOS.class.getClassLoader()
			.getResource("") + "\\XML\\Client2VirtualOS.xml"; // Client2VirtualOS.xml的路径
	static HashMap<String, String> map = new HashMap<String, String>();
	static {
		Document doc = XMLDriver.initialXML(xmlPath);
		Element root = doc.getDocumentElement();
		NodeList clientList = root.getElementsByTagName("client");
		for (int i = 0; i < clientList.getLength(); i++) {
			// 第一个参数是<clientName>节点
			// 第二个参数是<osTag>节点
			map.put(clientList.item(i).getChildNodes().item(1).getTextContent(),
					clientList.item(i).getChildNodes().item(3).getTextContent());
		}
	}

	/**
	 * 通过username用户名作为key来取得该用户在服务器上的账户名称
	 * 
	 * @param username
	 * @return
	 */
	public static String getVirtualOS(String username) {
		return map.get(username);
	}

}
