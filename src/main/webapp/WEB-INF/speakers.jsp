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
<jsp:include page="sections/header.jsp"/>
<div class="container" id="main">
    <div class="container">
        <div class="row">
            <form action="${pageContext.request.contextPath}/${sessionScope.role}/catalogofspeakers"
                  onchange="submit()">
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
    <div class=" container">
        <c:forEach items="${requestScope.speakers}" var="speaker">

            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-4">
                            <h5 class="card-title">${speaker.name} ${speaker.surname}</h5>
                        </div><!--col-6-->
                        <div class="col">
                            <div class="row">
                                <div class="col-4">
                                    <h5><fmt:message key="page.message.rate.me"/></h5>
                                </div>
                                <div class="col-8">
                                    <form class="rating ${speaker.id}" method="post"
                                          onsubmit="getScrollPosition('scroll-speaker${speaker.id}')">
                                        <input type="hidden" name="speakerId" value="${speaker.id}">
                                        <input type="hidden" name="currentPage"
                                               value="${paginationParameters.currentPage}">
                                        <input type="hidden" name="recordsPerPage"
                                               value="${paginationParameters.recordsPerPage}">
                                        <input type="hidden" name="scrollPosition"
                                               id="scroll-speaker${speaker.id}" >
                                        <input type="radio" id="star5${speaker.id}" name="rating${speaker.id}" value="5"
                                               onclick="this.form.submit()"/><label
                                            for="star5${speaker.id}"><span>&#9733</span></label>
                                        <input type="radio" id="star4${speaker.id}" name="rating${speaker.id}" value="4"
                                               onclick="this.form.submit()"/>
                                        <label for="star4${speaker.id}"><span>&#9733</span></label>
                                        <input type="radio" id="star3${speaker.id}" name="rating${speaker.id}" value="3"
                                               onclick="this.form.submit()"/>
                                        <label for="star3${speaker.id}"><span>&#9733</span></label>
                                        <input type="radio" id="star2${speaker.id}" name="rating${speaker.id}" value="2"
                                               onclick="this.form.submit()"/>
                                        <label for="star2${speaker.id}"><span>&#9733</span></label>
                                        <input type="radio" id="star1${speaker.id}" name="rating${speaker.id}" value="1"
                                               onclick="this.form.submit()"/>
                                        <label for="star1${speaker.id}"><span>&#9733</span></label>

                                    </form>
                                    <script>
                                        var radiobtn = document.getElementById('star${requestScope.ratings.get(speaker.id)}${speaker.id}');
                                        radiobtn.setAttribute("checked", "");
                                    </script>
                                </div><!--col-6-->
                            </div><!--row-->
                        </div><!--col-->
                        <div class="col-1">
                            <p class="font-weight-bold">${speaker.rating} </p>
                        </div>


                    </div><!--/row-->
                    <c:set var="voteForYourself" value="voteForYourself${speaker.id}"/>
                    <c:if test="${not empty requestScope.get(voteForYourself)}">
                        <div class="row">
                            <div class="col-lg-12">
                                <h5 class="text-danger text-center">${requestScope.get(voteForYourself)}</h5>
                            </div>
                        </div>
                    </c:if>
                    <c:set var="alreadyVoted" value="alreadyVoted${speaker.id}"/>
                    <c:if test="${not empty requestScope.get(alreadyVoted)}">
                        <div class="row">
                            <div class="col-lg-12">
                                <h5 class="text-info text-center">${requestScope.get(alreadyVoted)}</h5>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-6">
                            <ul class="list-group">
                                <c:forEach items="${speaker.speakerReports}" var="report">
                                    <li class="list-group-item">${report.topic}</li>
                                </c:forEach>
                            </ul>
                        </div><!--col-6-->
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
                                                     href="/${sessionScope.role}/catalogofspeakers?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage-1}"><<</a>
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
                                                             href="/${sessionScope.role}/catalogofspeakers?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${paginationParameters.currentPage lt paginationParameters.pagesAmount}">
                            <li class="page-item"><a class="page-link"
                                                     href="/${sessionScope.role}/catalogofspeakers?recordsPerPage=${paginationParameters.recordsPerPage}&currentPage=${paginationParameters.currentPage+1}">>></a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
<jsp:include page="sections/footer.jsp"/>
<script src=<c:url value="/js/main.js"/>></script>

</body>
<script> window.scroll({top:  ${scrollPosition}, left: 0});</script>

</html>
