<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="container">

    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <spring:url value="/resources/css/padajuciMeni.css" var="meniCss" />
    <link href="${meniCss}" rel="stylesheet" />
    <spring:url value="/resources/js/padajuciMeni.js" var="padajuciMeni" />
    <script src="${padajuciMeni}"></script>


    <ul class="dark_menu">

        <li>
            <a ><spring:message code="label.lang" /></a>

            <ul>
                <li><a href="?lang=en"><spring:message code="label.english" /></a></li>
                <li><a href="?lang=sr"><spring:message code="label.serbian" /></a></li>

            </ul>

        </li>

    </ul>

</div>
