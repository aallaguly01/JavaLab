<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <h1>${_csrf_token}</h1>
    <h1>Login</h1>
    <form action="/login" method="POST">
        <input type="hidden" value="${_csrf_token}" name="_csrf_token">
        <input type="text" name="email" placeholder="email"></br>
        <input type="password" name="password"  placeholder="password">
        <input type="submit" value="OK">
    </form>
</div>

</body>
</html>
