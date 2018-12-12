<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title> Catalog - AllConferences</title>
    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' />"
          crossorigin="anonymous" rel="stylesheet">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">
    <link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.csss' />"
          rel="stylesheet">
</head>
<meta charset="UTF-8">


<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<jsp:include page="sections/header.jsp"/>
<div>
    <button class="btn btn-primary" type="button" style="margin-top: 102px;margin-left: 80%;">create new&nbsp;
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
                    <button class="btn btn-primary" type="button">adminedit</button>
                    <button class="btn btn-primary" type="button">admindelete</button>
                </th>
            </tr>
            </thead>
            <c:forEach items="${conference.reports}" var="report">

                <tr>
                    <td>${report.topic}</td>
                    <td>${report.speaker.name} ${report.speaker.surname}</td>
                    <td>${report.dateTime}</td>
                    <td>
                            <button class="btn btn-primary" type="button">subscribe</button>
                            <button class="btn btn-primary" type="button">unsubscribe</button>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:forEach>
</div>
<jsp:include page="sections/footer.jsp"/>
</body>
</html>
