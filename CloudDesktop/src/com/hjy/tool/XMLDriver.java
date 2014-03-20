package com.hjy.tool;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

/**
 * ����XML������װ�ڴ�.
 * 
 * @author hjy
 * 
 */
public class XMLDriver {

	/**
	 * ��ʼ��XML���󣬻�ö�XML������Document doc.
	 * 
	 * @param XMLpath
	 * @return �ɹ�����Document doc�����򷵻�null;
	 */
	public static Document initialXML(String XMLpath) {
		// ���XML�ļ�.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("����DocumentBuilder����ʧ�ܣ�at XMLDriver.java, at initialXML()");
			return null;
		}
		Document doc = null;
		try {
			doc = builder.parse(XMLpath);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("��ȡdoc����ʧ�ܣ�at XMLDriver.java, at initialXML().");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("��ȡdoc����ʧ�ܣ�at XMLDriver.java, at initialXML().");
			e.printStackTrace();
			return null;
		}
		return doc;
	}

	/**
	 * ����XML�����ļ�
	 * 
	 * @param doc
	 *            XML��Document����
	 * @param path
	 *            ��Ҫ�����·��
	 * @return ����ɹ�����true�����򷵻�false.
	 */
	public static boolean saveXML(Document doc, String path) {
		if (doc == null || path == null) {
			System.out.println("doc��path��null,at XMLDriver.java, at saveXML.");
			return false;
		}
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			System.out
					.println("transformer���񴴽�ʧ��. at XMLDriver.java, at saveXML.");
			e.printStackTrace();
			return false;
		}
		DOMSource source = new DOMSource(doc);
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		StreamResult result = new StreamResult(new File(path));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("XML�ļ�����ʧ��. at XMLDriver.java, at saveXML.");
			return false;
		}
		return true;
	}

	/**
	 * ��ȡXML��Ϣ
	 * 
	 * @param fileName
	 *            ��ȡXML�ļ�������
	 * @param tagName
	 *            ��ȡ��ĳ����ǩ
	 * @return ����XML��Ϣ�����󷵻�null.
	 */
	public static String XmlReader(String fileName, String tagName) {
		File file = new File(fileName);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document doc = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("DocumentBuilder���󴴽�ʧ��. at XMLDriver.java, at XmlReader().");
			return null;
		}
		try {
			doc = builder.parse(file);// ��ȡ��XML�ļ�
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("Document���󴴽�ʧ��. at XMLDriver.java, at XmlReader().");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("Document���󴴽�ʧ��. at XMLDriver.java, at XmlReader().");
			return null;
		}
		Element root = doc.getDocumentElement();
		NodeList nodelist = root.getElementsByTagName(tagName);
		Element node = (Element) nodelist.item(0);
		return node.getTextContent();
	}

	/**
	 * ר����xml�ļ���д�����¸�ʽ������.
	 * <property>
	 * <name>content</name>
	 * <value>content</value>
	 * </property>
	 * @param doc Document����
	 * @param nameForProperty name���ı��ڵ�
	 * @param valueForProperty value���ı��ڵ�
	 * @return д��ɹ�����doc Document���󣬷��򷵻�null.
	 */
	public static Document setPropertyTag(Document doc, String nameForProperty,
			String valueForProperty) {
		if (doc == null) {
			System.out
					.println("Document����docΪ�գ� at XMLDriver.java��at setPropertyTag()");
			return null;
		}
		Element root = doc.getDocumentElement();
		Element property = doc.createElement("property");
		Element name = doc.createElement("name");
		Element value = doc.createElement("value");
		Text content = doc.createTextNode(nameForProperty);
		name.appendChild(content);
		content = doc.createTextNode(valueForProperty);
		value.appendChild(content);
		property.appendChild(name);
		property.appendChild(value);
		root.appendChild(property);
		return doc;
	}
}
