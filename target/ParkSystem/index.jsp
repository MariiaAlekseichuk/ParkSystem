<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.namesystem"></fmt:message>
    <fmt:message bundle="${loc}" key="local.loginbutton" var="loginbutton"></fmt:message>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"></fmt:message>
    <fmt:message bundle="${loc}" key="local.updatebutton" var="updatebutton"></fmt:message>
    <fmt:message bundle="${loc}" key="local.password" var="localpassword"></fmt:message>
    <fmt:message bundle="${loc}" key="local.login" var="locallogin"></fmt:message>

    <title><c:out value="${namesystem}"/></title>

    <link rel="stylesheet" type="text/css" href="css/style.css" />

</head>
<body>
<h1><c:out value="${namesystem}"/></h1>

<div align="right">
    <form name="Language choose" action="\index" method="get">
        <SELECT size="2" required name="local">
            <OPTION value="ru">Русский (RU)</OPTION>
            <OPTION value="en">English (EN)</OPTION>
        </SELECT>
        <br>
        <br>
        <input type="submit" value="${updatebutton}"/>
    </form>
</div>


<form action="/index" method="post">
    <input name="login" type="text" value="lesnoi" placeholder="${locallogin}"/>
    <br>
    <br>
    <input name="password" type="password" value="1" placeholder="${localpassword}"/>
    <br>
    <br>

    <input type="submit" value="${loginbutton}"/>

</form>

<br>

<form action="/addnewuser">
    <input type="submit" value="${registration}"/>
</form>

</body>
</html>
