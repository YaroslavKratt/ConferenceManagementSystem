<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${ empty sessionScope.lang ? 'en' : sessionScope.lang}"/>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Home - AllConferences</title>
    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' />" crossorigin="anonymous" rel="stylesheet">

    <link href="<c:url value='css/index.css' />" rel="stylesheet">
    <link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.csss' />" rel="stylesheet">


</head>

<body>
<section id="main">
    <div>
        <jsp:include page="WEB-INF/sections/header.jsp"/>
        <div id="promo">
            <div class="jumbotron">
                <h1>Welcome to world best conference agregator</h1>
                <p>Here you will find conference on any topic &nbsp;you need</p>
                <p><a class="btn btn-primary" role="button" href="/conferences">Learn more</a></p>
            </div>
        </div>
    </div>
</section>
<jsp:include page="WEB-INF/sections/footer.jsp"/>
</body>

</html>
