<%@page import="com.hjy.monitor.OnlineClients"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>云桌面监控页面</title>
<script type="text/javascript">
function initMonitorExe() {
	try {
		var wsh = new ActiveXObject("wscript.shell");
		wsh.run("file:///C:/Users/JustYoung/Desktop/CloudProcessMonitor.exe");
	} catch (e) {
		alert(e.message);
		return;
	}
}
</script>
</head>
<body bgcolor="#87CEFA">
	<h1 align="center">云桌面监控</h1>
	<hr>

	<%!String onlineClients;%>
	<%
		onlineClients = OnlineClients.showOnlineClients(application,
				request);
	%>
	<%
		if (onlineClients != null) {
	%>
	<table border="1" align="center">
		<caption>在线用户列表</caption>
		<tr>
			<th>用户名</th>
			<th>用户IP</th>
			<th>远程账户名</th>
			<th>状态</th>
		</tr>
		<%=onlineClients%>
	</table>
	<%
		} else {
	%>
	<p>当前没有在线的用户</p>
	<%
		}
	%>
	<center>
	<input type="button" id="Monitor" onclick="initMonitorExe();" style="background:#00ff00" value="监控用户在线情况">
	<hr>
	</center>
</body>
</html>