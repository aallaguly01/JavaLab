<html lang="en">
<body>


<form method="${htmlFormMethod}" action="${htmlFormAction}">
    <#list htmlInput as htmlInputs>
        <input type="${htmlInputs.type}" name="${htmlInputs.name}" placeholder="htmlInputs.placeholder">
    </#list>
</form>
</body>
</html>