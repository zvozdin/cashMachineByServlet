<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Commodity Expert</title>
<link rel="icon" href="data:,">
</head>
<body>
	Welcome ${sessionScope.LOGIN_USER}
	<br>
	Menu:
	<br>
	<c:forEach items="${sessionScope.COMMODITY_EXPERTactivities}" var="activity">
	    <a href="${activity}">${activity}</a>
        <br>
    </c:forEach>

    <br>
	<a href="logout">logout</a>
</body>
</html>