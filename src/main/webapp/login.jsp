<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <c:set var="role" value="${param.role}" scope="session" />
<head>
<title>Welcome</title>
</head>
<body>
	<form action="login" method="post">
        <c:choose>
            <c:when test="${param.role=='SENIOR_CASHIER'}">
                ${param.role}
                <br>
                login:
                <br>
                <input type="text" name="login" value="admin" required />
                <br>
                password:
                <br>
                <input type="password" name="password" required />
                <br>
            </c:when>
            <c:otherwise>
                ${param.role}
                <br>
                login:
                <br>
                <input type="text" name="login" value="" required/>
                <br>
                password:
                <br>
                <input type="password" name="password" required />
                <br>
            </c:otherwise>
        </c:choose>
        <br>

        <input type="submit" value="Enter" />
    </form>
</body>
</html>