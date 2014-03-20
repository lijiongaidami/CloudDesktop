<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>远程桌面登陆</title>
</head>
<body bgcolor="#87CEFA">
<h1 align="center">远程桌面登陆</h1>
<hr>

<form name="LoginForm" action="LoginServlet" method="post">
<table align="center">
<tr>
<td>用户名：</td><td><input type="text" name="userName"></td>
</tr>
<tr>
<td>密码：</td><td><input type="password" name="password"></td>
</tr>
</table>
<center><input type="submit" value="提交" align="middle"></center>
</form>

</body>
</html>