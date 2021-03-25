<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>
<body>
<h1>Sign In</h1>
<form action="/signIn" method="post">
<#--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">-->

    <label>
        <input name="email" type="email" placeholder="EMAIL">
        <input name="password" type="password" placeholder="PASSWORD">
        <input type="checkbox" name="remember-me">Rememcer me
    </label>
    <input type="submit" value="Sign In">
</form>
</body>
</html>