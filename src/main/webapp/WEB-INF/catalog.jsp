<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title> Catalog - AllConferences</title>
    <link href="<c:url value='/css/main.css'/>" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">

    <link href="<c:url value='/css/main.css' />" rel="stylesheet">


</head>
<meta charset="UTF-8">


<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"/>
<fmt:setLocale value="${ empty sessionScope.language ? 'en' : sessionScope.language}"/>
<fmt:setBundle basename="messages"/>

<fmt:setLocale value="${language}" scope="session"/>
<jsp:include page="sections/header.jsp"/>
<div>
    <button class="btn btn-primary" type="button"><fmt:message key="page.message.create.conference"/>
    </button>
</div>
<div class="table-responsive">
    <c:forEach items="${requestScope.conferences}" var="conference">
        <table class="table">
            <thead>
            <tr>
                <th>${conference.topic}</th>
                <th>${conference.location}</th>
                <th>${conference.dateTime}</th>
                <th>
                    <button class="btn btn-primary" type="button"><fmt:message key="page.message.edit"/></button>
                    <button class="btn btn-primary" type="button"><fmt:message key="page.message.delete"/></button>
                </th>
            </tr>
            </thead>
            <c:forEach items="${conference.reports}" var="report">

                <tr>
                    <td>${report.topic}</td>
                    <td>${report.speaker.name} ${report.speaker.surname}</td>
                    <td>${report.dateTime}</td>
                    <td>
                        <button class="btn btn-primary" type="button"><fmt:message
                                key="page.message.subscribe"/></button>
                        <button class="btn btn-primary" type="button"><fmt:message
                                key="page.message.unsubscribe"/></button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:forEach>
</div>
<jsp:include page="sections/footer.jsp"/>
<script src=<c:url value="/bootstrap/js/bootstrap.bundle.js" />></script>
<script src=<c:url value="/bootstrap/js/bootstrap.js" />></script>
<script src=<c:url value="/bootstrap/js/jquery.min.js" />></script>
<script src=<c:url value="/bootstrap/js/smoothproducts.min.js" />></script>
<script src=<c:url value="/bootstrap/js/theme.js" />></script>
</body>
</html>
