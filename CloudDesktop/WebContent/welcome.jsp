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
		alert("欢迎登录远程桌面" + info);
		try {
			var wsh = new ActiveXObject("wscript.shell");
			wsh.run("file:///C:/Users/JustYoung/Desktop/技术报告.docx");
		} catch (e) {
			alert(e.message);
			return;
		}
	}
</script>
<title>欢迎使用云桌面</title>
</head>
<body>
	<h1 align="center">欢迎使用云桌面</h1>
	<%!String username;%>
	<%!String isLogin;%>
	<%
		isLogin = (String) session.getAttribute("isLogin");
		username = (String) session.getAttribute("username");
		if (username != null && isLogin.equals("true")) {
	%>
	<p>
		欢迎您<%=username%></p>
	<table>
		<tr>
			<td>您的云账户是：</td>
			<td><a id="RemoteInfo" href="welcome.jsp"
				onclick="goRdesktop();return false"><%=Client2VirtualOS.getVirtualOS(username)%></a></td>
		</tr>
		<tr>
			<td>请点击此链接链接云终端</td>
			<td><a href="c:" target="_blank">登录远程桌面</a></td>
		</tr>
	</table>
	<%
		} else {
	%>
	<p>抱歉，您还没有登录！</p>
	<jsp:forward page="Login.jsp"></jsp:forward>
	<%
		}
	%>

</body>
</html>