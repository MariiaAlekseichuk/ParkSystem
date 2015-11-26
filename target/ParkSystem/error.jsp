<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.nologin" var="nologin"></fmt:message>
    <fmt:message bundle="${loc}" key="local.back" var="back"></fmt:message>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<br>

<div align="center">
    <c:out value="${nologin}"/>


    <form action="/index">
        <input type="submit" value="${back}">
    </form>

</div>
</body>
</html>
