<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>${sessionScope.ROLE}</title>
    <link rel="icon" href="data:,">
</head>
<body>
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
            <c:forEach items="${sessionScope.products}" var="product">
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
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>
</body>
</html>
