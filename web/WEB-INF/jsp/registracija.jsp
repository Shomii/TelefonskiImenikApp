
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
        <title><spring:message code="label.pageTitle2"/> </title>
        <spring:url value="/resources/css/registracija.css" var="registracijaCss" />
        <link href="${registracijaCss}" rel="stylesheet" />
        <spring:url value="/resources/js/proveraRegistracije.js" var="proveraRegistracije" />
        <script src="${proveraRegistracije}"></script>
    </head>

    <body> 

        <div class="container">
            <form:form id="contact" name="registracionaForma" method="POST" action="registracija" commandName="noviKorisnik"  onsubmit="return validateForm()">
                <form:hidden path ="datumKreiranja" readonly="" class="form-control" value="${datum}" />
                <h4><spring:message code="label.registration1" /></h4>

                <fieldset>
                    <c:set var="title"><spring:message code="label.registration2"/></c:set>
                    <form:input path="ime" placeholder="${title}" type="text" id="ime" name="ime" onchange="validateLength(this, 3)" /> <br/>
                    <p id="imeGreska" style="color: red"> </p>
                    <form:errors path="ime" />
                </fieldset>

                <fieldset >
                    <c:set var="title2"><spring:message code="label.registration3"/></c:set>
                    <form:input path="prezime" placeholder="${title2}" type="text" onchange="validateLength(this, 5)" /> <br/>
                    <p id="prezimeGreska" style="color: red"> </p>
                    <form:errors path="prezime" />
                </fieldset>

                <fieldset>
                    <c:set var="title3"><spring:message code="label.email"/></c:set>
                    <form:input path="email"  placeholder="${title3}" type="email" onchange="validateEmail(this)" /><br/>
                    <p id="imeGreskaEmail" style="color: red"> </p>
                    <form:errors path="email" />
                    <span style="color: red"> ${emailError}</span>
                </fieldset>


                <fieldset>
                    <c:set var="title4"><spring:message code="label.username"/></c:set>
                    <form:input path="username" placeholder="${title4}" type="text" name="username" onchange="validateLength(this, 3 )" /><br/>
                    <p id="usernameGreska" style="color: red"> </p>
                    <form:errors path="username" />
                    <span style="color: red"> ${usernameError}</span>

                </fieldset>

                <fieldset>
                    <c:set var="title5"><spring:message code="label.password"/></c:set>
                    <form:password path="password" placeholder="${title5}" onchange="validateLength(this, 3 )" /><br/>
                    <p id="passwordGreska" style="color: red"> </p>
                    <form:errors path="password" />
                </fieldset>

                <fieldset>
                    <c:set var="title6"><spring:message code="label.retypePassword"/></c:set>
                    <form:password path="retypePassword" placeholder="${title6}" onchange="validateLength(this, 3 )" /><br/>
                    <p id="passwordGreska" style="color: red"> </p>
                    <form:errors path="password" />
                    <span style="color: red"> ${passwordDontMatch}</span>
                </fieldset>

                <fieldset>
                    <c:set var="title6"><spring:message code="label.registration4"/></c:set>
                    <form:button type="submit" class="btn btn-primary btn-block btn-large" data-submit="...Sending"> ${title6} </form:button>
                    </fieldset>
            </form:form>
        </div>

    </body>

</html>