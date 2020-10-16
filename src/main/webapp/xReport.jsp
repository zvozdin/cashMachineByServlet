<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

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

	<c:remove var="xReport" scope="session" />
</body>
</html>
