<!doctype html>
<html lang="en">
<#import "spring.ftl" as spring/>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2><a href="?lang=ru">РУ</a> </h2>
<h2><a href="?lang=en">EN</a> </h2>
<h1><@spring.message 'sign_up_page.registration.title'/></h1>
<@spring.bind "userForm"/>
<#if namesErrorMessage??>
    <p style=".error">${namesErrorMessage}</p>
</#if>
<form action="/signUp" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <label>Имя</label>
    <@spring.formInput "userForm.firstName"/>
    <@spring.showErrors "<br>" "error"/>
    <label>Фамилия</label>
    <@spring.formInput "userForm.lastName"/>
    <@spring.showErrors "<br>" "error"/>
    <label>Email</label>
    <@spring.formInput "userForm.email"/>
    <@spring.showErrors "<br>" "error"/>
    <label for="password">Пароль</label>
    <@spring.formPasswordInput "userForm.password"/>
    <@spring.showErrors "<br>" "error"/>
    <input type="submit" value="Регистрация">

</form>
</body>
</html>
