<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: gresl
  Date: 05.01.2021
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center;">
    <h1>
        <a href="<c:url value="/create"/>">Add New Employee</a>
        &nbsp;&nbsp;&nbsp;
        <a href="<c:url value="/list"/>">List All Employees</a>

    </h1>
</div>
<div align="center">
    <c:if test="${employee != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${employee == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${employee != null}">
                            Edit Employee
                        </c:if>
                        <c:if test="${employee == null}">
                            Add New Employee
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${employee != null}">
                    <input type="hidden" name="id" value="<c:out value='${employee.id}' />"/>
                </c:if>

                <tr>
                    <th>First Name:</th>
                    <td>
                        <input type="text" name="firstName" size="45"
                               value="<c:out value='${employee.firstName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Last Name:</th>
                    <td>
                        <input type="text" name="lastName" size="45"
                               value="<c:out value='${employee.lastName}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Salary:</th>
                    <td>
                        <input type="text" name="salary" size="5"
                               value="<c:out value='${employee.salary}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>
                        <c:if test="${employee != null}">
                        <input type="date" name="fromDate" value=""/>
                    </th>
                    <td>

                            <input type="date" name="toDate" value=""/>
                        </c:if>
                    </td>
                </tr>
                <c:forEach items="${employee.vacations}" var="vacation">
                    <tr>
                        <td>${vacation.fromDate}</td>
                        <td>${vacation.toDate}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
