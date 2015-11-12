<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 09.11.2015
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
  <title></title>
</head>
<body>
<%--= request.getAttribute("userList") --%>
<table class="item-table">
  <tr>
    <th>Name</th>
  </tr>

    <tr>
      <td>111</td>
      <c:out value='${userList}' />
      <%-- td>${userList}</td --%>
    </tr>

</table>

</body>
</html>

