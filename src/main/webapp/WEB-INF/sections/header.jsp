<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang: pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<html lang="${lang}">
<head>
    <meta charset="UTF-8" >

</head>
<body>


<fmt:bundle basename="messages">
    <c:choose>
        <c:when test="${sessionScope.role == 'guest'}">
            <nav class="navbar navbar-light navbar-expand-md">
                <div class="container-fluid"><a class="navbar-brand" href="/">AllConferences</a>
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                            class="navbar-toggler-icon"></span></button>
                    <div
                            class="collapse navbar-collapse float-right">
                        <ul class="nav navbar-nav float-right ml-auto">
                            <li class="nav-item" role="presentation"><a class="nav-link"
                                                                        href="${pageContext.request.contextPath}/guest/login"><fmt:message
                                    key="page.message.button.login"/> </a>
                            </li>
                            <li class="nav-item" role="presentation"><a class="nav-link float-right"
                                                                        href="${pageContext.request.contextPath}/guest/registration"><fmt:message
                                    key="page.message.button.registration"/></a>
                            </li>
                            <li>
                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/changelanguage"
                                      class="form-horizontal" method="post">
                                    <select class="form-control" name="lang" onchange="submit()">
                                        <option value="en_US" ${sessionScope.lang == 'en_US' ? 'selected' : ''}> EN
                                        </option>
                                        <option value="uk_UA" ${sessionScope.lang == 'uk_UA' ? 'selected' : ''}> UA
                                        </option>
                                    </select>
                                </form>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </c:when>
        <c:otherwise>

            <nav class="navbar navbar-light navbar-expand-md">

                <div class="container-fluid"><a class="navbar-brand" href="/">AllConferences</a>
                    <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span
                            class="sr-only"></span><span
                            class="navbar-toggler-icon"></span></button>

                    <div class="collapse navbar-collapse float-right">
                        <ul class="nav navbar-nav  float-right ml-auto">

                            <li class="nav-item" role="presentation"><a class="nav-link nav float-right"
                                                                        href="${pageContext.request.contextPath}/${sessionScope.role}/logout"><fmt:message
                                    key="page.message.button.logout"/></a>
                            </li>
                            <li>
                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/changelanguage"
                                      class="form-horizontal" method="post">
                                    <select class="form-control" name="lang" onchange="submit()">
                                        <option value="en_US" ${sessionScope.lang == 'en_US' ? 'selected' : ''}> EN
                                        </option>
                                        <option value="uk_UA" ${sessionScope.lang == 'uk_UA' ? 'selected' : ''}> UA
                                        </option>
                                    </select>
                                </form>
                            </li>
                        </ul>


                    </div>

                </div>


            </nav>

        </c:otherwise>
    </c:choose>
</fmt:bundle>
</body>
</html>
