<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Greška</title>

        <style>
            #slika{
                position:relative;
                left:30%; 
            }

            #back{
                position:relative;
                top:10%; 
                left:9%;
            }    

        </style>

    </head>
    <body>
        <section id="slika"">
            <img src="<c:url value="/resources/img/greska.jpg" />" alt="" />
            <div id="back"> 

                <input action="action" value="Vrati se na prethodnu stranu"  type="button" onclick="history.go(-1);" />   
            </div>


        </section>
    </body>
</html>
