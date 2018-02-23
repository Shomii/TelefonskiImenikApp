
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="">
        <title>Pregled telefonskog imenika</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">-->
        <!-- css -->
        <spring:url value="/resources/css/telefonskiImenik.css" var="imenikCss" />
        <link href="${imenikCss}" rel="stylesheet" />      
        <spring:url value="/resources/css/deleteDugme.css" var="deleteCss" />
        <link href="${deleteCss}" rel="stylesheet" />
        <spring:url value="/resources/css/sweetalert.css" var="confirmCss" />
        <link href="${confirmCss}" rel="stylesheet" />      
        <spring:url value="/resources/css/jquery.dataTables.min.css" var="datatablesCss" />
        <link href="${datatablesCss}" rel="stylesheet" />
        <spring:url value="/resources/css/font-awesome.min.css" var="fontAwesomeCss" />
        <link href="${fontAwesomeCss}" rel="stylesheet" />
        <spring:url value="/resources/css/fixedHeader.dataTables.min.css" var="datatablesFixedCss" />
        <link href="${datatablesFixedCss}" rel="stylesheet" />
        <!-- js -->
        <spring:url value="/resources/js/jquery-3.1.0.js" var="jquery" />
        <script src="${jquery}"></script>        
        <spring:url value="/resources/js/sweetalert.min.js" var="jsConfirm" />
        <script src="${jsConfirm}"></script>
        <spring:url value="/resources/js/jquery.cluetip.js" var="jsClueTip" />
        <script src="${jsClueTip}"></script>            
        <spring:url value="/resources/js/jquery.dataTables.min.js" var="datatablesJS" />
        <script src="${datatablesJS}"></script>
        <spring:url value="/resources/js/dataTables.fixedHeader.min.js" var="datatablesFixedJS" />
        <script src="${datatablesFixedJS}"></script>

        <style>

            #flake {
                color: #fff;
                position: absolute;
                font-size: 25px;
                top: -50px;
            }

            .animate
            {
                transition: all 0.1s;
                -webkit-transition: all 0.1s;
            }

            .action-button
            {
                position: absolute;
                left: -53%;
                top: -9%;
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

            .bezAkcije{
                position: absolute;
                left: -108%;
                top: -10%;
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

            .action-button:active
            {
                transform: translate(0px,5px);
                -webkit-transform: translate(0px,5px);
                border-bottom: 1px solid;
            }  

            tr:nth-child(1) > th{cursor:n-resize;}
            input{height:24px;display:inline-block;padding:0px;margin:0px;text-align:center;padding:0px 4px;}
            .filtered{background-color:#FFCCCC;}


            .naslov{
                display: block;
                margin: auto;
                max-width: 600px;
                padding:5px;
                width: 100%;
                color: #fafafa;
                font-size: 40px;
                font-weight: 400;
                font-style:normal;
                font-family: "Roboto", helvetica, arial, sans-serif;
                text-shadow: -1px -1px 1px rgba(0, 0, 0, 0.1);
                text-transform:uppercase;
                /*  position: inherit;
                  left: 50%;*/
            }

            #element_to_pop_up { 
                background-color:#fff;
                border-radius:15px;
                color:#000;
                display:none; 
                padding-top: 25px;
                min-width:950px;
                min-height: 800px;
            }
            .b-close{
                cursor:pointer;
                position:absolute;
                right:10px;
                top:5px;
                color: red;
            }

            .ui-dialog {
                left: 85% !important;
                top: 18% !important;
                margin-left: -175px !important; 
                margin-top: -175px !important; 
            } 

            .user {
                display: inline-block;
                width: 100px;
                height: 100px;
                border-radius: 50%;
                background-repeat: no-repeat;
                background-position: center center;
                background-size: cover;
            }

            .cluetip-rounded {
                background: transparent url(bl.gif) no-repeat 0 100%;
                margin-top: 10px;
            }

            .cluetip-rounded #cluetip-outer {
                background: transparent url(tl.gif) no-repeat 0 0;
                margin-top: -12px;
            }

            .cluetip-rounded #cluetip-title {
                background: transparent url(tr.gif) no-repeat 100% 0;
                padding: 12px 12px 0;
                margin: 0 -12px 0 0;
                position: relative;
            }
            .cluetip-rounded #cluetip-inner {
                background: url(br.gif) no-repeat 100% 100%;
                padding: 5px 12px 12px;
                margin: 0 -12px 0 0;
                position: relative;
            }

            .cluetip-rounded span#cluetip-close { 
                text-align: right;
                margin: 0 5px 5px;
                color: #009;
                background: transparent;
            }
            .cluetip-rounded span#cluetip-close a {
                color: #777;
            }
            /* stupid IE6 HasLayout hack */
            .cluetip-rounded #cluetip-title,
            .cluetip-rounded #cluetip-inner {
                zoom: 1;
            }

            #lokalizacija a{
                color: #f5f7f8;text-decoration: none;
            }

            #lokalizacija{
                position: absolute;
                left: 130%;
                top: -10%;
            }

            .dropbtn {
                /*    background-color: #4CAF50;*/
                color: #f5f7f8;
                padding: 16px;
                font-size: 21px;
                /*    border-radius: 18px;*/
                border: none;
                cursor: pointer;
                background: #f5f7f8;
                background: -moz-linear-gradient(#6eb6de, #6eb6de);
                background: -o-linear-gradient(#6eb6de, #6eb6de);
                background: -webkit-linear-gradient(#6eb6de, #6eb6de);
            }

            .dropbtn:hover, .dropbtn:focus {
                background-color: #3e8e41;
            }

            .dropdown {
                position: absolute;
                display: inline-block;
                left: 110%;
                top:0%;


            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #6eb6de;
                min-width: 160px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {background-color: #f1f1f1}

            .show {display:block;}
        </style>

    </head>

    <body>

        <div class="headerNav">
            <%@include file="navbar/header.jsp" %>
        </div>

        <div id="wrapper" style="width: 70%;" style="float: left;">
            <table class="tablesorter-blackice" id="tablesorter" data-page-length='5' style="float: left;">
                <thead>
                <div class="naslov"> <spring:message code="label.title" /> </div>

                <p class="text" style="color: white;position: absolute;top: 13%;left: 3%;"><spring:message code="label.table_1" /></p>
                <p class="text" style="color: white;position: absolute;top: 13%;left: 53%;"><spring:message code="label.table_2" /></p>
                <tr>
                    <th  class="fh-fixedHeader" style="color:#406AAD" data-orderable="false"><spring:message code="label.picture" /></th>
                    <th class="fh-fixedHeader" style="color:#406AAD"><spring:message code="label.name" />   <!--<input id="searchInput" value="Type To Filter"> --> </th>                
                    <th class="fh-fixedHeader" style="color:#406AAD"><spring:message code="label.lastname" /> </th>
                    <th class="fh-fixedHeader" style="color:#406AAD"><spring:message code="label.phoneNumber" /></th>
                    <th class="fh-fixedHeader" style="color:#406AAD" data-orderable="false"> <spring:message code="label.viewContact" /> </th>
                    <th class="fh-fixedHeader" style="color:#406AAD" data-orderable="false"><spring:message code="label.update" /></th>
                    <th class="fh-fixedHeader" style="color:#406AAD" data-orderable="false"><spring:message code="label.deleteContact" /></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${kontakti}" var="kontakt">                       
                        <c:set var="korisnikZaImenik" value="${kontakt.korisnikZaImenik.id}"/>                               
                        <c:if test="${korisnikZaImenik == korisnikID}">                       

                            <tr><td><img class="user" src="<c:url value="resources/slike/${kontakt['kontaktSlika']}" />" alt="" /></td>                        
                                <td class="clickable-row" data-href='${pageContext.request.contextPath}/kontaktPregled?kontaktID=${kontakt.kontaktID}'>${kontakt["ime"]}</td>
                                <td class="use-address" id="sticky" href="${pageContext.request.contextPath}/kontaktPregled?kontaktID=${kontakt.kontaktID}" rel="ajax6.htm" id="nr">${kontakt["prezime"]}</td>   
                                <td class="my-button">${kontakt["brojTelefona"]}</td>                            
                                <td style="width: 15%;">
                                    <a href="${pageContext.request.contextPath}/kontaktPregled?kontaktID=${kontakt.kontaktID}" class="openpop azuriranje azuriranje_button" style="margin-top: 20px;"><spring:message code="label.viewContact" /></a><br>
                                </td>
                                <td style="width: 15%;">                                
                                    <a href="${pageContext.request.contextPath}/azuriranjeKontakta?kontaktID=${kontakt.kontaktID}" class="openpop2 azuriranje azuriranje_button" style="margin-top: 20px;overflow: auto;"><spring:message code="label.update" /></a><br>
                                </td>

                                <form:form id="brisanje" class="formaZaBrisanje" action="brisanjeKontakta?kontaktID=${kontakt.kontaktID}">
                                    <td  style="width: 10%;"> <section  > <button class="delete delete_button button_tds" style="width: 70%; margin-top: 15px;"><spring:message code="label.delete" /></button> </section> </td>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
                        </form:form>  
                        </tr>  
                    </c:if>
                </c:forEach> 
                </tbody>
            </table>
            <a href="${pageContext.request.contextPath}/noviKontakt" class="openpop2 azuriranje azuriranje_button" style="margin-top: 20px;overflow: auto;width: 90px;align-self: center;"><spring:message code="label.table_newContact" /></a><br>
        </div>  

        <div style='display: block;color: white;position: absolute;left: 85%;top: 20%;'>  

            <div style="width: 150px;" id="lokalizacija" class="local">         
                <p>Trenutno telefonski imenik koristi ${total} korisnik-a</p>
                <p style="color: #003eff;">
                    <c:forEach items="${users}" var="user">       
                        <c:out value="${user.username}" /><br/><br/>            
                    </c:forEach>
                </p>
            </div>
        </div>

        <script>
            $(".formaZaBrisanje").submit(function (e) {
                e.preventDefault();


                var form = this;
                e.preventDefault();
                swal({
                    title: "Da li ste sigurni",
                    text: "da hoćete da obrišete ovaj kontakt ?",
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
                                    text: 'Kontakt je obrisan!',
                                    type: 'success'
                                }, function () {
                                    form.submit();
                                });
                            } else {
                                swal("Otkazano", "Brisanje kontakta je otkazano", "error");
                            }
                        });
            });
        </script>

        <script>
            jQuery(document).ready(function ($) {
                $(".clickable-row").click(function () {
                    window.document.location = $(this).data("href");
                });
            });
        </script>

        <script>

            function myFunction() {
                document.getElementById("myDropdown").classList.toggle("show");
            }

            window.onclick = function (event) {
                if (!event.target.matches('.dropbtn')) {

                    var dropdowns = document.getElementsByClassName("dropdown-content");
                    var i;
                    for (i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }
        </script>

        <script>
            var odabranaKlasa = document.getElementsByClassName('popup');
            var modal = odabranaKlasa[0];
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>




        <script>
            $(document).ready(function () {
                var tabela = $('#tablesorter').dataTable({
                    fixedHeader: true,
                    "bLengthChange": true,
                    "bFilter": true,
                    "iDisplayLength": 5,
                    "sDom": '<"H"lfr>t<"F"ip>',
                    "oLanguage": {
                        "sUrl": "/resources/dataTables.txt"
                    }
                });
            });
        </script>

        <script>
            $(document).ready(function () {
                $(".test").click(function () {
                    $("#thedialog").attr('src', $(this).attr("href"));
                    $("#somediv").dialog({
                        width: 320,
                        height: 960,
                        modal: true,
                        close: function () {
                            $("#thedialog").attr('src', "about:blank");
                        }
                    });
                    return false;
                });
            });
        </script>






        <script type="text/javascript" src="http://arrow.scrolltotop.com/arrow2.js"></script>





        <style>  
            .close{
                cursor:pointer;
                position:absolute;
                right:44%;
                top:5%;
                transition: all 200ms;
                font-size: 20px;
                font-weight: bold;
                text-decoration: none;
                color: #fff;

            }

            .close:hover {
                color: #3e8e41;
                font-size: 25px;
            }
            .close2{
                /*position: absolute; right: 32%;top: 5%; margin: -8px 0 0 -12px; width: 20px; height: 20px; color: #fff; font-size: 14px; font-weight: bold; text-align: center; border-radius: 50%; background-color: #5c5c5c; cursor: pointer;*/
                cursor:pointer;
                position:absolute;
                right:32%;
                top:3%;
                transition: all 200ms;
                font-size: 20px;
                font-weight: bold;
                text-decoration: none;
                color: #fff;
            }

            .close2:hover {
                color: #3e8e41;
                font-size: 25px;
            }
        </style>

        <script>
                    $(document).ready(function () {
                        $(".popup").hide();
                        $(".openpop").click(function (e) {
                            e.preventDefault();
                            $("iframe").attr("src", $(this).attr('href'));
                            $(".links").fadeOut('slow');
                            $(".popup").fadeIn('slow');
                            //$("#wrapper").fadeTo(700, 0.3).css('display', 'block');
                            $("#wrapper, .container, .bezAkcije, .dropdown, .blue, .text").fadeTo(700, 0.7).css({"-webkit-filter": "blur(3px)", "-moz-filter": "blur(3px)", "-o-filter": "blur(3px)", "-ms-filter": "blur(3px)", "filter": "blur(3px)"});
                        });

                        $(".close").click(function () {
                            $(this).parent().fadeOut("slow");
                            $(".links").fadeIn("slow");
        //        $("#wrapper").fadeIn('slow');
                            //$("#wrapper").fadeTo(700, 1.0).css('display', 'block');
                            $("#wrapper, .container, .bezAkcije, .dropdown, .blue, .text").fadeTo(700, 1.0).css({"-webkit-filter": "blur(0px)", "-moz-filter": "blur(0px)", "-o-filter": "blur(0px)", "-ms-filter": "blur(0px)", "filter": "blur(0px)"});
                        });
                    });
        </script>

        <script>
            $(document).ready(function () {
                $(".popup2").hide();
                $(".openpop2").click(function (e) {
                    e.preventDefault();
                    $("iframe").attr("src", $(this).attr('href'));
                    $(".links").fadeOut('slow');
                    $(".popup2").fadeIn('slow');
                    //$("#wrapper").fadeTo(700, 0.3).css('display', 'block');
                    $("#wrapper, .container, .bezAkcije, .dropdown, .blue, .text").fadeTo(700, 0.7).css({"-webkit-filter": "blur(3px)", "-moz-filter": "blur(3px)", "-o-filter": "blur(3px)", "-ms-filter": "blur(3px)", "filter": "blur(3px)"});
                });

                $(".close2").click(function () {
                    $(this).parent().fadeOut("slow");
                    $(".links").fadeIn("slow");
                    //$("#wrapper").fadeTo(700, 1.0).css('display', 'block');
                    $("#wrapper, .container, .bezAkcije, .dropdown, .blue, .text").fadeTo(700, 1.0).css({"-webkit-filter": "blur(0px)", "-moz-filter": "blur(0px)", "-o-filter": "blur(0px)", "-ms-filter": "blur(0px)", "filter": "blur(0px)"});
                });
            });
        </script>



        <div class="wrapper2">
            <div class="popup">
                <iframe id="iframe" scrolling="no" border="no" style="min-width:5%;min-height:100%;padding: 10px;padding-right: 3px;position: absolute;top: 2%;left: 40%;margin-top: 20px;margin-bottom: 30px;
                        -moz-border-radius: 12px;-webkit-border-radius: 12px;border-radius: 12px;-moz-box-shadow: 4px 4px 14px #000;-webkit-box-shadow: 4px 4px 14px #000;box-shadow: 0px 0px 10px 5px white;
                        /*background-color: #DDD5B6;*/
                        background-image: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
                        background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                        background-image: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                        background-image: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                        background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);">
                    <p>Your browser does not support iframes.</p>

                </iframe>
                <a href="#" class="close">X</a>

            </div>
        </div>

        <div class="wrapper3">
            <div class="popup2">
                <iframe id="iframe2" src="" scrolling="yes" border="no" style="min-width:550px;min-height:850px;padding: 5px;position: absolute;top: 2%;left: 40%; margin-top: 1px;margin-bottom: 1px;
                        -moz-border-radius: 12px;-webkit-border-radius: 12px;border-radius: 12px;-moz-box-shadow: 4px 4px 14px #000;-webkit-box-shadow: 4px 4px 14px #000;box-shadow: 0px 0px 10px 5px white;

                        background-image: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
                        background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                        background-image: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                        background-image: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                        background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
                        /*box-shadow: 0px 0px 10px 5px white;*/       
                        ">
                    <p>Your browser does not support iframes.</p>
                </iframe>
                <a href="#" class="close2">X</a>

            </div>
        </div>


        <script>
            $(document).on('keyup', function (evt) {
                if (evt.keyCode == 27) {
                    parent.closeIFrame2();
                }
            });
        </script>



        <script>
            function closeIFrame2() {
                $("iframe").hide();
                //$("#wrapper").fadeTo(700, 1.0).css('display', 'block');
                $("#wrapper, .container, .bezAkcije, .dropdown, .blue, .text").fadeTo(700, 1.0).css({"-webkit-filter": "blur(0px)", "-moz-filter": "blur(0px)", "-o-filter": "blur(0px)", "-ms-filter": "blur(0px)", "filter": "blur(0px)"});
                setTimeout("location.href = 'http://localhost:8080/TelefonskiImenikApp_5_11_27_1/telefonskiImenik'", 800);
            }
        </script>

        <script>
            function closeIFrame() {
                $('#iframe2').hide();
                //$("#wrapper").fadeTo(700, 1.0).css('display', 'block');
                $("#wrapper, .container, .bezAkcije, .dropdown, .blue, .text").fadeTo(700, 1.0).css({"-webkit-filter": "blur(0px)", "-moz-filter": "blur(0px)", "-o-filter": "blur(0px)", "-ms-filter": "blur(0px)", "filter": "blur(0px)"});

                setTimeout("location.href = 'http://localhost:8080/TelefonskiImenikApp_5_11_27_1/telefonskiImenik'", 2000);
            }
        </script>

        <script>
            var iframe = $('#iframe').contents();
            iframe.find("wrapper").click(function () {
                alert("test");
            });
        </script>




        <style> 
            a.tooltipKorisnik {outline:none; }
            a.tooltipKorisnik strong {line-height:30px;}
            a.tooltipKorisnik:hover {text-decoration:none;} 
            a.tooltipKorisnik #span {
                z-index:10;display:none; padding:14px 20px;
                margin-top:60px; margin-left:-550px;
                width:300px; line-height:16px;
            }
            a.tooltipKorisnik:hover #span{
                display:inline; position:absolute;
                border:2px solid #FFF;  color:#EEE;
                /*    background:#333 url(resources/images/css-tooltip-gradient-bg.png) repeat-x 0 0;*/
                background-image: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
                background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background-image: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background-image: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
                /*box-shadow: 0px 0px 10px 5px white;*/  
            }
            .callout {z-index:20;position:absolute;border:0;top:-14px;left:20px;}

            /*CSS3 extras*/
            a.tooltipKorisnik #span{
                border-radius:2px;        
                box-shadow: 0px 0px 8px 4px #666;
                /*    opacity: 0.8;
                    position: absolute;
                    left: 50px;*/
            } 
        </style>

    </body>
</html>
