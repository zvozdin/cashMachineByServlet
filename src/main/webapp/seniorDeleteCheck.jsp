<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>${sessionScope.ROLE}</title>
    <link rel="icon" href="data:,">
</head>
<body>
    Choose check to delete
    <hr/>
	<form action="deleteOrder" method="post">
	    <c:forEach items="${checks}" var="check">
            <input type="radio" name="check_code" value="${check.checkCode}" required />
            check #${check.checkCode}
            <br>
            <fmt:formatDate type = "date" value = "${check.date}" />
            #${check.login}
            <hr/>
        </c:forEach>
        <br>
        <input type="submit" value="delete order" />
    </form>
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>
</body>
</html>