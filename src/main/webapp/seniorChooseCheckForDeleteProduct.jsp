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
    Choose product to delete
    <hr/>
	<c:forEach items="${checks}" var="check">
	    <form action="deleteProduct" method="post">
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
            <input type="submit" value="delete product" />
	        <hr/>
        </form>
    </c:forEach>

    <c:forEach begin="1" end="${maxPageNum}" varStatus="loop" >
        <a href="cancelProduct?page=${loop.count}">${loop.count}</a>
    </c:forEach>
</body>
</html>
