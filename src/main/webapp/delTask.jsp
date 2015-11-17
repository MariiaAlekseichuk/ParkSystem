<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 16.11.2015
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--%@ page pageEncoding="UTF-8" %!-->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Delete task</title>
</head>
<body>

<p><b>Do you want to Delete Task?</b></p>


<form method="POST" action='\delTask' name="DelTask">
    <input type="hidden" name="action" value="delete"/>
    <td><input type="submit" value="Delete"/></td>
</form>
<br>

<form action='/tasks' name="DelTask">
    <td><input type="submit" value="No" ac/></td>
</form>


<c:out value='${taskWasDeleted}'/>
</body>
</html>
