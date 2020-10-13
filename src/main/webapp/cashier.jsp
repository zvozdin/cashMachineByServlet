<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>${sessionScope.ROLE}</title>
<link rel="icon" href="data:," >
</head>
<body>
	Products:
	<hr/>
    <form action="cart" method "post" >
        <c:forEach var="product" items="${sessionScope.products}" varStatus="loop" >
            code #${product.code} ${product.name} Size: ${product.size} Price: ${product.price} Quantity =>
            <input type="number" min="1" max="${product.quantity}" name="${product.code}" />
            In stock [${product.quantity}]
            <br>
        </c:forEach>
        <br>
        <input type="submit" value="into cart" />
    </form>
    <br>
	<a href="mainUser.jsp" >main</a> <a href="logout" >logout</a>
</body>
</html>
