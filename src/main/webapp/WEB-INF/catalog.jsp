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
<div class="container">
    <div class="row">
        <form action="${pageContext.request.contextPath}/${sessionScope.role}/catalog" onchange="submit()">
            <input type="hidden" name="currentPage" value="1">
            <label for="records"><fmt:message key="page.message.records.per.page"/> </label>
            <select class="form-control" id="records" name="recordsPerPage">
                <c:choose>
                    <c:when test="${paginationParameters.recordsPerPage==5}">
                        <option value="5" selected>5</option>
                    </c:when>
                    <c:otherwise>
                        <option value="5">5</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${paginationParameters.recordsPerPage==10}">
                        <option value="10" selected>10</option>
                    </c:when>
                    <c:otherwise>
                        <option value="10">10</option>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${paginationParameters.recordsPerPage==15}">
                        <option value="15" selected>15</option>
                    </c:when>
                    <c:otherwise>
                        <option value="15">15</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </form>
    </div>
</div><!--container-->
<div class="container justify-content-lg-center" id="main">

    <c:forEach items="${requestScope.conferences}" var="conference">
        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-5">
                                <p class="card-title font-weight-bold"><fmt:message
                                        key="page.message.conference"/> ${conference.topic}</p>
                            </div>
                            <div class="col-2">
                                <p class="card-title font-weight-bold"><fmt:message
                                        key="page.message.location"/>${conference.location}</p>
                            </div>
                            <div class="col-2">
                                <p class="card-title font-weight-bold">${conference.getFormatedDateTime()}</p>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.role == 'admin'}">
                                    <div class="col-3">
                                        <div class="row">

                                            <div class="col-6">
                                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/deleteconference"
                                                      method="post">
                                                    <input type="hidden" name="deleteConference"
                                                           value="${conference.id}">
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

                                        </div>

                                    </div>
                                </c:when>
                            </c:choose>
                        </div> <!--/row> -->

                        <c:forEach items="${conference.reports}" var="report">
                            <div class="row">
                                <div class="col-5">
                                    <p class="card-title">${report.topic}</p>
                                </div>
                                <div class="col-2">
                                    <p class="card-title">${report.speakerName} ${report.speakerSurname}</p>
                                </div>
                                <div class="col-2">
                                    <p> ${report.getFormatedDateTime()}</p>
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
        <div class="col-xs-1" align="center">
            <nav>
                <ul class="pagination">
                    <c:if test="${paginationParameters.currentPage != 1}">
                        <li class="page-item"><a class="page-link"
                                                 href="/${sessionScope.role}/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage-1}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${paginationParameters.pagesAmount}" var="i">
                        <c:choose>
                            <c:when test="${paginationParameters.currentPage eq i}">
                                <li class="page-item active"><a class="page-link">
                                        ${i} <span class="sr-only">(current)</span></a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link"
                                                         href="/${sessionScope.role}/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${paginationParameters.currentPage lt paginationParameters.pagesAmount}">
                        <li class="page-item"><a class="page-link"
                                                 href="/${sessionScope.role}/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div><!--/container-->
</div>
</fmt:bundle>
<jsp:include page="sections/footer.jsp"/>
</body>
</html>
