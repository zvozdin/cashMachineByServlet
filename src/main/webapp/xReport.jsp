<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html>
<head>
<title>xReport</title>
<link rel="icon" href="data:,">
</head>
<body>
	X Report!
    <hr/>
    Date <fmt:formatDate type = "date" value = "${now}" />
    <br>
	Till <fmt:formatDate type = "time" value = "${now}" />
    <hr/>
    Checks count => ${sessionScope.xReport.checksCount}
    <br>
    Common product quantity => ${sessionScope.xReport.quantity}
    <br>
    Money at the cash machine => ${sessionScope.xReport.bill}
    <hr/>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>

	<c:remove var="xReport" scope="session" />
</body>
</html>
