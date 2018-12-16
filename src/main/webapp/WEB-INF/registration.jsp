<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Registration - AllConferences</title>
    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' />"
          crossorigin="anonymous" rel="stylesheet">
    <link href="<c:url value="/css/index.css" />" rel="stylesheet">
    <link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.csss' />"
          rel="stylesheet">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
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
            <form>
                <div class="form-group">
                    <label for="name"><fmt:message key = "page.message.name"/></label>
                    <input class="form-control item" type="text"
                           id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="surname"><fmt:message key="page.message.surname"/> </label>
                    <input class="form-control" type="text" name="surname" id="surname" required>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="page.message.email" /></label>
                    <input class="form-control item" type="email" id="email" name="email" required>
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

<jsp:include page="/WEB-INF/sections/footer.jsp"/>
</fmt:bundle>

</body>

</html>
