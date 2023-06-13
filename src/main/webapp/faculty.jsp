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

<div class="container">
  ${faculty.name}
  <br>
  <fmt:message key="budgetPlaces"/>: ${faculty.budgetPlaces}
  <fmt:message key="totalPlaces"/>: ${faculty.totalPlaces}

  <ul class="list-group">
    <c:forEach var="applicant" items="${applicants}" varStatus="status">
      <li class="list-group-item">
        <div class="d-flex justify-content-between">
          <div>
              ${applicant.name}
              <fmt:message key="passed"/>: ${marks[status.index].passed}
              <br/>
              <fmt:message key="marks"/>:
              ${marks[status.index].mark1}
              ${marks[status.index].mark2}
              ${marks[status.index].mark3}
              ${marks[status.index].avgCertificateMark}
          </div>
          <div class="row">
            <div style="margin: auto">${rating[status.index]}</div>
            <c:if test="${sessionScope.user!=null && sessionScope.user.role==sessionScope.admin}">
              <c:choose>
                <c:when test="${!marks[status.index].passed}">
                  <form style="margin: 5px" action="home" method="post">
                    <input name="command" type="hidden" value="permit">
                    <input name="id" type="hidden" value="${marks[status.index].id}">
                    <input name="facultyId" type="hidden" value="${faculty.id}">
                    <input name="applicantId" type="hidden" value="${applicant.id}">
                    <input name="mark1" type="hidden" value="${marks[status.index].mark1}">
                    <input name="mark2" type="hidden" value="${marks[status.index].mark2}">
                    <input name="mark3" type="hidden" value="${marks[status.index].mark3}">
                    <input name="avgCertificateMark" type="hidden" value="${marks[status.index].avgCertificateMark}">
                    <button class="btn btn-success" type="submit"><fmt:message key="permit"/></button>
                  </form>
                </c:when>
                <c:otherwise>
                  <form style="margin: 5px" action="home" method="post">
                    <input name="command" type="hidden" value="forbid">
                    <input name="id" type="hidden" value="${marks[status.index].id}">
                    <input name="facultyId" type="hidden" value="${faculty.id}">
                    <input name="applicantId" type="hidden" value="${applicant.id}">
                    <input name="mark1" type="hidden" value="${marks[status.index].mark1}">
                    <input name="mark2" type="hidden" value="${marks[status.index].mark2}">
                    <input name="mark3" type="hidden" value="${marks[status.index].mark3}">
                    <input name="avgCertificateMark" type="hidden" value="${marks[status.index].avgCertificateMark}">
                    <button class="btn btn-danger" type="submit"><fmt:message key="forbid"/></button>
                  </form>
                </c:otherwise>
              </c:choose>
            </c:if>
          </div>
        </div>
      </li>
    </c:forEach>
  </ul>
</div>
</body>
</html>
