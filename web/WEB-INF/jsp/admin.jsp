
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="">
        <spring:url value="/resources/css/admin.css" var="adminCss" />
        <link href="${adminCss}" rel="stylesheet" />      
        <spring:url value="/resources/css/deleteDugme.css" var="deleteCss" />
        <link href="${deleteCss}" rel="stylesheet" />      
        <spring:url value="/resources/js/jquery-3.1.0.js" var="jquery" />
        <script src="${jquery}"></script>       
        <spring:url value="/resources/js/sweetalert.min.js" var="jsConfirm" />
        <script src="${jsConfirm}"></script>      
        <spring:url value="/resources/css/sweetalert.css" var="confirmCss" />
        <link href="${confirmCss}" rel="stylesheet" />

        <style>

            .animate
            {
                transition: all 0.1s;
                -webkit-transition: all 0.1s;
            }

            .action-button
            {
                position: absolute;
                left: -28%;
                top: -115%;
                padding: 10px 40px;
                margin: 0px 10px 10px 0px;
                float: left;
                border-radius: 10px;
                font-family: 'Pacifico', cursive;
                font-size: 17px;
                color: #FFF;
                text-decoration: none;	
            }

            .adm{
                position: absolute;
                left: 30px;
                top: -13px;

            }

            .header {
                max-width: 650px;
                width: 100%;
                margin: 0 auto;
                position: relative;
                left: -5%;
            }

            .bezAkcije{
                position: absolute;
                left: -80%;
                top: -115%;
                width: 300px;
                height: 24px;
                padding: 10px 10px;
                margin: 0px 10px 10px 0px;
                float: left;
                border-radius: 10px;
                font-family: 'Pacifico', cursive;
                text-align: center;
                vertical-align: middle;
                font-size: 15px;
                color: #FFF;
                text-decoration: none;
                background-color: #284B82;
                border-bottom: 5px solid #406AAD;
                text-shadow: 0px -2px #2980B9;

            }

            .blue
            {
                background-color: #284B82;
                border-bottom: 5px solid #406AAD;
                text-shadow: 0px -2px #2980B9;
            }

            .action-button:active
            {
                transform: translate(0px,5px);
                -webkit-transform: translate(0px,5px);
                border-bottom: 1px solid;
            }

            .table-title {
                color: #fafafa;
                font-size: 50px;
                font-weight: 400;
                text-align: center;
                font-style:normal;
                font-family: "Roboto", helvetica, arial, sans-serif;
                text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
                text-transform:uppercase;
            }

            #lokalizacija a{
                color: #f5f7f8;text-decoration: none;
            }

            #lokalizacija{
                position: absolute;
                left: 150%;
                top: -10%;
            }

            .local{             
                text-align: left;
                margin-top:8px;
                margin-right: 10px;
                padding:10px 10px 10px 10px;
                border-radius: 18px;
                font-size: 18px;
                background: #f5f7f8;
                background: -moz-linear-gradient(#6eb6de, #2685AA);
                background: -o-linear-gradient(#6eb6de, #2685AA);
                background: -webkit-linear-gradient(#6eb6de, #2685AA);
            }
        </style>
    </head>

    <body>



        <div class="table-title"> <spring:message code="label.adminList" /></div>

        <div class="header">
            <%@include file="navbar/headerAdmin.jsp" %>
        </div>

        <table class="table-fill" >
            <thead>
                <tr>
                    <th class="text-center"><spring:message code="label.adminName" /></th>
                    <th class="text-center"><spring:message code="label.adminLastname" /></th>
                    <th class="text-center"><spring:message code="label.adminEmail" /></th>
                    <th class="text-center"><spring:message code="label.adminUserName" /></th>
                    <th class="text-center"><spring:message code="label.adminPassword" /></th>
                    <th class="text-center"><spring:message code="label.adminActive" /></th>
                    <th class="text-center"><spring:message code="label.adminDelete" /></th>

                </tr>
            </thead>
            <tbody class="table-hover">
                <c:forEach items="${korisnici}" var="korisnik">

                    <c:set var="admin" value="${korisnik.username}"/>
                    <c:set var="admin2" value="admin"/>
                    <c:if test="${admin != admin2}">
                        <tr>
                            <td class="text-center">${korisnik["ime"]}</td>
                            <td class="text-center">${korisnik["prezime"]}</td>
                            <td class="text-center">${korisnik["email"]}</td>
                            <td class="text-center">${korisnik["username"]}</td>
                            <td class="text-center">${korisnik["datumKreiranja"]}</td>

                            <c:choose>
                                <c:when test="${korisnik.enabled == true}">
                                    <c:set var="aktiviranKorisnik"><spring:message code="label.adminActiveUser" /></c:set>
                                    <c:set var="aktivan" value="${aktiviranKorisnik}"/>                           
                                    <td class="text-center">${aktivan}</td>
                            <br />
                        </c:when>    
                        <c:otherwise>
                            <c:set var="neAktiviranKorisnik"><spring:message code="label.adminNonActiveUser" /></c:set>
                            <c:set var="aktivan" value="${neAktiviranKorisnik}"/>
                            <td class="text-center" style="color: red">${aktivan}</td>
                            <br />
                        </c:otherwise>
                    </c:choose>


                    <form:form class="formaZaBrisanje" id="brisanje" action="brisanjeKorisnika?id=${korisnik.id}" >

                        <td> <section> <button class="delete delete_button button_tds" ><spring:message code="label.adminDeleteButton" /></button> </section> </td>

                    </form:form> 

                </tr>

            </c:if>
        </c:forEach>
    </tbody>

</table>


<script>


    $(".formaZaBrisanje").submit(function (e) {
        e.preventDefault();


        var form = this;
        e.preventDefault();
        swal({
            title: "Da li ste sigurni",
            text: "da hoćete da obrišete ovog korisnika ?",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: '#DD6B55',
            confirmButtonText: 'Da, obriši!',
            cancelButtonText: "Otkaži brisanje!",
            closeOnConfirm: false,
            closeOnCancel: false
        },
                function (isConfirm) {
                    if (isConfirm) {
                        swal({
                            title: 'Obrisano!',
                            text: 'Korisnik je obrisan!',
                            type: 'success'
                        }, function () {
                            form.submit();
                        });

                    } else {
                        swal("Otkazano", "Brisanje korisnika je otkazano", "error");
                    }
                });
    });
</script>
</body>

</html>