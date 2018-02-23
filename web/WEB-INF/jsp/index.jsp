<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>
        <link rel="shortcut icon" href="">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="label.pageTitle" /></title>
        <spring:url value="/resources/css/index.css" var="indexCss" />
        <link href="${indexCss}" rel="stylesheet" />
    </head>
    <c:url value="${pageContext.request.contextPath}/j_spring_security_check" var="loginUrl"/>
    <body onload='document.loginForm.username.focus();'>
        <div id="locale">
            <%@include file="padajuciMeni.jsp" %>
        </div>

        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.authenticated}"><br/><br/>
            </c:when>
            <c:otherwise>
                <div id="naslov">
                    <h1 > <spring:message code="label.title" /> </h1>             

                </div>
                <div class="login" id="login-box">
                    <h1><spring:message code="label.loginHead" /></h1>
                    <%@include file="navbar/message.jsp" %>
                    <form method="post" name='loginForm' action="${loginUrl}" method='POST'>                
                        <input type="text"  placeholder="<spring:message code="label.username" />" name='username' required="required" />
                        <input type="password"  placeholder="<spring:message code="label.password" />" name='password' required="required" />
                        <button type="submit" class="btn btn-primary btn-block btn-large"><spring:message code="label.login" /></button>
                        <div id="noviKorisnik">
                            <h4 > <spring:message code="label.footer1" /><a href="${pageContext.request.contextPath}/registracija"> <spring:message code="label.footer2" /></a></h4> 
                            <h4 > <a href="${pageContext.request.contextPath}/zaboravljenaLozinka"> <spring:message code="label.forgotPassword" /></a></h4>
                        </c:otherwise>
                    </c:choose>                          
                    <sec:authorize access="isAuthenticated()">
                        <div id="noviKorisnik" style="position: relative;left: 40%;">
                            <p style="color: white;width: 400px;font-size: 30px;">Trenutno ste ulogovani kao korisnik <strong>"<sec:authentication property="principal.username" />"</strong>, pređi na </p>
                            <p style="color: red;width: 400px;">  <a style="font-style: italic;font-size: 30px;" href="${pageContext.request.contextPath}/telefonskiImenik">Telefonski Imenik</a></p>
                        </div>
                    </sec:authorize>

                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
        </div>

        <div >
            <%@include file="footer.jsp" %>
        </div>

    </body>

</html>
