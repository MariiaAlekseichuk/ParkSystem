<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 15.11.2015
  Time: 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Add New User</title>
</head>
<body>

<form method="post" action='addnewuser' name="frmAddUser"><input
        type="hidden" name="action" value="insert" />
  <p><b>Add New Record</b></p>
  <table>
    <tr>
    <td>Login</td>
    <td><input type="text" name="login" value=""/></td>
  </tr>
    <tr>
    <tr>
      <td>Password</td>
      <td><input type="text" name="password" value=""/></td>
    </tr>
    <tr>
      <td>First Name</td>
      <td><input type="text" name="firstName" value=""/></td>
    </tr>
    <tr>
      <td>Last Name</td>
      <td><input type="text" name="lastName" /></td>
    </tr>
    <tr>
      <td>Email</td>
      <td><input type="text" name="email" /></td>
    </tr>
    <tr>
      <td><input type="submit" value="Submit" /></td>
    </tr>
  </table>
</form>
</body>
</html>
