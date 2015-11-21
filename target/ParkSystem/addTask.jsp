<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.fn" var="fn"></fmt:message >
    <fmt:message bundle="${loc}" key="local.ln" var="ln"></fmt:message >
    <fmt:message bundle="${loc}" key="local.tasktype" var="tasktype"></fmt:message >
    <fmt:message bundle="${loc}" key="local.tasktext" var="tasktext"></fmt:message >
    <fmt:message bundle="${loc}" key="local.ofsender" var="ofsender"></fmt:message >
    <fmt:message bundle="${loc}" key="local.ofrecipient" var="ofrecipient"></fmt:message >
    <fmt:message bundle="${loc}" key="local.addnewtask" var="addnewtask"></fmt:message >
    <fmt:message bundle="${loc}" key="local.done" var="done"></fmt:message >


    <title>${addnewtask}</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>


<form method="POST" action='\add_task' name="AddTask">
    <p><b>${addnewtask}</b></p>
    <table border="1">

        <tr>
            <td>${fn}${ofsender}</td>
            <td><input type="text" name="firstNameOfSender" value="Иван"/></td>
        </tr>
        <tr>
            <td>${ln}${ofsender}</td>
            <td><input type="text" name="lastNameOfSender" value="Лесной"/></td>
        </tr>
        <tr>
            <td>${tasktype}</td>
            <td>
            <SELECT required NAME="taskType" >
                <OPTION selected>Высадка</OPTION>
                <OPTION>Лечение</OPTION>
                <OPTION>Обработка</OPTION>
            </SELECT>

            </td>
        </tr>
        <tr>
            <td>${tasktext}</td>
            <td><input type="text" name="taskText"/></td>
        </tr>
        <tr>
            <td>${fn}${ofrecipient}</td>
            <td><input type="text" name="firstNameOfRecipient"/></td>
        </tr>
        <tr>
            <td>${ln}${ofrecipient}</td>
            <td><input type="text" name="lastNameOfRecipient"/></td>
        </tr>
    </table>
    <br>

    <input type="hidden" name="action" value="insert"/>
    <input type="submit" value='${done}'/><c:out value='${taskWasAdded}'/>
</form>

</body>
</html>
