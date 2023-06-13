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
    <meta charset="UTF-8">
    <link rel="stylesheet" href="style/header.css">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
</head>
<body>
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
                            <form action="/home" method="post">
                                <div class="d-flex justify-content-center">
                                    <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2"> <fmt:message key="admissionCommittee"/> </h3>
                                </div>
                                <input name="command" type="hidden" value="logIn">
                                <div class="d-flex justify-content-center">
                                    <div class="mb-4">
                                        <c:choose>
                                            <c:when test="${sessionScope.login != null}">
                                                <input type="text" name="login" value="${sessionScope.login}">
                                                ${sessionScope.login = null}
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" name="login" placeholder="<fmt:message key="login"/>">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${sessionScope.error == 'loginLogin'}">
                                           <%-- <script>
                                                alert("Incorrect login input")
                                            </script>--%>
                                            <div style="color: red">
                                                Incorrect login input
                                            </div>
                                            ${sessionScope.error = null}
                                        </c:if>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <div class="mb-4">
                                        <c:choose>
                                            <c:when test="${sessionScope.password != null}">
                                                <input type="password" name="password" value="${sessionScope.password}">
                                                ${sessionScope.password = null}
                                            </c:when>
                                            <c:otherwise>
                                                <input type="password" name="password" placeholder="<fmt:message key="password"/>">
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${sessionScope.error == 'loginPassword'}">
                                            <%--<script>
                                                alert("Incorrect password input")
                                            </script>--%>
                                            <div style="color: red">
                                                Incorrect password input
                                            </div>
                                            ${sessionScope.error = null}
                                        </c:if>
                                    </div>
                                </div>
                                <div style="margin: 5px 0" class="d-flex justify-content-center">
                                    <c:if test="${sessionScope.error == 'badLogin'}">
                                        <%--<script>
                                            alert("Wrong login or password")
                                        </script>--%>
                                        <div style="color: red">
                                            Wrong login or password
                                        </div>
                                        ${sessionScope.error = null}
                                    </c:if>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <button class = "btn btn-success btn-lg mb-1" type="submit"> <fmt:message key="logIn"/> </button>
                                </div>
                                <div class="d-flex justify-content-center">
                                    <p>
                                        <fmt:message key="Don`tHaveAnAccount?"/>
                                        <a href="registration.jsp"> <fmt:message key="createOne."/> </a>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
