<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <title>Registration - AllConferences</title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value="/css/index.css" />" rel="stylesheet">
    <fmt:bundle basename="messages">

</head>
<body>
<jsp:include page="sections/header.jsp"/>
<main class="page registration-page">
    <section class="form">
        <div class="container">
            <div class="block-heading">
                <h2 class="text-info"><fmt:message key="page.message.registration"/> </h2>
            </div>
            <form method="post">
                <div class="form-group">
                    <label for="name"><fmt:message key = "page.message.name"/></label>
                    <input class="form-control item" type="text"
                           id="name" name="name" value="${name}" >
                    <p class="text-danger">
                            ${wrongName}
                    </p>
                </div>
                <div class="form-group">
                    <label for="surname"><fmt:message key="page.message.surname"/> </label>
                    <input class="form-control" type="text" name="surname" id="surname" value="${surname}" >
                    <p class="text-danger">
                            ${wrongSurname}
                    </p>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="page.message.email" /></label>
                    <input class="form-control item" type="email" id="email" name="email" value="${email}" }>
                    <p class="text-danger">
                            ${wrongEmail}
                    </p>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="page.message.password"/> </label>
                    <input class="form-control item" type="password" id="password" name="password" required>
                    <p class="text-danger">
                            ${wrongPassword}
                    </p>
                </div>

                <button class="btn btn-primary btn-block" type="submit"><fmt:message key="page.message.sign.up"/> </button>
            </form>
        </div>
    </section>
</main>


</fmt:bundle>

</body>

</html>
