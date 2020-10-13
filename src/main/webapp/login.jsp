<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="${sessionScope.language}">
<head>
    <title><fmt:message key = "label.welcome" /></title>
    <link rel="icon" href="data:,">
</head>
<body>
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>English</option>
            <option value="ua" ${sessionScope.language == 'ua' ? 'selected' : ''}>Українська</option>
        </select>
    </form>
    <hr/>

    <fmt:message key = "label.welcome" />
	<form action="login" method="post">
	    <c:forEach items="${roles}" var="role">
            <input type="radio" name="role" value="${role}" required />
            <fmt:message key="role.label.${role}" />
            <br>
        </c:forEach>
        <fmt:message key = "login.label.username" />:
        <br>
        <input type="text" name="login" value="" required />
        <br>
        <fmt:message key = "login.label.password" />:
        <br>
        <input type="password" name="password" required />
        <br>
        <br>
        <fmt:message key="login.button.submit" var="buttonValue" />
        <input type="submit" value="${buttonValue}" />
    </form>
</body>
</html>
