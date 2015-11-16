
<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 14.11.2015
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Задания</title>
</head>
<body>

<form action="/index" method="get">
  <input type="submit" value="Logout">
</form>

<table border="1">
<tr>
  <th>Задания</th>
</tr>
<c:forEach items="${tasks}" var="tasktype">
  <tr>
    <td><c:out value='${tasktype.lastnameOfSender}' />
    </td>
    <td><c:out value='${tasktype.firstnameOfSender}' />
    </td>
    <td><c:out value='${tasktype.tasktype}' />
    </td>
    <td><c:out value='${tasktype.tasktext}' />
    </td>
    <td><c:out value='${tasktype.lastnameOfRecipient}' />
    </td>
    <td><c:out value='${tasktype.firstnameOfRecipient}' />
    </td>
    <td><c:out value='${tasktype.isdone}' />
    </td>
    <td><c:out value='${tasktype.isconfirmed}' />
    </td>
  </tr>
</c:forEach>
</table>
</body>
</html>
