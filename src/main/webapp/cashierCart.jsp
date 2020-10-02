<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>CASHIER</title>
<link rel="icon" href="data:,">
</head>
<body>
	Products:
	<br>
    <form action="" method "post">
            <c:forEach var="product" items="${sessionScope.order.products}">
                code #${product.code} ${product.name} Size: ${product.size} Price: ${product.price} Quantity =>
                <input type="number" min="0" name="${product.code}" value="${product.quantity}" />
                <br>
            </c:forEach>

        <br>
        <button type="submit" formaction="open">Change check</button>
        <button type="submit" formaction="closeCheckCashier">Close check</button>
    </form>
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>
</body>
</html>
