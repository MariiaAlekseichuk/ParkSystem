<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 16.11.2015
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Edit task</title>
</head>
<body>

<form method="POST" action='\editTask' name="EditTask">
<table border="1">

    <tr>
        <td>firstnameOfSender</td>
        <td><input type="text" name="firstnameOfSender" value='${task.firstnameOfSender}'/></td>
    </tr>
    <tr>
        <td>lastnameOfSender</td>
        <td><input type="text" name="lastnameOfSender" value='${task.lastnameOfSender}'/></td>
    </tr>
    <tr>
        <td>tasktype</td>
        <td><input type="text" name="tasktype" value='${task.tasktype}'/></td>
    </tr>
    <tr>
        <td>tasktext</td>
        <td><input type="text" name="tasktext" value='${task.tasktext}'/></td>
    </tr>
    <tr>
        <td>firstnameOfRecipient</td>
        <td><input type="text" name="firstnameOfRecipient" value='${task.firstnameOfRecipient}'/></td>
    </tr>
    <tr>
    <td>lastnameOfRecipient</td>
    <td><input type="text" name="lastnameOfRecipient" value='${task.lastnameOfRecipient}'/></td>
</tr>
    <tr>
        <td>isDone</td>
        <td><input type="text" name="isdone" value='${task.isdone}'/></td>
    </tr>
    <tr>
        <td>isConfirmed</td>
        <td><input type="text" name="isconfirmed" value='${task.isconfirmed}'/></td>
    </tr>
</table>



<br>

    <input type="hidden" name="action" value="update"/>
    <td><input type="submit" value="Update"/></td>
</form>


<c:out value='${taskWasEdited}'/>
</body>
</html>

