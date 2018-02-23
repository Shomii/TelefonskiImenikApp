<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="">
        <meta http-equiv="Content-Type" content="text/html;text/css charset=UTF-8">
        <title><spring:message code="label.viewContact_title" /></title>
        <spring:url value="/resources/css/kontaktPregled.css" var="pregledCss" />
        <link href="${pregledCss}" rel="stylesheet" />      
    </head>
    <body>

        <form:form commandName="prikaziKontakt">
            <div id="pricing-table" class="clear content">
                <div class="plan" style="margin-top:-80px"> 
                    <h3><spring:message code="label.viewContact_contact" />
                        <span>
                            <c:forEach items="${kontaktModelii}" var="slika">
                                <c:set var="kontaktID" value="${slika.kontaktID}"/>                       
                                <c:set var="kontaktID2" value="${prikaziKontakt.kontaktID}"/>

                                <c:if test="${kontaktID == kontaktID2}">

                                    <img class="user" src="<c:url value="resources/slike/${slika['kontaktSlika']}" />" alt="" />

                                </c:if>

                            </c:forEach>
                        </span>
                    </h3>

                    <a class="signup" ><h2 style="margin-bottom: -15px"><spring:message code="label.viewContact_name" /></h2><br/><form:input class="imeIPrezime" path="ime" name="ime" readonly="true"/> <form:input class="imeIPrezime" path="prezime" name="prezime" readonly="true"/></a>         
                    <ul>
                        <li><b><spring:message code="label.viewContact_email" /></b> <br/><form:input path="email" class="podaci" name="email" readonly="true"/> </li>
                        <li><b><spring:message code="label.viewContact_areacode" /></b><br/> <form:input path="pozivniBroj" class="podaci" name="pozivniBroj" readonly="true"/></li>
                        <li><b><spring:message code="label.viewContact_phonenum" /></b> <br/><form:input path="brojTelefona" class="podaci" name="brojTelefona" readonly="true"/></li>
                        <li><b><spring:message code="label.viewContact_adress" /></b> <br/><form:input path="adresa" class="podaci" name="adresa" readonly="true"/></li>
                        <li><b><spring:message code="label.viewContact_city" /></b> <br/><form:input path="grad" class="podaci" name="grad" readonly="true"/></li>
                        <li><b><spring:message code="label.viewContact_comment" /></b><br/> <form:input path="opis" class="podaci" name="opis" readonly="true"/></li>
                    </ul> 
                </div>
            </div>

        </form:form> 

    </body>
</html>