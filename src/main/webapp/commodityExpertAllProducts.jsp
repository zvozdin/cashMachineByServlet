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
        <table border="1" cellpadding="5" >
            <caption><h2>Products</h2></caption>
            <tr>
                <th>id</th>
                <th>code</th>
                <th>name</th>
                <th>name_UA</th>
                <th>size</th>
                <th>price</th>
                <th>quantity</th>
            </tr>
            <c:forEach items="${sessionScope.allProducts}" var="product">
                <tr>
                    <td style="text-align:center" >${product.id}</td>
                    <td style="text-align:center" >${product.code}</td>
                    <td style="text-align:center" >${product.name}</td>
                    <td style="text-align:center" >${product.name_UA}</td>
                    <td style="text-align:center" >${product.size}</td>
                    <td style="text-align:center" >${product.price}</td>
                    <td style="text-align:center" >${product.quantity}</td>
                </tr>
            </c:forEach>
        </table>
</body>
</html>
