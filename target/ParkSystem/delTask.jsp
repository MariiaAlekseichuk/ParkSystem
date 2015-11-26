<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.fn" var="fn"></fmt:message>
    <fmt:message bundle="${loc}" key="local.ln" var="ln"></fmt:message>
    <fmt:message bundle="${loc}" key="local.deltask" var="deltask"></fmt:message>
    <fmt:message bundle="${loc}" key="local.deltaskquestion" var="deltaskquestion"></fmt:message>
    <fmt:message bundle="${loc}" key="local.delbutton" var="delbutton"></fmt:message>
    <fmt:message bundle="${loc}" key="local.no" var="no"></fmt:message>


    <title>${deltask}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>

<h1 align="center"><p><b>${deltaskquestion}</b></p></h1>

<div align="center">
    <form method="POST" action='\delete_task' name="DelTask">
        <input type="hidden" name="action" value="delete"/>
        <td><input type="submit" value=${delbutton}></td>
        <c:out value='${taskWasDeleted}'/>
    </form>
    <br>

    <INPUT type=button value="${no}" onClick="history.back();">
</div>
</body>
</html>
