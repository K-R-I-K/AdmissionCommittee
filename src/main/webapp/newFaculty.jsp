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
            <li class="active">
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
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img3.webp"
                         class="w-100" style="border-top-left-radius: .3rem; border-top-right-radius: .3rem;"
                         alt="Sample photo">
                    <div class="card-body p-4 p-md-5">
                        <div class="d-flex justify-content-center">
                            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2"> <fmt:message key="newFaculty"/> </h3>
                        </div>
                        <form action="/home" method="post">
                            <input name="command" type="hidden" value="addFaculty">
                            <div class="d-flex justify-content-center">
                                <div class="mb-4">
                                    <input type="text" name="name" placeholder="<fmt:message key="name"/>">
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="mb-4">
                                    <input type="number" name="budget_places" placeholder="<fmt:message key="budgetPlaces"/>">
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="mb-4">
                                    <input type="number" name="total_places" placeholder="<fmt:message key="totalPlaces"/>">
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button class = "btn btn-success btn-lg mb-1" type="submit"> <fmt:message key="addNewFaculty"/> </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>




<%-- <select name="folders" multiple="multiple">
   <c:forEach items="${folders}" var="folder">
     <option value="${folder.id}">${folder.name}</option>
     <br>
   </c:forEach>
 </select>
 <button class = "btn btn-success btn-lg mb-1" type="submit">Add new file</button>
</form>--%>

<%--<form action="/home" method="post">
    <div>
        <input name="command" type="hidden" value="addFaculty">
        <input type="text" name="name" placeholder="name">
        <input type="number" name="budget_places" placeholder="budget places">
        <input type="number" name="total_places" placeholder="total places">
        <button class = "btn btn-success btn-lg mb-1" type="submit">Add new Faculty</button>
    </div>
</form>--%>

</body>
</html>
