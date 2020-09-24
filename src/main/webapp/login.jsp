<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <c:set var="role" value="${param.role}" scope="session" />
<head>
<title>Welcome</title>
</head>
<body>
	<form action="login" method="post">
        <c:choose>
            <c:when test="${param.role=='SENIOR_CASHIER'}">
                ${param.role} password:
                <br>
                <input type="password" name="password">
                <br>
            </c:when>
            <c:otherwise>
                ${param.role} login:
                <br>
                <input type="text" name="login" value=""/>
                <br>
                Password:
                <br>
                <input type="password" name="password">
                <br>
            </c:otherwise>
        </c:choose>
        <br>

        <input type="submit" value="Enter" />
    </form>
</body>
</html>