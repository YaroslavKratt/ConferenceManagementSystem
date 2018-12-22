<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' />"
          crossorigin="anonymous" rel="stylesheet">
    <link href="<c:url value="/css/error.css" />" rel="stylesheet">
    <link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.csss' />"
          rel="stylesheet">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
</head>
<body>
<div class="container  justify-content-center  vertical-center">
    <div class="jumbotron text-center ">
        <h1 class="display-4"><fmt:message key="page.error.oops"/> </h1>
        <p class="lead"> <fmt:message key="page.error.not.found"/> </p>
        <a class="btn btn-primary btn-lg" href="/" role="button"><fmt:message key="page.message.go.home"/> </a>
        </p>
    </div><!--jumbotron-->
</div><!--container-->
</body>
</fmt:bundle>
</html>

