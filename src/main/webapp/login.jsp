<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<form action="login" method="post">
        Welcome:
        <br>
	    <c:forEach items="${roles}" var="role">
            <input type="radio" name="role" value="${role}" />${role}
            <br>
        </c:forEach>
        <br>
        login:
        <br>
        <input type="text" name="login" value="" required />
        <br>
        password:
        <br>
        <input type="password" name="password" required />
        <br>
        <br>

        <input type="submit" value="Login" />
    </form>
</body>
</html>