<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: kratt
  Date: 29.11.18
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" >

<head>
    <meta charset="UTF-8">
    <title>Login Form</title>

</head>

<body>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>

<div class="login">
    <h1>Login</h1>
    <form method="post">
        <input type="text" name="email" placeholder="Email" required="required" />
        <input type="password" name="password" placeholder="Password" required="required" />
        <button type="submit">Let me in.</button>
    </form>
</div>
</body>

</html>