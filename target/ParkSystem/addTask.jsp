<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.tasktype" var="tasktype"></fmt:message>
    <fmt:message bundle="${loc}" key="local.tasktext" var="tasktext"></fmt:message>
    <fmt:message bundle="${loc}" key="local.tasksender" var="tasksender"></fmt:message>
    <fmt:message bundle="${loc}" key="local.recipient" var="recipient"></fmt:message>
    <fmt:message bundle="${loc}" key="local.addnewtask" var="addnewtask"></fmt:message>
    <fmt:message bundle="${loc}" key="local.done" var="done"></fmt:message>
    <fmt:message bundle="${loc}" key="local.back" var="back"></fmt:message>

    <title>${addnewtask}</title>
</head>
<body>

<form method="post" action='\add_task' name="AddTask">
    <p><b>${addnewtask}</b></p>
    <table border="1">

        <tr>
            <td>${tasksender}</td>
            <td>
                <SELECT disabled required NAME="FLNamesSender">
                        <OPTION value='${currentUser.id}'>
                            <c:out value='${currentUser.lasttname}'/>
                            <c:out value='${currentUser.firstname}'/>
                        </OPTION>
                </SELECT>
            </td>
        </tr>
        <tr>
            <td>${tasktype}</td>
            <td>
                <SELECT required contenteditable NAME="taskType">
                    <OPTION >Высадка</OPTION>
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
            <td>${recipient}</td>
            <td><SELECT required NAME="FLNamesRecipient">
                <c:forEach items="${users}" var="users">
                    <OPTION value='${users.id}'>
                        <c:out value='${users.lasttname}'/>
                        <c:out value='${users.firstname}'/>
                    </OPTION>
                </c:forEach>
            </SELECT>
            </td>
        </tr>
    </table>
    <br>

    <input type="hidden" name="action" value="insert"/>
    <input type="submit" value='${done}'/>
</form> <c:out value='${taskWasAdded}'/>
<br>
<form action="/tasks">
    <input type="submit" value="${back}" />
</form>

</body>
</html>
