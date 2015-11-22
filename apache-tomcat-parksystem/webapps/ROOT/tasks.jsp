<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.yourtasks" var="yourtasks"></fmt:message >
    <fmt:message bundle="${loc}" key="local.editdonebutton" var="editdonebutton"></fmt:message >
    <fmt:message bundle="${loc}" key="local.delbutton" var="delbutton"></fmt:message >
    <fmt:message bundle="${loc}" key="local.editbutton" var="editbutton"></fmt:message >
    <fmt:message bundle="${loc}" key="local.addtaskbutton" var="addtaskbutton"></fmt:message >
    <fmt:message bundle="${loc}" key="local.seeallusers" var="seeallusers"></fmt:message >

    <fmt:message bundle="${loc}" key="local.tasksender" var="tasksender"></fmt:message >
    <fmt:message bundle="${loc}" key="local.tasktype" var="tasktype"></fmt:message >
    <fmt:message bundle="${loc}" key="local.tasktext" var="tasktext"></fmt:message >
    <fmt:message bundle="${loc}" key="local.recipient" var="recipient"></fmt:message >
    <fmt:message bundle="${loc}" key="local.isdone" var="isdone"></fmt:message >
    <fmt:message bundle="${loc}" key="local.isconfirmed" var="isconfirmed"></fmt:message >
    <fmt:message bundle="${loc}" key="local.logoutbutton" var="logoutbutton"></fmt:message >


</head>
<body>
<p><c:out value='${yourtasks}'/></p>

<div align="right">
<form action="logout.jsp">
    <input type="submit" value="${logoutbutton}"/>
</form>
</div>

<table border="1">
    <tr>
        <td align="center" colspan="2">
            <strong>${tasksender}</strong></td>
        <td align="center">
            <strong>${tasktype}</strong>
        </td>
        <td align="center">
            <strong>${tasktext}</strong>
        </td>
        <td align="center" colspan="2">
            <strong>${recipient}</strong>
        </td>
        <td align="center">
            <strong>${isdone}</strong>
        </td>
        <td align="center">
            <strong>${isconfirmed}</strong>
        </td>
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
                <c:if test='${groupid==1}'>
            <td>
                    <a href="\edit_task?id=${tasktype.taskid}">${editbutton}</a>
            </td>
                </c:if>

                <c:if test='${groupid==1}'>
             <td>
                    <a href="\delete_task?id=${tasktype.taskid}">${delbutton}</a>
             </td>
                 </c:if>
                <c:if test='${groupid==2}'>
             <td>
                    <a href="\edit_is_task_done?id=${tasktype.taskid}">${editdonebutton}</a>
              </td>
                </c:if>

        </tr>
        </c:forEach>
</table>
<br>
<br>

<form action="\add_task">
 <c:if test='${groupid==1}'>
        <input type="submit" value=${addtaskbutton} style='display:table-cell'/>

</form>

<br>
<a href="\users">${seeallusers}</a>
 </c:if>
</body>
</html>
