<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">

    <title><fmt:message key="page.message.edit.conference"/></title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">

</head>
<body>
<jsp:include page="sections/header.jsp"/>
<div class="container center-block" id="main">
    <h3 class="text-center"><fmt:message key="page.message.edit.conference"/></h3>
    <div class="card text-center">
        <form method="post">

            <div class="card-body">

                <div class="row align-items-center">
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <label for="conference-name"><h5><fmt:message key="page.message.topic.of.conference"/></h5>
                        </label>
                        <input type="text" name="conference-name" id="conference-name" required
                               value="${conference.topic}">
                        <input type="hidden" name="conference" value="${conference.id}">
                    </div>
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <label for="conference-location"><h5><fmt:message key="page.message.conference.location"/></h5>
                        </label>
                        <input type="text" name="conference-location" id="conference-location" required
                               value="${conference.location}">
                    </div>
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <label for="conference-date-time"><h5><fmt:message key="page.message.date.of.conference"/></h5>
                        </label>
                        <input type="datetime-local" name="conference-date-time" id="conference-date-time" required
                               value="${conference.dateTime}">
                    </div>
                </div>
                <div class="row justify-content-end">
                    <div class="col-md-4 col-lg-4 col-sm-12 ">

                        <c:if test="${not empty wrongDate }">
                            <p class="text-danger"> ${wrongDate}</p>
                        </c:if>
                    </div>
                </div>

                <div class="container" id="report-inputs">

                    <div class="row align-items-center">
                        <div class="col-md-4 col-lg-4 col-sm-12  centered">
                            <h5><fmt:message key="page.message.topic.of.report"/></h5>
                        </div>
                        <div class="col-md-4 col-lg-4 col-sm-12  centered">
                            <h5><fmt:message key="page.message.begin.of.report"/></h5>
                        </div>
                        <div class="col-md-4 col-lg-4 col-sm-12  centered">
                            <h5><fmt:message key="page.message.choose.speaker"/></h5>
                        </div>
                    </div>
                    <div class="container" id="first-inputs">
                        <c:forEach items="${conference.reports}" var="report">
                            <div class="row report-input align-items-center" id="report-data${report.id}">
                                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                    <input type="text" name="report-name${report.id}" required value="${report.topic}">
                                    <input type="hidden" name="report-name" value="${report.topic}">

                                    <input type="hidden" name="report-id" value="${report.id}">
                                </div>
                                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                    <input type="datetime-local" name="report-date-time${report.id}"
                                           required value="${report.dateTime}">
                                    <input type="hidden" name="report-date-time" required value="${report.dateTime}">

                                </div>
                                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                    <select class="selectpicker" name="report-speaker${report.id}"
                                            id="report-speaker${report.id}">
                                        <c:forEach var="possibleSpeaker" items="${requestScope.possibleSpeakers}">
                                            <option value="${possibleSpeaker.id}"
                                                    <c:if test="${report.speakerId == possibleSpeaker.id}">
                                                        selected
                                                    </c:if>
                                                    name="${possibleSpeaker.id}">${possibleSpeaker.name} ${possibleSpeaker.surname}
                                            </option>

                                        </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="row">
                                <div class="col  centered">

                                    <c:set var="dateError" value="earlierThanConference${report.id}"/>
                                    <c:if test="${not empty dateError }">
                                        <p class="text-danger"> ${requestScope.get(dateError)}</p>
                                    </c:if>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/${sessionScope.role}/deletereport">
                                        <input hidden name="currentPage"
                                               value="${paginationParameters.currentPage}">
                                        <input hidden name="recordsPerPage"
                                               value="${paginationParameters.recordsPerPage}">
                                        <input type="hidden" name="conference" value="${conference.id}">
                                        <input type="hidden" name="uri"
                                               value="${pageContext.request.contextPath}/${sessionScope.role}/editconference">
                                        <button class="btn btn-danger" type="submit"
                                                name="reportIdForRemoving"
                                                value="${report.id}"><fmt:message
                                                key="page.message.delete"/></button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="row">
                            <div class="col centered">
                                <h5><fmt:message key="page.message.new.report"/></h5>
                            </div>
                        </div>
                        <div class="row report-input align-items-center">
                            <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                <input type="text" name="report-name-new">
                            </div>
                            <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                <input type="datetime-local" name="report-date-time-new">


                            </div>
                            <div class="col-md-4 col-lg-4 col-sm-12  centered">
                                <select class="selectpicker" id="report-speaker-new" name="report-speaker-new">
                                    <c:forEach var="possibleSpeaker" items="${requestScope.possibleSpeakers}">
                                        <option value="${possibleSpeaker.id}"
                                                name="${possibleSpeaker.id}">${possibleSpeaker.name} ${possibleSpeaker.surname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col  centered">

                                <c:set var="dateError" value="earlierThanConference-new"/>
                                <c:if test="${not empty dateError }">
                                    <p class="text-danger"> ${requestScope.get(dateError)}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col  centered">
                                <c:if test="${not empty emptyField }">
                                    <p class="text-danger"> ${requestScope.emptyField}</p>
                                </c:if>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row ">
                                <div class="col centered">
                                    <input type="hidden" name="submitted" value="true">
                                    <button class="btn btn-primary  center-block" type="submit"><fmt:message
                                            key="page.message.save.changes"/>
                                    </button>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </form>

    </div><!--card body-->
</div><!--card-->
</div><!--/container-->

</body>
<jsp:include page="sections/footer.jsp"/>

<script src=<c:url value="/js/main.js"/>></script>

</fmt:bundle>
</html>
