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
 * 将对XML操作封装在此.
 * 
 * @author hjy
 * 
 */
public class XMLDriver {

	/**
	 * 初始化XML对象，获得对XML操作的Document doc.
	 * 
	 * @param XMLpath
	 * @return 成功返回Document doc，否则返回null;
	 */
	public static Document initialXML(String XMLpath) {
		// 获得XML文件.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("建立DocumentBuilder对象失败，at XMLDriver.java, at initialXML()");
			return null;
		}
		Document doc = null;
		try {
			doc = builder.parse(XMLpath);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			System.out.println("获取doc对象失败，at XMLDriver.java, at initialXML().");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("获取doc对象失败，at XMLDriver.java, at initialXML().");
			e.printStackTrace();
			return null;
		}
		return doc;
	}

	/**
	 * 保存XML对象到文件
	 * 
	 * @param doc
	 *            XML的Document对象
	 * @param path
	 *            需要保存的路径
	 * @return 保存成功返回true，否则返回false.
	 */
	public static boolean saveXML(Document doc, String path) {
		if (doc == null || path == null) {
			System.out.println("doc和path是null,at XMLDriver.java, at saveXML.");
			return false;
		}
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			System.out
					.println("transformer对像创建失败. at XMLDriver.java, at saveXML.");
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
			System.out.println("XML文件保存失败. at XMLDriver.java, at saveXML.");
			return false;
		}
		return true;
	}

	/**
	 * 读取XML信息
	 * 
	 * @param fileName
	 *            读取XML文件的名字
	 * @param tagName
	 *            读取的某个标签
	 * @return 返回XML信息，错误返回null.
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
					.println("DocumentBuilder对象创建失败. at XMLDriver.java, at XmlReader().");
			return null;
		}
		try {
			doc = builder.parse(file);// 获取到XML文件
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("Document对象创建失败. at XMLDriver.java, at XmlReader().");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("Document对象创建失败. at XMLDriver.java, at XmlReader().");
			return null;
		}
		Element root = doc.getDocumentElement();
		NodeList nodelist = root.getElementsByTagName(tagName);
		Element node = (Element) nodelist.item(0);
		return node.getTextContent();
	}

	/**
	 * 专门往xml文件中写入以下格式的内容.
	 * <property>
	 * <name>content</name>
	 * <value>content</value>
	 * </property>
	 * @param doc Document对象
	 * @param nameForProperty name的文本节点
	 * @param valueForProperty value的文本节点
	 * @return 写入成功返回doc Document对象，否则返回null.
	 */
	public static Document setPropertyTag(Document doc, String nameForProperty,
			String valueForProperty) {
		if (doc == null) {
			System.out
					.println("Document对象doc为空！ at XMLDriver.java，at setPropertyTag()");
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
