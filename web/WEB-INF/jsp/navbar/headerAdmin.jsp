
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
<div>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />


    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" 
               name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div class="bezAkcije" > <div class="adm"><h2>
                    <spring:message code="label.admin" /> : ${pageContext.request.userPrincipal.name}  
                </h2> </div></div>
        <div ><a href="javascript:formSubmit()" class="action-button shadow animate blue"><spring:message code="label.logoff" /></a> </div>
    </c:if>
</div>
<div class="container">
    <header class="ccheader">
        <h1 style="color: white"></h1>	

    </header>
    <div class="wrapper">

        <section class="ccblue section_navbar">	
            <div class="mainmenu">
                <ul>
                    <li><a href="index"><i class="icon-phone icon-large"></i>&nbsp;<main><spring:message code="label.header1" /></main><span><spring:message code="label.header1_2" /></span></a></li>
                    <li><a href="telefonskiImenik"><i class="icon-phone icon-large"></i>&nbsp;<main><spring:message code="label.adminHeader2" /></main><span><spring:message code="label.adminHeader2_2" /></span></a></li>
                    <li><a href="uIzradi"><i class="icon-user icon-large"></i>&nbsp;<main><spring:message code="label.header2" /></main><span><spring:message code="label.header2_2" /></span></a></li>
                    <li> <a href="noviKontakt"><i class="icon-gear icon-large"></i>&nbsp;<main><spring:message code="label.header3" /></main><span><spring:message code="label.header3_2" /></span></a></li> 
                    <li> <a href="admin"><i class="icon-gear icon-large"></i>&nbsp;<main><spring:message code="label.adminHeader3" /></main><span><spring:message code="label.adminHeader3_2" /></span></a></li>
                </ul>
            </div>
        </section>	
    </div>
</div>
<div id="lokalizacija" class="local">


    <a href="?lang=en" ><spring:message code="label.english" /></a>
    <a href="?lang=sr"><spring:message code="label.serbian" /></a>





</div>
