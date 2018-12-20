<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title><fmt:message key="page.message.login"/></title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/index.css' />" rel="stylesheet">
    <meta charset="UTF-8" >



</head>

<body >

<jsp:include page="sections/header.jsp"/>
<main class="page login-page">
    <section class="form">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info"><fmt:message key="page.message.login"/></h2>
            </div>
            <form method="post">
                <div class="form-group"><label for="email"><fmt:message key="page.message.email"/> </label>
                    <input class="form-control item" type="email" id="email" name="email" required>
                    <p class="text-danger">
                            ${wrongEmail}
                </div>
                <div class="form-group"><label for="password"><fmt:message key="page.message.password"/></label>
                    <input class="form-control" type="password" id="password" name="password" required>
                    <p class="text-danger">
                            ${wrongPassword}
                </div>
                <button class="btn btn-primary" type="submit"><fmt:message key="page.message.login"/></button>
            </form>
        </div>
    </section>
</main>
<jsp:include page="sections/footer.jsp"/>
</fmt:bundle>

</body>
</html>