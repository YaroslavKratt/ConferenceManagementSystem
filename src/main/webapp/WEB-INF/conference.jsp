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
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages"></head>

<body>
<form>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <h3>Conference</h3>
            </div>
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <h3>Date</h3>
            </div>
        </div>
        <div class="row align-items-center">
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <input type="text">
            </div>
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <input type="date">

            </div>
        </div>
    </div>

    <div class="container" id="report-inputs">
        <div class="row align-items-center">
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                    <h3>Report</h3>

            </div>
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <h3>Time</h3>

            </div>
        </div>
        <div class="row report-input align-items-center" id="first-report">
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <input type="text">
            </div>
            <div class="col-md-6 col-lg-6 col-sm-12  centered">
                <input type="time">

            </div>
        </div>
    </div>
    <div class="container">
    <div class="row row-centered">
        <div class="col-md-6 col-lg-6 col-sm-12  centered">
            <button class="btn btn-primary  center-block" type="button" onclick="addInputFieldsForReport();"> +
            </button>
            <button class="btn btn-primary  center-block" type="button" onclick="removeLastReportInputsDiv();">-
            </button>
        </div>
        <div class="col-md-6 col-lg-6 col-sm-12  centered">
            <button class="btn btn-primary  center-block" type="button" onclick="addInputFieldsForReport();">submit
            </button>
        </div>
    </div>
    </div>
</form>
<jsp:include page="sections/footer.jsp"/>
</fmt:bundle>
<script src=<c:url value="/js/main.js"/>></script>
</body>
</html>
