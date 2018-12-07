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
<jsp:include page="sections/header.jsp"/>
<main class="page login-page">
    <section class="clean-block clean-form dark">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Log In</h2>
            </div>
            <form>
                <div class="form-group"><label for="email" style="color: black;">Email</label><input class="form-control item" type="email" id="email"></div>
                <div class="form-group"><label for="password">Password</label><input class="form-control" type="password" id="password"></div><button class="btn btn-primary btn-block" type="submit">Log In</button></form>
        </div>
    </section>
</main>
<jsp:include page="sections/footer.jsp"/>
</body>

</html>