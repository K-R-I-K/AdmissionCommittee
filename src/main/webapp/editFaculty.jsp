<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${empty language}">
    <c:set var="language" scope="session" value="${pageContext.request.locale.language}"/>
</c:if>
<c:if test="${!empty language}">
    <fmt:setLocale value="${language}" scope="session"/>
</c:if>

<fmt:setBundle basename="resources"/>

<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="style/header.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style>
        .nav-link{padding:15px 5px; transition:0.2s;}

        .userName{
            color:#000;
            font-weight:bold;
            font-size:18px;
            margin: auto;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
    <ul id="navbar" class="navbar-nav mr-auto">
        <li class="nav-item">
            <a href="/home?command=getFaculties" class="nav-link"> <fmt:message key="listOfFaculties"/></a>
        </li>
        <c:if test="${sessionScope.user != null && sessionScope.user.role == sessionScope.admin}">
            <li class="nav-item">
                <a href="newFaculty.jsp" class="nav-link"> <fmt:message key="addNewFaculty"/> </a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user != null && sessionScope.user.role == sessionScope.admin}">
            <li class="nav-item">
                <a href="/home?command=getUsers" class="nav-link"> <fmt:message key="listOfUsers"/> </a>
            </li>
        </c:if>
        <c:if test="${sessionScope.user != null && sessionScope.user.role != sessionScope.blocked}">
            <li class="nav-item">
                <a href="/home?command=setFacultiesForRegistration" class="nav-link"> <fmt:message key="registerForFaculty(ies)"/> </a>
            </li>
        </c:if>
    </ul>

    <div style="margin: 0 10px" class="change-language">
        <c:choose>
            <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] eq 'en'}">
                <a class="active" href="">ENG</a>
            </c:when>
            <c:otherwise>
                <a href="home?command=changeLanguage&language=en">ENG</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] eq 'uk'}">
                <a class="active" href="">УКР</a>
            </c:when>
            <c:otherwise>
                <a href="home?command=changeLanguage&language=ukr">УКР</a>
            </c:otherwise>
        </c:choose>
    </div>

    <c:choose>
        <c:when test="${sessionScope.user == null}">
            <div class="nav-item">
                <a href="logIn.jsp" class="btn btn-primary"> <fmt:message key="logIn"/> </a>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <p class="userName">
                        ${user.name}
                    <c:if test="${sessionScope.user.role == sessionScope.blocked}">
                        (<fmt:message key="blocked"/>)
                    </c:if>
                </p>
            </div>
            <form style="margin: 0 10px" action="home" method="get">
                <input name="command" type="hidden" value="logOut">
                <button class="btn btn-primary" type="submit"> <fmt:message key="logOut"/> </button>
            </form>
        </c:otherwise>
    </c:choose>
</nav>

<section class="h-100 h-custom" style="background-color: #8fc4b7;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-8 col-xl-6">
                <div class="card rounded-3">
                    <div class="card-body p-4 p-md-5">
                        <form action="/home" method="post">
                            <input name="command" type="hidden" value="editFaculty">
                            <input name="id" type="hidden" value="${editFaculty.id}">
                            <div class="mb-4">
                                <div class="row">
                                    <div class="col-md-4 mb-1">
                                        <fmt:message key="name"/>:
                                    </div>
                                    <div class="col-md-5 mb-1">
                                        <input type="text" name="name" value="${editFaculty.name}">
                                    </div>
                                </div>
                            </div>
                            <div class="mb-4">
                                <div class="row">
                                    <div class="col-md-4 mb-1">
                                        <fmt:message key="budgetPlaces"/>:
                                    </div>
                                    <div class="col-md-5 mb-1">
                                        <input type="number" name="budget_places" value="${editFaculty.budgetPlaces}">
                                    </div>
                                </div>
                            </div>
                            <div class="mb-4">
                                <div class="row">
                                    <div class="col-md-4 mb-1">
                                        <fmt:message key="totalPlaces"/>:
                                    </div>
                                    <div class="col-md-5 mb-1">
                                        <input type="number" name="total_places" value="${editFaculty.totalPlaces}">
                                    </div>
                                </div>
                            </div>
                            <button class = "btn btn-success btn-lg mb-1" type="submit"><fmt:message key="editFaculty"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
