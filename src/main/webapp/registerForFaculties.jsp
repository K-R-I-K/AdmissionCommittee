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

<html class="mh-100">
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
<body class="mh-100">

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
            <li class="active">
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
                        <form style="margin: 0" action="/home" method="post">
                            <div class="d-flex justify-content-center">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2"> <fmt:message key="registerForFaculty(ies)"/> </h3>
                            </div>
                            <input name="command" type="hidden" value="registerForFaculties">
                            <input name="user_id" type="hidden" value="${user.id}">
                            <div class="d-flex justify-content-center">
                                <select style="width: 100px; height: 100px; margin-bottom: 10px" name="chosenFaculties" multiple="multiple">
                                    <%--<c:if test="${sessionScope.selectedFaculties != null}">
                                        <c:forEach var="faculty" items="${selectedFaculties}">
                                            <option value="${faculty.id}" selected="selected">${faculty.name}</option>
                                            <br>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${sessionScope.faculties != null}">--%>
                                        <c:forEach var="faculty" items="${faculties}">
                                            <option value="${faculty.id}">${faculty.name}</option>
                                            <br>
                                        </c:forEach>
                                    <%--</c:if>--%>
                                </select>
                            </div>
                            <div class="d-flex justify-content-center">
                                <c:if test="${sessionScope.error == 'facultyFaculties'}">
                                    <div style="color: red">
                                        Select faculty(ies)
                                    </div>
                                    ${sessionScope.error = null}
                                </c:if>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="mb-4">
                                    <c:choose>
                                        <c:when test="${sessionScope.mark1 != null}">
                                            <input style="width: 120px; margin: 0 5px" type="number" name="mark1" min="100" max="200" value="${sessionScope.mark1}">
                                            ${sessionScope.mark1 = null}
                                        </c:when>
                                        <c:otherwise>
                                            <input style="width: 120px; margin: 0 5px" type="number" name="mark1" min="100" max="200" placeholder="<fmt:message key="firstMark"/>">
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${sessionScope.error == 'facultyMark1'}">
                                        <div style="color: red">
                                            Incorrect first mark input
                                        </div>
                                        ${sessionScope.error = null}
                                    </c:if>
                                </div>
                                <div class="mb-4">
                                    <c:choose>
                                        <c:when test="${sessionScope.mark2 != null}">
                                            <input style="width: 120px; margin: 0 5px" type="number" name="mark2" min="100" max="200" value="${sessionScope.mark2}">
                                            ${sessionScope.mark2 = null}
                                        </c:when>
                                        <c:otherwise>
                                            <input style="width: 120px; margin: 0 5px" type="number" name="mark2" min="100" max="200" placeholder="<fmt:message key="secondMark"/>">
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${sessionScope.error == 'facultyMark2'}">
                                        <div style="color: red">
                                            Incorrect second mark input
                                        </div>
                                        ${sessionScope.error = null}
                                    </c:if>
                                </div>
                                <div class="mb-4">
                                    <c:choose>
                                        <c:when test="${sessionScope.mark3 != null}">
                                            <input style="width: 120px; margin: 0 5px" type="number" name="mark3" min="100" max="200" value="${sessionScope.mark3}">
                                            ${sessionScope.mark3 = null}
                                        </c:when>
                                        <c:otherwise>
                                            <input style="width: 120px; margin: 0 5px" type="number" name="mark3" min="100" max="200" placeholder="<fmt:message key="thirdMark"/>">
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${sessionScope.error == 'facultyMark3'}">
                                        <div style="color: red">
                                            Incorrect third mark input
                                        </div>
                                        ${sessionScope.error = null}
                                    </c:if>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <div class="mb-4">
                                    <c:choose>
                                        <c:when test="${sessionScope.avgCertificateMark != null}">
                                            <input type="number" step="0.01" name="avg_certificate_mark" min="1" max="12" value="${sessionScope.avgCertificateMark}">
                                            ${sessionScope.avgCertificateMark = null}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="number" step="0.01" name="avg_certificate_mark" min="1" max="12" placeholder="<fmt:message key="avgCertificateMark"/>">
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${sessionScope.error == 'facultyAvgCertificateMark'}">
                                        <div style="color: red">
                                            Incorrect avg certificate mark input
                                        </div>
                                        ${sessionScope.error = null}
                                    </c:if>
                                </div>
                            </div>
                            <div class="d-flex justify-content-center">
                                <button class = "btn btn-success btn-lg mb-1" type="submit"> <fmt:message key="registerForFaculty(ies)"/> </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
