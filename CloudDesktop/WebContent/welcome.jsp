<%@page import="com.hjy.monitor.Client2VirtualOS"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function goRdesktop() {
		var info = window.document.getElementById("RemoteInfo").childNodes[0].nodeValue;
		alert("��ӭ��¼Զ������" + info);
		try {
			var wsh = new ActiveXObject("wscript.shell");
			wsh.run("file:///C:/Users/JustYoung/Desktop/��������.docx");
		} catch (e) {
			alert(e.message);
			return;
		}
	}
</script>
<title>��ӭʹ��������</title>
</head>
<body>
	<h1 align="center">��ӭʹ��������</h1>
	<%!String username;%>
	<%!String isLogin;%>
	<%
		isLogin = (String) session.getAttribute("isLogin");
		username = (String) session.getAttribute("username");
		if (username != null && isLogin.equals("true")) {
	%>
	<p>
		��ӭ��<%=username%></p>
	<table>
		<tr>
			<td>�������˻��ǣ�</td>
			<td><a id="RemoteInfo" href="welcome.jsp"
				onclick="goRdesktop();return false"><%=Client2VirtualOS.getVirtualOS(username)%></a></td>
		</tr>
		<tr>
			<td>�����������������ն�</td>
			<td><a href="c:" target="_blank">��¼Զ������</a></td>
		</tr>
	</table>
	<%
		} else {
	%>
	<p>��Ǹ������û�е�¼��</p>
	<jsp:forward page="Login.jsp"></jsp:forward>
	<%
		}
	%>

</body>
</html>