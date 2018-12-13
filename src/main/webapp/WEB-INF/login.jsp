<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">

<head>
    <title>Login Form</title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/index.css' />" rel="stylesheet">

</head>
<meta charset="UTF-8">

<body>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<jsp:include page="sections/header.jsp"/>
<main class="page login-page">
    <section class="form">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info">Log In</h2>
            </div>
            <form method="post">
                <div class="form-group"><label for="email">Email</label>
                    <input class="form-control item" type="email" id="email" name="email" required>
                    <p class="text-danger">
                        ${wrongEmail}
                </div>
                <div class="form-group"><label for="password">Password</label>
                    <input class="form-control" type="password" id="password" name="password" required>
                    <p class="text-danger">
                        ${wrongPassword}
                </div>
                <button class="btn btn-primary" type="submit">Log In</button>
            </form>
        </div>
    </section>
</main>
<jsp:include page="sections/footer.jsp"/>
</body>

</html>