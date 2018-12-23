<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${speaker.name} ${speaker.surname}</title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">

</head>
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







<div class="col-xs-1" align="center">
    <nav>
        <ul class="pagination">
            <c:if test="${paginationParameters.currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="/${sessionScope.role}/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage-1}"><<</a>
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
                                         href="/${sessionScope.role}/catalog?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage+1}">>></a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

</body>
</fmt:bundle>
</html>
