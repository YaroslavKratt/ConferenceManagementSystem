<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<c:choose>
    <c:when test="${sessionScope.role == 'guest'}">
        <nav class="navbar navbar-light navbar-expand-md">
            <div class="container-fluid"><a class="navbar-brand" href="/">AllConferences</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                        class="navbar-toggler-icon"></span></button>
                <div
                        class="collapse navbar-collapse float-right" id="navcol-1">
                    <ul class="nav navbar-nav float-right ml-auto">
                        <li class="nav-item" role="presentation"><a class="nav-link"
                                                                    href="${pageContext.request.contextPath}/guest/login">LOGIN</a>
                        </li>
                        <li class="nav-item" role="presentation"><a class="nav-link float-right"
                                                                    href="${pageContext.request.contextPath}/guest/registration">REGISTRATION</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="navbar navbar-light navbar-expand-md">
            <div class="container-fluid"><a class="navbar-brand" href="#">AllConferences</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                        class="navbar-toggler-icon"></span></button>
                <div
                        class="collapse navbar-collapse float-right" id="navcol-1">
                    <ul class="nav navbar-nav float-right ml-auto">

                        <li class="nav-item" role="presentation"><a class="nav-link float-right"
                                                                    href="${pageContext.request.contextPath}/${sessionScope.role}/logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </c:otherwise>
</c:choose>
</body>
</html>
