<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.usersinfo" var="usersinfo"></fmt:message>

    <h1 align="center"><title>${usersinfo}</title></h1>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div align="center">
    <table border="1">
        <tr>
            <th>${usersinfo}</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value='${user}'/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

