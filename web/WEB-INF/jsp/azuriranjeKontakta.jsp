
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="label.pageTitle4" /></title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <spring:url value="/resources/css/noviKontakt.css" var="noviKontaktCss" />
        <link href="${noviKontaktCss}" rel="stylesheet" />
        <spring:url value="/resources/js/noviKontakti.js" var="noviKontakti" />
        <script src="${noviKontakti}"></script>
        <spring:url value="/resources/js/jquery-3.1.0.js" var="jquery" />
        <script src="${jquery}"></script>

        <script type="text/javascript">
            function PreviewImage(no) {
                var oFReader = new FileReader();
                oFReader.readAsDataURL(document.getElementById("uploadImage" + no).files[0]);
                oFReader.onload = function (oFREvent) {
                    document.getElementById("uploadPreview" + no).src = oFREvent.target.result;
                };
            }

        </script>

    </head>
    <body>    

        <div id="wrapper">


            <form:form action="azuriranjeKontakta" method="post" name="editForma" commandName="azuriraniKontakt" enctype="multipart/form-data" onsubmit="return validateForm()">
                <form:hidden path="kontaktID" />
                <fieldset>
                    <legend><spring:message code="label.pageTitle4-2" /></legend>
                    <div>
                        <form:input path="ime" type="text" id="ime" name="ime" placeholder="Ime" onchange="validateLength(this, 3)"/><br/>
                        <p id="imeGreska" style="color: red"> </p>
                        <form:errors path="ime" />
                    </div>
                    <div>
                        <form:input path="prezime" type="text" name="prezime" placeholder="Prezime" onchange="validateLength(this, 5)"/><br/>
                        <p id="prezimeGreska" style="color: red"> </p>
                        <form:errors path="prezime" />
                    </div>
                    <div>
                        <form:input path="email" type="text" name="email" placeholder="Email" onchange="validateEmail(this)"/><br/>
                        <p id="emailGreska" style="color: red"> </p>
                        <form:errors path="email" />
                    </div>

                    <div>
                        <form:input path="pozivniBroj" id="pozivniBroj" type="text" name="pozivniBroj" placeholder="Pozivni broj" onchange="brojevi(this)"/><br/>
                        <p id="pozivniBrojGreska" style="color: red"> </p>
                        <form:errors path="pozivniBroj" />
                    </div>

                    <div>
                        <form:input path="brojTelefona" type="text" name="brojTelefona" placeholder="Broj telefona" onchange="brojevi(this)"/><br/>
                        <p id="brojTelefonaGreska" style="color: red"> </p>
                        <form:errors path="brojTelefona" />
                    </div>

                    <div>
                        <form:input path="adresa" type="text" name="Adresa" placeholder="Adresa" onchange="validateLength(this, 5)"/><br/>
                        <p id="adresaGreska" style="color: red"> </p>
                        <form:errors path="adresa" />
                    </div>

                    <div>
                        <form:input path="grad" type="text" name="Grad" placeholder="Grad" onchange="validateLength(this, 3)"/><br/>
                        <p id="gradGreska" style="color: red"> </p>
                        <form:errors path="grad" />
                    </div>           
                    <div> 
                        <spring:message code="label.comment" var="title8" /> 
                        <form:textarea  path="opis" name="opis" placeholder="${title8}"></form:textarea><br/>
                        <form:errors path="opis" />
                    </div>

                    <p><spring:message code="label.newContact_picture" /></p>

                    <img id="uploadPreview1" src="http://i111.photobucket.com/albums/n153/terrapins_sky/plantonic/no_image_zpsa9af9c43.gif" width="100"/><br />
                    <input id="uploadImage1" type="file" name="projectImages" value="Ucitaj sliku" onchange="PreviewImage(1);"/>

                    <div></div>

                    <form:button type="submit" value="Pošalji" id="zatvori" class="zatvori btn btn-primary btn-block btn-large"> <spring:message code="label.updatingContact" /> </form:button>
                    <form:button value="Pošalji" class="zatvori btn btn-primary btn-block btn-large" onclick="history.go(-1); return false;">  <spring:message code="label.cancel" /> </form:button>
                    </fieldset>    
            </form:form>
        </div>



        <script>
            $(document).ready(function () {
                $(".zatvori").click(function () {
                    parent.closeIFrame();
                });
            });
        </script>

    </body>
</html>
