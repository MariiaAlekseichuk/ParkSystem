<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 14.11.2015
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Задания</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Задания</th>
    </tr>
    <c:forEach items="${tasks}" var="tasktype">
    <p><input type="hidden" size="25" name="taskid" value='${tasktype.taskid}'>
        <tr>
            <td><c:out value='${tasktype.lastnameOfSender}'/>
            </td>
            <td><c:out value='${tasktype.firstnameOfSender}'/>
            </td>
            <td><c:out value='${tasktype.tasktype}'/>
            </td>
            <td><c:out value='${tasktype.tasktext}'/>
            </td>
            <td><c:out value='${tasktype.lastnameOfRecipient}'/>
            </td>
            <td><c:out value='${tasktype.firstnameOfRecipient}'/>
            </td>
            <td><c:out value='${tasktype.isdone}'/>
            </td>
            <td><c:out value='${tasktype.isconfirmed}'/>
            </td>
            <td><c:out value='${tasktype.isconfirmed}'/>
            </td>
            <td>
            <a href="/editTask?id=${tasktype.taskid}">Edit</a>
        </td>
            <td>
                <!--form method="POST" action='/delTask' name="DelTask"!-->
                <a href="/delTask?id=${tasktype.taskid}">Delete</a>
                </form>
            </td>

        </tr>
        </c:forEach>
</table>
<br>
<br>
<form action="addTask.jsp">
    <input type="submit" value="Add"/>
</form>
</body>
</html>
