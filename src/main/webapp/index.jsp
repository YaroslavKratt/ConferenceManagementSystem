<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${ empty sessionScope.lang ? 'en' : sessionScope.lang}"/>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Home - AllConferences</title>

    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/index.css' />" rel="stylesheet">



</head>

<body>
<section id="main">
    <div>
        <jsp:include page="WEB-INF/sections/header.jsp"/>
        <div id="promo">
            <div class="jumbotron">
                <h1>Welcome to world best conference agregator</h1>
                <p>Here you will find conference on any topic &nbsp;you need</p>
                <p><a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/${sessionScope.role}/catalog">Learn more</a></p>
            </div>
        </div>
    </div>
</section>
<jsp:include page="WEB-INF/sections/footer.jsp"/>
</body>

</html>
