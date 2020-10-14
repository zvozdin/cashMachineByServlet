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

    <fmt:message key="report.label.${sessionScope.report}" />

</body>
</html>
