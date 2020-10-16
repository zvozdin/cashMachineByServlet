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
        <form action="insert" method="post" >
            <table border="1" cellpadding="5" >
                <caption><h2>Products</h2></caption>
                <tr>
                    <th>code</th>
                    <th>name</th>
                    <th>name_UA</th>
                    <th>size</th>
                    <th>price</th>
                    <th>quantity</th>
                </tr>
                <tr>
                    <td><input type="text" name="code" pattern="[a-zA-Z0-9\S]{1,15}" required /></td>
                    <td><input type="text" name="name" pattern="[a-zA-Z\S]{1,15}" required /></td>
                    <td><input type="text" name="name_UA" pattern="[а-яА-ЯїЇіІєЄ^И\S]{1,15}" required /></td>
                    <td><input type="text" name="size" pattern="[SML]{1,}" required /></td>
                    <td><input type="number" name="price" pattern="\d+\.?\d*" required /></td>
                    <td><input type="number" name="quantity" min="0" required /></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="add" />
        </form>
    </body>
</html>
