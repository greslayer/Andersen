<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gresl
  Date: 04.01.2021
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee list</title>
</head>
<body>
<center>
    <h1>
        <a href="<c:url value="/list"/>">List all Employees</a>
        &nbsp;&nbsp;&nbsp;
        <a href="<c:url value="/create"/>">Add new Employee</a>
    </h1>
</center>
<table border="1" cellpadding="5">
    <caption>List of</caption>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Salary</th>
    </tr>
    <c:forEach items="${listEmployee}" var="employee">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.salary}</td>
            <td>
                <a href="<c:url value="/edit?id=${employee.id}"/>">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="<c:url value="/delete?id=${employee.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
