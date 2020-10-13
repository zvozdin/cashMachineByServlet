<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>CHECK</title>
<link rel="icon" href="data:,">
</head>
<body>
    <c:set var = "bill" scope = "request" value = "0"/>
	Success!
    <hr/>
	Your order: #${sessionScope.checkCode}
    <br>
    ----------------------
    <br>
    <c:forEach var="product" items="${sessionScope.order.products}" varStatus="loop">
        ${loop.count}. #${product.code} ${product.name} ${product.size}
        <br>
        ${product.quantity} * ${product.price} = ${product.bill}
        <br>
        <c:set var="bill" value="${bill + product.quantity * product.price}" />
    </c:forEach>
    ----------------------
    <br>
    Sum ${bill}
    <br>
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>

	<c:remove var="bill" scope="request" />
	<c:remove var="checkCode" scope="session" />
	<c:remove var="order" scope="session" />
	<c:remove var="cart" scope="session" />
</body>
</html>
