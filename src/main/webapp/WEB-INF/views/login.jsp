<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/25
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
</head>
<body>
<h2>登录</h2>
<p style="color:red">${msg}</p><hr>
<form  action="${pageContext.request.contextPath}/user/logintoken" method="post" >

    <br>
    <input name="userName" type="text" placeholder="请输入用户名">
    <br>
    <input name="userPassword"  type="password" placeholder="请输入密码">
    <br>
    <input type="submit" name="commit" value="登录">
</form >
</body>
</html>
