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
    <hr/>
	Current Check:
	<br>
    <form action="" method "post">
        <c:forEach var="product" items="${sessionScope.order.products}">
            code #${product.code} ${product.name} Size: ${product.size} Price: ${product.price} Quantity =>
            <input type="number" min="0" max="${product.quantity}" name="${product.code}" value="${product.quantity}" required />
            <br>
        </c:forEach>
        <br>
        <button type="submit" formaction="open">Change check</button>
        <button type="submit" formaction="closeCheck">Close check</button>
    </form>
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>
</body>
</html>
