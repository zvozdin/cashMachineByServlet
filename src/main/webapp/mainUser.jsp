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
    <form>
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>English</option>
            <option value="ua" ${sessionScope.language == 'ua' ? 'selected' : ''}>Українська</option>
        </select>
    </form>
    <hr/>
	<fmt:message key = "label.welcome" /> ${sessionScope.user.login}
	<hr/>
	<fmt:message key = "label.menu" />:
	<br>
	<c:forEach items="${sessionScope.activities}" var="activity">
	    <a href="${activity}">
	        <fmt:message key = "activity.label.${activity}" />
	    </a>
        <br>
    </c:forEach>
    <br>
	<a href="logout">
	    <fmt:message key = "logout.button.submit" />
	</a>
</body>
</html>
