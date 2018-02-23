<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kontaktiraj administratore</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
        <style>
            body{
                width: 100%;
                height:100%;
                font-family: 'Open Sans', sans-serif;
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-image: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
                background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background-image: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background-image: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background-image: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
                /*filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 );*/
            }  


            #contact{
                padding:10px 0 10px;
            }

            .contact-text{
                margin:45px auto;
            }

            .mail-message-area{
                width:100%;
                padding:0 15px;
            }

            .mail-message{
                width: 100%;
                background:rgba(255,255,255, 0.8) !important;
                -webkit-transition: all 0.7s;
                -moz-transition: all 0.7s;
                transition: all 0.7s;
                margin:0 auto;
                border-radius: 0;
            }

            .not-visible-message{
                height:0px;
                opacity: 0;
            }

            .visible-message{
                height:auto;
                opacity: 1;
                margin:25px auto 0;
            }

            .form{
                width: 100%;
                padding: 15px;
                background:#f8f8f8;
                border:1px solid rgba(0, 0, 0, 0.075);
                margin-bottom:25px;
                color:#007fff !important;
                font-size:17px;
                -webkit-transition: all 0.4s;
                -moz-transition: all 0.4s;
                transition: all 0.4s;
            }

            .form:hover{
                border:2px solid black;
            }

            .form:focus{
                color: white;
                outline: none;
                border:1px solid #8BC3A3;
            }

            .textarea{
                height: 200px;
                max-height: 200px;
                max-width: 100%;
            }

            .button{
                padding:8px 12px;
                background:#0A5175;
                display: block;
                width:120px;
                margin:10px 0 0px 0;
                border-radius:3px;
                -webkit-transition: all 0.3s;
                -moz-transition: all 0.3s;
                transition: all 0.3s;
                text-align:center;
                font-size:0.8em;
                box-shadow: 0px 1px 4px rgba(0,0,0, 0.10);
                -moz-box-shadow: 0px 1px 4px rgba(0,0,0, 0.10);
                -webkit-box-shadow: 0px 1px 4px rgba(0,0,0, 0.10);
            }

            .button:hover{
                background:red;
                color:white;
            }

            .form-btn{
                width:180px;
                display: block;
                height: auto;
                padding:15px;
                background:#6eb6de;
                color: white;
                border:none;
                border-radius:3px;
                outline: none;
                -webkit-transition: all 0.3s;
                -moz-transition: all 0.3s;
                transition: all 0.3s;
                margin:auto;
                box-shadow: 0px 1px 4px rgba(0,0,0, 0.10);
                -moz-box-shadow: 0px 1px 4px rgba(0,0,0, 0.10);
                -webkit-box-shadow: 0px 1px 4px rgba(0,0,0, 0.10);
            }

            .form-btn:hover{

                color:#6eb6de;
                background:white;
                border:none;
            }

            .form-btn:active{
                opacity: 0.9;
            }
            center{
                margin-top:330px;
            }
            input {
                position: relative;
                z-index: 9999;
            }

            ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
                color:    #007fff;
            }
            :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
                color:    #007fff;
                opacity:  1;
            }
            ::-moz-placeholder { /* Mozilla Firefox 19+ */
                color:    #007fff;
                opacity:  1;
            }
            :-ms-input-placeholder { /* Internet Explorer 10-11 */
                color:    #007fff;
            }
            ::-ms-input-placeholder { /* Microsoft Edge */
                color:    #007fff;
            }

        </style>

    </head>
    <body>

        <div style="position: absolute;left: 36%;top: 1%;">

            <h3 style="color: white;alignment-adjust: central;">Kontaktirajte nas sa bilo kojim pitanjem !</h3>

        </div>
        <div class="inner contact" style="margin-top: 100px;">

            <div class="contact-form">

                <form id="contact-us" method="post" action="sendEmail.do">
                    <div class="col-xs-6 wow animated slideInLeft" data-wow-delay=".5s">
                        <input type="text" name="name" id="name" required="required" class="form" placeholder="Ime" />
                        <input type="text" name="mail" id="mail" required="required" class="form" placeholder="Email" />
                        <input type="text" name="subject" id="subject" required="required" class="form" placeholder="Predmet poruke" />
                    </div>
                    <div class="col-xs-6 wow animated slideInRight" data-wow-delay=".5s">                     
                        <textarea name="message" id="message" class="form textarea"  placeholder="Poruka"></textarea>
                    </div>                     
                    <div class="relative fullwidth col-xs-12">                    
                        <button type="submit" id="submit" name="submit" class="form-btn semibold">Pošalji poruku</button> 
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>                     
                    <div class="clear"></div>
                    <div style="min-width: 350px;height: 50px;position: absolute;margin-left: 41%;top: 45%;">
                        <input action="action" class="form-btn semibold" value="Vrati se na imenik"  type="button" onclick="history.go(-1);" />   
                    </div>
                </form>


                <div class="mail-message-area">

                    <div class="alert gray-bg mail-message not-visible-message">
                        <strong>Thank You !</strong> Your email has been delivered.
                    </div>
                </div>

            </div>
        </div>

    </body>
</html>