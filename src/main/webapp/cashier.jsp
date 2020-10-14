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
    <fmt:message key = "product.label.products" />:
	<hr/>
    <form action="cart" method "post" >
        <c:forEach var="product" items="${sessionScope.products}" varStatus="loop" >
            <fmt:message key = "product.label.code" /> #${product.code} <br>
            <fmt:message key = "product.label.name" />
            <c:choose>
                <c:when test="${language == 'ua'}" >
                    ${product.name_UA}
                </c:when>
                <c:otherwise>
                    ${product.name}
                </c:otherwise>
            </c:choose>
            <br>
            <fmt:message key = "product.label.size" /> ${product.size} <br>
            <fmt:message key = "product.label.price" /> ${product.price} <fmt:message key = "product.label.currency" /> <br>
            <fmt:message key = "product.label.quantity" />
            <input type="number" min="1" max="${product.quantity}" name="${product.code}" /> <br>
            <fmt:message key = "product.label.inStock" /> [${product.quantity}]
	        <hr/>
        </c:forEach>
        <br>
        <fmt:message key="cart.button.submit" var="buttonValue" />
        <input type="submit" value="${buttonValue}" />
    </form>
</body>
</html>
