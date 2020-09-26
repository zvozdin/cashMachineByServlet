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
            <c:when test="${param.role=='CASHIER'}">
                ${param.role}
                <br>
                login:
                <br>
                <select name="login">
                    <c:forEach items="${sessionScope.cashiers}" var="cashier">
                        <option value="${cashier.login}">${cashier.login}</option>
                    </c:forEach>
                </select>
                <br>
                password:
                <br>
                <input type="password" name="password" required />
                <br>
            </c:when>
            <c:when test="${param.role=='COMMODITY_EXPERT'}">
                ${param.role}
                <br>
                login:
                <br>
                <select name="login">
                    <c:forEach items="${sessionScope.commodityExperts}" var="commodityExpert">
                        <option value="${commodityExpert.login}">${commodityExpert.login}</option>
                    </c:forEach>
                </select>
                <br>
                password:
                <br>
                <input type="password" name="password" required />
                <br>
            </c:when>
        </c:choose>
        <br>

        <input type="submit" value="Enter" />
    </form>
</body>
</html>