<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <meta charset="UTF-8">
    <title><fmt:message key="page.message.speakers"/></title>
    <link href="<c:url value='/bootstrap/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-grid.css' />" rel="stylesheet">
    <link href="<c:url value='/bootstrap/css/bootstrap-reboot.css' />" rel="stylesheet">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet">


</head>
<jsp:include page="../sections/header.jsp"/>
<div class="container" id="main">
    <div class="container">
        <form action="${pageContext.request.contextPath}/${sessionScope.role}/statistics"
              onchange="submit()">
            <input type="hidden" name="currentPage" value="1">
            <div class="row">
                <div class="col-4">
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
                </div>
                <div class="col-4">

                    <label for="sortType"><fmt:message key="page.message.filter"/> </label>
                    <select class="form-control" id="sortType" name="sortType">
                        <c:choose>
                            <c:when test="${sortType=='all'}">
                                <option value="all" selected><fmt:message key="page.message.sort.asсending"/> </option>
                            </c:when>
                            <c:otherwise>
                                <option value="all"><fmt:message key="page.message.sort.asсending"/> </option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${sortType=='past'}">
                                <option value="past" selected><fmt:message key="page.message.sort.only.past" /></option>
                            </c:when>
                            <c:otherwise>
                                <option value="past"><fmt:message key="page.message.sort.only.past" /></option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${sortType=='future'}">
                                <option value="future" selected><fmt:message key="page.message.sort.only.future" /></option>
                            </c:when>
                            <c:otherwise>
                                <option value="future"><fmt:message key="page.message.sort.only.future" /></option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
            </div>
        </form>
    </div><!--container-->
    <div class=" container">
        <c:forEach items="${requestScope.conferences}" var="conference">
            <div class="row">
                <div class="col-12">

                    <div class="row" style="padding-top: 100px">
                        <div class="col-4">
                            <h5 class="font-weight-bold"><fmt:message
                                    key="page.message.conference"/> ${conference.topic}</h5>
                        </div>
                        <div class="col-4">
                            <h5 class=" font-weight-bold"><fmt:message
                                    key="page.message.location"/>${conference.location}</h5>
                        </div>
                        <div class="col-2">
                            <h5 class=" font-weight-bold">${conference.getFormatedDateTime()}</h5>
                        </div>
                    </div> <!--/row> -->
                    <div class="card">
                        <div class="card-body">
                            <div class="row ">
                                <div class="col-4 "><h5><fmt:message key="page.message.topic.of.report"/></h5></div>
                                <div class="col-2"><h5><fmt:message key="page.message.speaker.of.report"/></h5></div>
                                <div class="col-2"><h5><fmt:message key="page.message.begin.of.report"/></h5></div>
                                <div class="col-2"><h5><fmt:message key="page.message.registereted.users"/></h5></div>
                                <div class="col-2"><h5><fmt:message key="page.message.comers"/></h5></div>

                            </div>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/${sessionScope.role}/statistics"
                                  onsubmit="getScrollPosition('scroll${conference.id}')">
                                <c:forEach items="${conference.reports}" var="report">
                                    <input type="hidden" name="report-id" value="${report.id}">
                                    <div class="row" id="report${report.id}">
                                        <div class="col-4">
                                            <p class="card-title">${report.topic}</p>
                                        </div>
                                        <div class="col-2">
                                            <p class="card-title">${report.speakerName} ${report.speakerSurname}</p>
                                        </div>
                                        <div class="col-2">
                                            <p> ${report.getFormatedDateTime()}</p>
                                        </div>
                                        <div class="col-2">
                                            <p> ${report.regestratedAmount}</p>
                                        </div>
                                        <div class="col-2">
                                            <input type="number" class="comers-amount"
                                                   name="visitors-amount${report.id}" value="${report.comersAmount}"
                                                   min="0" required>
                                        </div>

                                    </div>
                                    <div class="row justify-content-center">


                                    </div>
                                    <!--/row-->
                                </c:forEach>
                                <div class="row text-center">
                                    <div class="col">
                                        <input hidden name="currentPage"
                                               value="${paginationParameters.currentPage}">
                                        <input hidden name="recordsPerPage"
                                               value="${paginationParameters.recordsPerPage}">
                                        <input hidden name="scrollPosition"
                                               id="scroll${conference.id}" value="">
                                        <input hidden name="submitted" value="true">
                                        <button id="btn${conference.id}" class="btn btn-primary"
                                                type="submit"
                                                name="reportForSubscription"
                                                value="${conference.id}">
                                            <fmt:message key="page.message.save.changes"/>
                                        </button>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>

            </div>


        </c:forEach>
    </div><!--/container-->


    <div class="container">
        <div class="row">
            <div class="col-xs-1" align="center">
                <nav>
                    <ul class="pagination">
                        <c:if test="${paginationParameters.currentPage != 1}">
                            <li class="page-item"><a class="page-link"
                                                     href="/${sessionScope.role}/statistics?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage-1}"><<</a>
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
                                                             href="/${sessionScope.role}/statistics?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${paginationParameters.currentPage lt paginationParameters.pagesAmount}">
                            <li class="page-item"><a class="page-link"
                                                     href="/${sessionScope.role}/statistics?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage+1}">>></a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
<jsp:include page="../sections/footer.jsp"/>
<script src=<c:url value="/js/main.js"/>></script>
<script> window.scroll({top:  ${scrollPosition}, left: 0});
</script>
</body>

</html>
