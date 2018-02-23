<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>

        <link rel="shortcut icon" href="">
        <script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
        <script src="<c:url value="/resources/js/jquery.autocomplete.min.js" />"></script>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/pretraga.css" />" rel="stylesheet">

        <title>Pretraga kontakta</title>

        <style>

            .text-center .table-fill{
                color: white;
            }
            #back{
                position: absolute;
                top: 50%;
                left: 45%;
            }
        </style>

    </head>
    <body>

        <section class="webdesigntuts-workshop">
            <h2 style="color: white">Pretraga telefonskog imenika</h2>

            <c:forEach items="${kontaktModelii}" var="kontakt">                           
                <form:form action="kontaktPregled_1?kontaktID=${kontakt.kontaktID}">               
                    <input type="search" id="ime" placeholder="Traži ime...">		    	
                    <button id="rezultatIme" type='submit'>Traži</button>
                </form:form> 
                <form:form action="kontaktPregled?kontaktID=${kontaktID}">		    
                    <input type="search" id="brojTelefona" placeholder="Traži broj...">		    	
                    <button id="rezultatBroj" type='submit'>Traži</button>
                </form:form>
                <div id="back"> 
                    <input action="action" value="Vrati se na prethodnu stranu"  type="button" onclick="history.go(-1);" />   
                </div>
            </section>

        </c:forEach>

        <script>
            $(document).ready(function () {

                $('#ime').autocomplete({
                    serviceUrl: '${pageContext.request.contextPath}/traziKontakte',
                    paramName: "ime",
                    delimiter: ",",
                    transformResult: function (response) {
                        return {
                            suggestions: $.map($.parseJSON(response), function (item) {
                                return {value: item.ime, kontaktModeli: item.id};
                            })
                        };
                    }
                });
            });
        </script>

        <script>
            $(document).ready(function () {

                $('#brojTelefona').autocomplete({
                    serviceUrl: '${pageContext.request.contextPath}/traziKontakte2',
                    paramName: "brojTelefona",
                    delimiter: ",",
                    data: "",
                    transformResult: function (response) {
                        return {
                            suggestions: $.map($.parseJSON(response), function (item) {

                                return {value: item.brojTelefona, kontaktModeli: item.id};
                            })
                        };
                    },
                });
            });
        </script>

        <div id="info"> </div>

    </body>
</html>
