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
    Choose product to delete
    <hr/>
	<form action="deleteProduct" method="post">
	    <c:forEach items="${checks}" var="check">
            check #${check.checkCode}
            <br>
            <fmt:formatDate type = "date" value = "${check.date}" />
            <br>
            <c:forEach var="product" items="${check.order.products}" varStatus="loop">
                <input type="radio" name="productIdAndCheckCode" value="${product.id}|${check.checkCode}" required />
                ${loop.count}. #${product.code} ${product.name} ${product.size}
                ${product.quantity} * ${product.price} = ${product.bill}
                <br>
            </c:forEach>
	        <hr/>
        </c:forEach>
        <br>
        <input type="submit" value="delete product" />
    </form>
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>
</body>
</html>
