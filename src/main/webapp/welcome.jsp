<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <c:set var="ctx" value="${request.contextPath}" />
<head>
<title>Enter</title>
</head>
<body>
	<form action="login.jsp?role=${role}" method="get">
        Roles:
        <br>
	    <c:forEach items="${roles}" var="role">
            <input type="radio" name="role" value="${role}" />${role}
            <br>
        </c:forEach>
        <br>
        <input type="submit" value="Select" />
    </form>
</body>
</html>