<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title> Catalog - AllConferences</title>
    <link href="<c:url value='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO' />"
          crossorigin="anonymous" rel="stylesheet">
    <link href="<c:url value='/css/index.css' />" rel="stylesheet">
    <link href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/baguettebox.js/1.10.0/baguetteBox.min.csss' />"
          rel="stylesheet">
</head>
<meta charset="UTF-8">

<body>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language: pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}" scope="session"/>
<jsp:include page="sections/header.jsp"/>
    <div>
        <button class="btn btn-primary" type="button" style="margin-top: 102px;margin-left: 80%;">create new&nbsp;
        </button>
    </div>
    <div class="table-responsive"
         style="background-color: #c1b8b8;margin-top: 2rem;margin-right: 178px;margin-left: 61px;width: 80%;">
        <table class="table">
            <thead>
            <tr>
                <th>NAME OF confernce</th>
                <th>place</th>
                <th>date time</th>
                <th>
                    <div class="btn-group" role="group">
                        <button class="btn btn-primary" type="button">adminedit</button>
                        <button class="btn btn-primary" type="button">admindelete</button>
                    </div>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>report</td>
                <td>speaker</td>
                <td>time</td>
                <td>
                    <div class="btn-group" role="group">
                        <button class="btn btn-primary" type="button">subscribe</button>
                        <button class="btn btn-primary" type="button">unsubscribe</button>
                    </div>
                </td>
            </tr>
            <tr></tr>
            </tbody>
        </table>
        ${map.kek}
    </div>

</body>
</html>
