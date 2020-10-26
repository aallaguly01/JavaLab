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

    <label value="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>
    <br></br>
    <label value="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>

    <button type="submit">Sign Up</button>
</form>
</body>
</html>
