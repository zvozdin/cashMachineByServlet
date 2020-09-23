<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="messages" />

<html lang="${param.lang}">
<head>
<title>PhraseApp - i18n</title>
</head>
<body>
	<h2>
        <fmt:message key="label.hello" />
    </h2>

	<ul>
		<li><a href="?lang=en">english</a></li>
		<li><a href="?lang=ua">українська</a></li>
	</ul>
</body>
</html>