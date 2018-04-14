<%--
  Created by IntelliJ IDEA.
  User: venedikttsulenev
  Date: 13/04/2018
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h3>Register</h3>
<hr/>
<form name="registerForm" method="POST" action="login">
    <input type="hidden" name="command" value="register"/>
    Login:<br/>
    <input type="text" name="login" value=""><br/>
    Password:<br/>
    <input type="password" name="password" value="">
    <%-- TODO: "Re-enter password" field--%>
    <br/>
    <input type="submit" value="Enter">
</form>
<hr/>
</body>
</html>
