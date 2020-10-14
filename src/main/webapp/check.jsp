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
    <c:set var = "bill" scope = "request" value = "0"/>
	<fmt:message key = "messages.label.success" />!
    <hr/>
	<fmt:message key = "messages.label.yourOrder" />: #${sessionScope.checkCode}
    <br>
    --------------------------
    <br>
    <c:forEach var="product" items="${sessionScope.cart}" varStatus="loop">
        ${loop.count}.
        #${product.code}
        <c:choose>
            <c:when test="${language == 'ua'}" >
                ${product.name_UA}
            </c:when>
            <c:otherwise>
                ${product.name}
            </c:otherwise>
        </c:choose>
        ${product.size}
        <br>
        ${product.quantity} * ${product.price}
        = ${product.bill} <fmt:message key = "product.label.currency" />
        <br>
        <c:set var="bill" value="${bill + product.quantity * product.price}" />
    </c:forEach>
    --------------------------
    <br>
    <fmt:message key = "messages.label.sum" /> ${bill} <fmt:message key = "product.label.currency" />
	<c:remove var="bill" scope="request" />
	<c:remove var="checkCode" scope="session" />
	<c:remove var="cart" scope="session" />
	<c:remove var="order" scope="session" />
</body>
</html>
