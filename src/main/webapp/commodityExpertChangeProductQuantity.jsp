<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Commodity Expert</title>
<link rel="icon" href="data:,">
</head>
<body>
    <form action="update" method "post">
        <select name="code">
            <c:forEach var="product" items="${sessionScope.products}">
                <option value="${product.code}">
                    ${product.name} ${product.size}
                </option>
            </c:forEach>
        </select>
        = <input type="number" min="0" name="value" />
        <br>
        <br>
        <input type="submit" value="change" />
    </form>
    <br>
	<a href="mainUser.jsp">main</a> <a href="logout">logout</a>
</body>
</html>
