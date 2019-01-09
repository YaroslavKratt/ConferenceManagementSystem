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
    <jsp:include page="sections/header.jsp"/>
    <fmt:bundle basename="messages"></head>

<body>
<div class="container" style="margin-top: 3rem" id="main">
    <form id="new-report" method="post" action="${pageContext.request.contextPath}/${sessionScope.role}/addreport">

        <div class="container" id="report-inputs">
            <div class="row justify-content-center">
                <div class="col-12 text-center">
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
                <div class="row report-input align-items-center" id="report-data">
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <div class="row">
                            <label for="report-name-en-new"> <fmt:message key="page.message.en"/> </label>
                            <input type="text" id="report-name-en-new" name="report-name-en-new">
                            <p class="text-danger"> ${notInEnglish}</p>

                        </div>
                        <div class="row">
                            <label for="report-name-ua-new"><fmt:message key="page.message.ua"/> </label>
                            <input type="text" id="report-name-ua-new" name="report-name-ua-new">
                        </div>
                    </div>
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <input type="datetime-local" name="report-date-time-new" required>


                    </div>
                    <div class="col-md-4 col-lg-4 col-sm-12  centered">
                        <select class="selectpicker" id="report-speaker" name="report-speaker-new">
                            <c:forEach var="possibleSpeaker" items="${requestScope.possibleSpeakers}">
                                <option value="${possibleSpeaker.id}"
                                        name="${possibleSpeaker.id}">${possibleSpeaker.name} ${possibleSpeaker.surname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col  text-center">
                        <c:if test="${not empty earlierThanConference}">
                            <p class="text-danger"> ${earlierThanConference}</p>
                        </c:if>
                    </div>
                </div>

            </div>

        </div>
        <div class="row">
            <div class="col  text-center">
                <input hidden name="currentPage"
                       value="${currentPage}">
                <input hidden name="recordsPerPage"
                       value="${recordsPerPage}">
                <input type="hidden" name="submitted" value="true">
                <input type="hidden" name="conferenceId" value="${conferenceId}">
                <input hidden name="scrollPosition"
                       value="${scrollPosition}">
                <button class="btn btn-primary  center-block" type="submit"><fmt:message
                        key="page.message.add.report"/>
                </button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="sections/footer.jsp"/>
</fmt:bundle>
<script src=<c:url value="/js/main.js"/>></script>
</body>
</html>
