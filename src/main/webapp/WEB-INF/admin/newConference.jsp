<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">

    <title> Conference - AllConferences</title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">
    <jsp:include page="../sections/header.jsp"/>
    <fmt:bundle basename="messages"></head>

<body>
<div class="container" id="main">
    <form id="new-confernce" method="post"
          action="${pageContext.request.contextPath}/${sessionScope.role}/createconference">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-6 col-lg-6 col-sm-12  centered">
                    <h5><fmt:message key="page.message.conference"/></h5>
                </div>
            </div>
            <div class="row align-items-center">
                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                    <label for="conference-name-en"><fmt:message key="page.message.topic.of.conference.en"/></label>
                    <input type="text" name="conference-name-en" id="conference-name-en" required>
                    <p class="text-danger"> ${conferenceNameEnNotInEnglish}</p>
                    <label for="conference-name-ua"><fmt:message key="page.message.topic.of.conference.ua"/></label>
                    <input type="text" name="conference-name-ua" id="conference-name-ua" required>
                </div>
                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                    <label for="conference-location-en"><fmt:message key="page.message.conference.location.en"/></label>
                    <input type="text" name="conference-location-en" id="conference-location-en" required>
                    <p class="text-danger"> ${conferenceLocationEnNotInEnglish}</p>
                    <label for="conference-location-ua"><fmt:message key="page.message.conference.location.ua"/></label>
                    <input type="text" name="conference-location-ua" id="conference-location-ua" required>
                </div>
                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                    <label for="conference-date-time"><fmt:message key="page.message.date.of.conference"/> </label>
                    <input type="datetime-local" name="conference-date-time" id="conference-date-time" required>
                    <p class="text-danger"> ${wrongDate}</p>
                </div>
            </div>
        </div>

        <div class="container" id="report-inputs">
            <div class="row align-items-center">
                <div class="col-md-6 col-lg-6 col-sm-12  centered">
                    <h5><fmt:message key="page.message.report"/></h5>

                </div>

            </div>
            <div class="row align-items-center">
                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                    <h6><fmt:message key="page.message.topic.of.report"/></h6>
                </div>
                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                    <h6><fmt:message key="page.message.begin.of.report"/></h6>
                </div>
                <div class="col-md-4 col-lg-4 col-sm-12  centered">
                    <h6><fmt:message key="page.message.choose.speaker"/></h6>
                </div>
            </div>
            <div class="container" id="first-inputs">
                <div class="row report-input align-items-center" id="report-data0">
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <div class="row">
                            <label for="report-name-en0"> <fmt:message key="page.message.en"/> </label>
                            <input type="text" id="report-name-en0" name="report-name-en"        required>
                        </div>
                        <div class="row">
                            <label for="report-name-ua0"> <fmt:message key="page.message.ua"/> </label>
                            <input type="text" id="report-name-ua0" name="report-name-ua"
                                   required>
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <input type="datetime-local" name="report-date-time" name="report-date-time0" required>
                        <p class="text-danger"> ${erlierThanConference}</p>

                    </div>
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <select class="selectpicker" id="report-speaker0" name="report-speaker">
                            <c:forEach var="possibleSpeaker" items="${requestScope.possibleSpeakers}">
                                <option value="${possibleSpeaker.id}"
                                        name="${possibleSpeaker.id}">${possibleSpeaker.name} ${possibleSpeaker.surname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row row-centered">
                    <div class="col-md-6 col-lg-6 col-sm-12  centered">
                        <button class="btn btn-primary  center-block" type="button"
                                onclick="addInputFieldsForReport();"> +
                        </button>
                        <button class="btn btn-primary  center-block" type="button"
                                onclick="removeLastReportInputsDiv();">-
                        </button>
                    </div>
                    <div class="col-md-6 col-lg-6 col-sm-12  centered">
                        <button class="btn btn-primary  center-block" type="submit"><fmt:message
                                key="page.message.create.conference"/>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="../sections/footer.jsp"/>
</fmt:bundle>
<script src=<c:url value="/js/main.js"/>></script>
</body>
</html>
