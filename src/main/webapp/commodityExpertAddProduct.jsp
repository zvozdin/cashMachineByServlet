<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Commodity Expert</title>
<link rel="icon" href="data:,">
</head>
    <body>
        <form action="insert" method="post" >
            <table border="1" cellpadding="5" >
                <caption><h2>Products</h2></caption>
                <tr>
                    <c:forEach items="${sessionScope.columns}" var="name" >
                        <td style="text-align:center" >${name}</td>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach items="${sessionScope.columns}" var="name" >
                        <td><input type="text" name="${name}" required /></td>
                    </c:forEach>
                </tr>
            </table>
            <br>
            <input type="submit" value="add" />
        </form>

    <br>
    <a href="mainUser.jsp">main</a> <a href="logout">logout</a>
    </body>
</html>
