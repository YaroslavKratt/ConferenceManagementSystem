<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>


<html>
<head>
    <title> Catalog - AllConferences</title>

    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">
    <meta charset="UTF-8">


</head>
<jsp:include page="sections/header.jsp"/>
<fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
<fmt:bundle basename="messages">
<body>
<div class="container justify-content-lg-center" id="main">

    <c:forEach items="${requestScope.conferences}" var="conference">
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-5">
                            <h5 class="card-title">${conference.topic}</h5>
                        </div>
                        <div class="col-2">
                            <h5 class="card-title">${conference.location}</h5>
                        </div>
                        <div class="col-2">
                            <h5 class="card-title"> ${conference.dateTime}</h5>
                        </div>
                        <div class="col-3">
                            <div class="row">
                                <c:choose>
                                    <c:when test="${sessionScope.role == 'admin'}">
                                        <div class="col-6">
                                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/deleteconference"
                                                  method="post">
                                                <input type="hidden" name="deleteConference" value="${conference.id}">
                                                <button class="btn btn-danger" type="submit"><fmt:message
                                                        key="page.message.delete"/></button>
                                            </form>
                                        </div>
                                        <div class="col-6">
                                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/editconference"
                                                  method="post">
                                                <button class="btn btn-primary" type="submit"><fmt:message
                                                        key="page.message.edit"/></button>
                                            </form>
                                        </div>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div> <!--/row> -->
                    <c:forEach items="${conference.reports}" var="report">
                        <div class="row">
                            <div class="col-5">
                                <h7 class="card-title">${report.topic}</h7>
                            </div>
                            <div class="col-2">
                                <h7 class="card-title">${report.speakerName} ${report.speakerSurname}</h7>
                            </div>
                            <div class="col-2">
                                    ${report.dateTime}
                            </div>
                            <div class="col-3">
                                <c:choose>
                                    <c:when test="${sessionScope.role=='user' || sessionScope.role =='admin' ||sessionScope.role =='speaker'}">
                                        <td>

                                            <c:choose>
                                                <c:when test="${not fn:contains(requestScope.subscriptions, report.id)}">
                                                    <form method="post"
                                                          action="${pageContext.request.contextPath}/${sessionScope.role}/subscribe">
                                                        <button class="btn btn-primary" type="submit"
                                                                name="reportForSubscription"
                                                                value="${report.id}"><fmt:message
                                                                key="page.message.subscribe"/></button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form method="post"
                                                          action="${pageContext.request.contextPath}/${sessionScope.role}/unsubscribe">

                                                        <button class="btn btn-danger" type="submit"
                                                                name="reportForUnsubscription"
                                                                value="${report.id}"><fmt:message
                                                                key="page.message.unsubscribe"/></button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </c:when>

                                </c:choose>
                            </div>
                        </div>
                        <!--/row-->

            </c:forEach>
                </div>
            </div>
        </div>
    </div>
            </c:forEach>


<div class="container">
    <div class="row">
        <div class="col-12 text-center">
            <c:choose>
                <c:when test="${sessionScope.role == 'admin'}">
                    <form
                            action="${pageContext.request.contextPath}/${sessionScope.role}/createconference"
                            method="post">
                        <button class="btn btn-primary" type="submit"><fmt:message
                                key="page.message.create.conference"/></button>
                    </form>
                </c:when>
            </c:choose>
        </div> <!--col-->
    </div><!--/continer-->
    <nav class="text-center" aria-label="...">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active">
                <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>
</div><!--/container-->
</div>
    </fmt:bundle>
<jsp:include page="sections/footer.jsp"/>
</body>
</html>
