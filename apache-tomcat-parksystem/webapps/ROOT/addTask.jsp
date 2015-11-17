<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 16.11.2015
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--%@ page pageEncoding="UTF-8" %!-->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add new task</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
%>

<SELECT NAME="select1" >
    <OPTION>Option 1</OPTION>
    <OPTION selected><input type="text" name="tasktype" value='${task.tasktype}'/></OPTION>
    <OPTION>Option 3</OPTION>
    <OPTION>Option 4</OPTION>
    <OPTION>Option 5</OPTION>
</SELECT>
<form method="POST" action='/addTask' name="AddTask">
    <p><b>Add New Task</b></p>
    <table border="1">

        <tr>
            <td>firstnameOfSender</td>
            <td><input type="text" name="firstNameOfSender" value="Иван"/></td>
        </tr>
        <tr>
            <td>lastnameOfSender</td>
            <td><input type="text" name="lastNameOfSender" value="Лесной"/></td>
        </tr>
        <tr>
            <td>tasktype</td>
            <td><input type="text" name="taskType"/></td>
        </tr>
        <tr>
            <td>tasktext</td>
            <td><input type="text" name="taskText"/></td>
        </tr>
        <tr>
            <td>firstnameOfRecipient</td>
            <td><input type="text" name="firstNameOfRecipient"/></td>
        </tr>
        <tr>
            <td>lastnameOfRecipient</td>
            <td><input type="text" name="lastNameOfRecipient"/></td>
        </tr>
    </table>
    <br>

    <input type="hidden" name="action" value="insert"/>
    <input type="submit" value="Add"/>
</form>
<c:out value='${taskWasAdded}'/>
</body>
</html>
