<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>${sessionScope.ROLE}</title>
<link rel="icon" href="data:,">
</head>
<body>
	Welcome ${sessionScope.user.login}
	<hr/>
	Menu:
	<br>
	<br>
	<c:forEach items="${sessionScope.activities}" var="activity">
	    <a href="${activity}">${activity}</a>
        <br>
    </c:forEach>
    <br>
	<a href="logout">logout</a>
</body>
</html>
