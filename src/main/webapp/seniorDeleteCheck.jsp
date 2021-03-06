<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key="role.label.${sessionScope.ROLE}" /></title>
    <link rel="icon" href="data:,">
</head>
<body>
    <a href="mainUser.jsp" ><fmt:message key = "label.main" /></a> |
    <a href="logout" ><fmt:message key = "logout.button.submit" /></a> |
    <a href="?language=en">English</a> |
    <a href="?language=ua">Українська</a>
    <hr color="green"  width="100%" >
    Choose check to delete
    <hr/>
	<c:forEach items="${checks}" var="check">
	    <form action="deleteOrder" method="post">
            <input type="radio" name="check_code" value="${check.checkCode}" required />
            check #${check.checkCode}
            <br>
            <fmt:formatDate type = "date" value = "${check.date}" />
            #${check.login}
            <input type="submit" value="delete order" />
            <hr/>
        </c:forEach>
        <br>
        </form>

    <c:forEach begin="1" end="${maxPageNum}" varStatus="loop" >
        <a href="cancelOrder?page=${loop.count}">${loop.count}</a>
    </c:forEach>
</body>
</html>
