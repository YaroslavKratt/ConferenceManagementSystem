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
                <c:choose>
                    <c:when test="${sessionScope.role == 'admin'}">
                        <th>
                            <button class="btn btn-primary" type="button"><fmt:message
                                    key="page.message.edit"/></button>
                            <button class="btn btn-primary" type="button"><fmt:message
                                    key="page.message.delete"/></button>
                        </th>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <c:forEach items="${conference.reports}" var="report">

                <tr>
                    <td>${report.topic}</td>
                    <td>${report.speaker.name} ${report.speaker.surname}</td>
                    <td>${report.dateTime}</td>
                    <c:choose>
                        <c:when test="${sessionScope.role=='user' || sessionScope.role =='admin'}">
                            <td>

                                <c:choose>
                                    <c:when test="${not fn:contains(requestScope.subscriptions, report.id)}">
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/${sessionScope.role}/subscribe">
                                            <button class="btn btn-primary" type="submit" name="reportForSubscription"
                                                    value="${report.id}"><fmt:message
                                                    key="page.message.subscribe"/></button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form method="post"
                                              action="${pageContext.request.contextPath}/${sessionScope.role}/unsubscribe">

                                            <button class="btn btn-primary" type="submit" name="reportForUnsubscription"
                                                    value="${report.id}"><fmt:message
                                                    key="page.message.unsubscribe"/></button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:when>

                    </c:choose>
                </tr>
            </c:forEach>

        </table>
    </c:forEach>
</div>
<jsp:include page="sections/footer.jsp"/>
</fmt:bundle>
</body>

</html>
