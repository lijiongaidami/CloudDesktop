<%@page import="com.hjy.monitor.OnlineClients"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>��������ҳ��</title>
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
	<h1 align="center">��������</h1>
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
		<caption>�����û��б�</caption>
		<tr>
			<th>�û���</th>
			<th>�û�IP</th>
			<th>Զ���˻���</th>
			<th>״̬</th>
		</tr>
		<%=onlineClients%>
	</table>
	<%
		} else {
	%>
	<p>��ǰû�����ߵ��û�</p>
	<%
		}
	%>
	<center>
	<input type="button" id="Monitor" onclick="initMonitorExe();" style="background:#00ff00" value="����û��������">
	<hr>
	</center>
</body>
</html>