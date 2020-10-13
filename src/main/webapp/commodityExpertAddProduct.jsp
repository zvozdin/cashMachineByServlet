<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>${sessionScope.ROLE}</title>
<link rel="icon" href="data:,">
</head>
    <body>
        <form action="insert" method="post" >
            <table border="1" cellpadding="5" >
                <caption><h2>Products</h2></caption>
                <tr>
                    <th>code</th>
                    <th>name</th>
                    <th>size</th>
                    <th>price</th>
                    <th>quantity</th>
                </tr>
                <tr>
                    <td><input type="text" name="code" pattern="[a-zA-Z0-9\S]{1,15}" required /></td>
                    <td><input type="text" name="name" pattern="[a-zA-Z\S]{1,15}" required /></td>
                    <td><input type="text" name="size" pattern="[SML]{1,}" required /></td>
                    <td><input type="number" name="price" pattern="\d+\.?\d*" required /></td>
                    <td><input type="number" name="quantity" min="0" required /></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="add" />
        </form>
    <br>
    <a href="mainUser.jsp">main</a> <a href="logout">logout</a>
    </body>
</html>
