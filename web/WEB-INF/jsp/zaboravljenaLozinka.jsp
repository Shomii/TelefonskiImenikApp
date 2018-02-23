
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="label.forgotPassword"/> </title>
        <spring:url value="/resources/css/registracija.css" var="registracijaCss" />
        <link href="${registracijaCss}" rel="stylesheet" />
        <spring:url value="/resources/js/proveraPromeneLozinke.js" var="proveraPromeneLozinke" />
        <script src="${proveraPromeneLozinke}"></script>
    </head>

    <body> 

        <div class="container">
            <form:form id="contact" name="zaboravljenaLozinka" method="POST" action="zaboravljenaLozinka" commandName="zaboravljenaLozinka"  onsubmit="return validateForm()">
                <form:hidden path="id" />
                <h4><spring:message code="label.forgotPassword" /></h4>

                <p> <spring:message code="label.forgotPasswordText"/> <strong>  </strong> </p>

                <fieldset> </fieldset>
                <fieldset> </fieldset>

                <fieldset>
                    <c:set var="title6"><spring:message code="label.enterEmail"/></c:set>
                    <form:input path="emailReset" placeholder="${title6}" onchange="validateLength(this, 3 )" /><br/>

                    <p id="passwordGreska" style="color: red"> </p>
                    <form:errors path="password" />
                    <span style="color: red"> ${emailDontMatch}</span>
                </fieldset>

                <fieldset>
                    <c:set var="title6"><spring:message code="label.sendEmailForgotPassword"/></c:set>
                    <form:button type="submit" class="btn btn-primary btn-block btn-large" data-submit="...Sending"> ${title6} </form:button>
                    </fieldset>
            </form:form>
        </div>

    </body>

</html>