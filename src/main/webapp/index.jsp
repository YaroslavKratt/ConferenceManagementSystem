<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <meta charset="UTF-8" >
    <title>Home - AllConferences</title>

    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/index.css' />" rel="stylesheet">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">

</head>
<body >
<section id="main">
    <div>
        <jsp:include page="WEB-INF/sections/header.jsp"/>
        <div id="promo">
            <div class="jumbotron">
                <h1><fmt:message key="page.message.welcome"/></h1>
                <p><fmt:message key="page.message.any.topic"/></p>
                <p><a class="btn btn-primary" role="button"
                      href="${pageContext.request.contextPath}/${sessionScope.role}/catalog"><fmt:message
                        key="page.message.learn.more"/></a></p>
            </div>
        </div>
    </div>
</section>
</fmt:bundle>
</body>

</html>
