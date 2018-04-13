<%--
  Created by IntelliJ IDEA.
  User: matty
  Date: 11.04.2018
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;
charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html><head><title>Login</title></head>
<body><h3>Login</h3>
<hr/>
<form name="loginForm" method="POST"
      action="controller">
    <input type="hidden" name="command" value="login" />
    Login:<br/>
    <input type="text" name="login" value=""><br/>
    Password:<br/>
    <input type="password" name="password" value="">
    <br/>
    <input type="submit" value="Enter">
</form><hr/>
</body></html>
