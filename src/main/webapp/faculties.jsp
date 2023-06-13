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
        <li class="active">
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

<div class="container">
    <h3 style="text-align: center"><fmt:message key="listOfFaculties"/></h3>
    <div class="d-flex justify-content-center">
        <form action="home" method="get">
            <input name="command" type="hidden" value="getFaculties">
            <select id="mySelect" onchange="myFunction()">
                <c:forEach var="type" items="${sortingTypes}" varStatus="loop">
                    <c:choose>
                        <c:when test="${sortingType == type}">
                            <option value="${type}" selected = "selected">
                                    <fmt:message key='sort.${type}'/>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${type}">
                                <fmt:message key='sort.${type}'/>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input id="sortingType" name="sortingType" type="hidden">
            <button id="button" class="btn" type="submit" hidden></button>
        </form>
    </div>
    <ul class="list-group">
        <c:forEach var="faculty" items="${faculties}" >
            <li class="list-group-item">
                    <div class="d-flex justify-content-between">
                        <div>
                                ${faculty.name}
                            <br>
                            <fmt:message key="budgetPlaces"/>: ${faculty.budgetPlaces}
                            <fmt:message key="totalPlaces"/>: ${faculty.totalPlaces}
                        </div>
                        <div>
                            <div class="row">
                                <form style="margin: 5px" action="home" method="get">
                                    <input name="command" type="hidden" value="getFaculty">
                                    <input name="id" type="hidden" value="${faculty.id}">
                                    <button class="btn btn-success" type="submit"><fmt:message key="more"/></button>
                                </form>
                                <c:if test="${sessionScope.user!=null && sessionScope.user.role==sessionScope.admin}">
                                    <form style="margin: 5px" action="home" method="post">
                                        <input name="command" type="hidden" value="getEditFaculty">
                                        <input name="id" type="hidden" value="${faculty.id}">
                                        <button class="btn btn-warning" type="submit"><fmt:message key="edit"/></button>
                                    </form>
                                    <form style="margin: 5px" action="home" method="post">
                                        <input name="command" type="hidden" value="deleteFaculty">
                                        <input name="id" type="hidden" value="${faculty.id}">
                                        <button class="btn btn-danger" type="submit"><fmt:message key="delete"/></button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
            </li>
        </c:forEach>
    </ul>
    <div class="row">
        <div class="col d-flex justify-content-center">
            <form action="home" method="get">
                <input name="command" type="hidden" value="getFaculties">
                <input name="page" type="hidden" value="<%=pref(request.getParameter("page"))%>">
                <input name="sortingType" type="hidden" value="${sessionScope.sortingType}">
                <button class="btn" type="submit"><<</button>
            </form>
            <c:if test="${isNext}">
                <form action="home" method="get">
                    <input name="command" type="hidden" value="getFaculties">
                    <input name="page" type="hidden" value="<%=next(request.getParameter("page"))%>">
                    <input name="sortingType" type="hidden" value="${sessionScope.sortingType}">
                    <button class="btn" type="submit">>></button>
                </form>
            </c:if>
        </div>
    </div>
</div>

<script>
    function myFunction() {
        document.getElementById("sortingType").value = document.getElementById("mySelect").value;
        document.getElementById('button').click();
    }
</script>
</body>
</html>
<%!
    int pref(String page) {
        if(page == null ) {
            return 1;
        }
        int pageNum = Integer.parseInt(page);
        if(pageNum <= 1) {
            return 1;
        }
        return pageNum - 1;
    }
    int next(String page) {
        if(page == null) {
            return 2;
        }
        int pageNum = Integer.parseInt(page);
        return pageNum + 1;
    }
    int current(String page){
        if(page == null){
            return 1;
        }
        return Integer.parseInt(page);
    }
%>