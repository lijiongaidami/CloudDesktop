package com.hjy.monitor;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.hjy.tool.XMLDriver;

/**
 * ͨ��Client2VirtualOS.xml�ļ���ʵ��ͨ���û����õ������������˻����Ƽ������˻���¼����.
 * 
 * @author JustYoung
 * 
 */
public class Client2VirtualOS {
	static String xmlPath = Client2VirtualOS.class.getClassLoader()
			.getResource("") + "\\XML\\Client2VirtualOS.xml"; // Client2VirtualOS.xml��·��
	static HashMap<String, String> map = new HashMap<String, String>();
	static {
		Document doc = XMLDriver.initialXML(xmlPath);
		Element root = doc.getDocumentElement();
		NodeList clientList = root.getElementsByTagName("client");
		for (int i = 0; i < clientList.getLength(); i++) {
			// ��һ��������<clientName>�ڵ�
			// �ڶ���������<osTag>�ڵ�
			map.put(clientList.item(i).getChildNodes().item(1).getTextContent(),
					clientList.item(i).getChildNodes().item(3).getTextContent());
		}
	}

	/**
	 * ͨ��username�û�����Ϊkey��ȡ�ø��û��ڷ������ϵ��˻�����
	 * 
	 * @param username
	 * @return
	 */
	public static String getVirtualOS(String username) {
		return map.get(username);
	}

}
