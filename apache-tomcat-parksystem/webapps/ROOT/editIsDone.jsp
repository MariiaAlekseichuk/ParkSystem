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
    <fmt:message bundle="${loc}" key="local.edittask" var="edittask"></fmt:message >
    <fmt:message bundle="${loc}" key="local.status" var="status"></fmt:message >
    <fmt:message bundle="${loc}" key="local.checked" var="checked"></fmt:message >
    <fmt:message bundle="${loc}" key="local.updatebutton" var="updatebutton"></fmt:message >
    <fmt:message bundle="${loc}" key="local.isdone" var="isdone"></fmt:message>
    <fmt:message bundle="${loc}" key="local.tasksender" var="tasksender"></fmt:message>
    <fmt:message bundle="${loc}" key="local.recipient" var="recipient"></fmt:message>


    <title>${edittask}</title>
</head>
<body>

<form method="POST" action='/edit_is_task_done' name="EditTask">
<table border="1">

    <tr>
        <td>${tasksender}</td>
        <td><input type="text" name="firstnameOfSender" value='${task.firstnameOfSender} ${task.lastnameOfSender}' disabled/></td>
    </tr>
    <tr>
        <td>${tasktype}</td>
        <td><input type="text" name="tasktype" value='${task.tasktype}'  disabled/></td>
    </tr>
    <tr>
        <td>${tasktext}</td>
        <td><input type="text" name="tasktext" value='${task.tasktext}'  disabled/></td>
    </tr>

    <tr>
        <td>${recipient}</td>
        <td><input type="text" name="firstnameOfRecipient" value='${task.firstnameOfRecipient} ${task.lastnameOfRecipient}' disabled/></td>
    </tr>
    <tr>
        <td>${isdone}</td>
        <td>
            <SELECT required contenteditable NAME="isdone">
                <OPTION selected>${task.isdone}</OPTION>
                <OPTION>Выполнено</OPTION>
                <OPTION>Не выполнено</OPTION>
            </SELECT>
        </td>
    </tr>
    <tr>
        <td>${checked}</td>
        <td><input type="text" name="isconfirmed" value='${task.isconfirmed}'  disabled/></td>
    </tr>
</table>



<br>

    <input type="hidden" name="action" value="update"/>
    <td><input type="submit" value="${updatebutton}"/></td>
    <c:out value='${taskWasEdited}'/>
</form>


</body>
</html>

