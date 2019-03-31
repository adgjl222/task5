<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/25
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<head>
    <title>注册页</title>
</head>
<body>
<h2>注册</h2>
<p style="color:red">${msg}</p><hr>
<form  action="${pageContext.request.contextPath}/user/regist" method="post" >

    <br>
    <input name="userName" type="text" placeholder="请输入用户名">
    <br>
    <input name="password1"  type="password" placeholder="请输入密码">
    <br>
    <input name="password2"  type="password" placeholder="请确认密码">
    <br>
    <input type="submit" name="commit" value="注册">
</form >
</body>
</html>
