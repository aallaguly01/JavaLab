<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 10/21/2020
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="/users" method="post">
    <input type="text" name="username" />
    <br/>
    <input type="password" name="password" />
    <input type="checkbox" name="remember" value="true" />
    <input type="submit" />
</form>
</body>
</html>
