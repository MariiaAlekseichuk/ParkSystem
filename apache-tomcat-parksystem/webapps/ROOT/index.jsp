<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Добро пожаловать</title>
    <h2>Система Парк</h2>
</head>
<body>
<form action="/tasks">
    <input name="login" type="text" value="vetka" title="login"/>
    <input name="password" type="password" value="3" title="password"/>

    <input type="submit"value="Enter"/>
</form>
<form action="addNewUser.jsp">
    <input type="submit" value="Registration" />
</form>
</body>
</html>
