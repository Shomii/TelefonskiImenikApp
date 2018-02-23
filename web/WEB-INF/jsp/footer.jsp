
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>

            @import url(http://weloveiconfonts.com/api/?family=zocial);


            [class*="zocial-"]:before {
                font-family: 'zocial', sans-serif;
            }

            .wrapper{
                width: 325px;
                height: 50px;
                position: absolute;
                top: 95%;
                left: 50%;
                margin-left: -162px;
                margin-top: -25px;
            }

            .icon{

                display: inline-block;
                position: relative;
                color: #bdbdbd;
                width: 50px;
                height: 50px;
                text-align: center;
                font-size: 1.47em;
                line-height: 2em;
                background-color: #fff;
                -moz-border-radius: 50%;
                -webkit-border-radius: 50%;
                border-radius: 50%;
                box-shadow: 0px 3px 0px #bdbdbd, 0px 3px 10px #bababa;
                -webkit-transition:background-color 250ms ease 0s;
                transition:background-color 250ms ease 0s;
            }

            .zocial-facebook{margin-left: -8px}

            .icon.facebook:hover{background-color: #4986c7;}
            .twitter:hover{background-color: #4cb6e8;}
            .linkedin:hover{background-color: #29a0cc;}
            .youtube:hover{background-color: #a32929;}
            .flickr:hover{background-color: #c257ad;}
            .email:hover{background-color: #d5b120;}

            a{
                text-decoration: none;
            }

            .icon:hover{
                color: #fff;
                box-shadow: 0px 3px 0px #686868, 0px 3px 10px #7e7e7e;
            }

            .icon:active{
                box-shadow: inset 0px 1px 4px #3d3d3d, 0px 0px 0px #bdbdbd;
                top: 3px;
            }


        </style>
    </head>
    <body>
        <div class="wrapper">
            <a class="icon facebook" href="#"><span class="zocial-facebook"></span></a>
            <a class="icon twitter" href="#"><span class="zocial-twitter"></span></a>
            <a class="icon linkedin" href="#"><span class="zocial-linkedin"></span></a>
            <a class="icon youtube" href="#"><span class="zocial-youtube"></span></a>
            <a class="icon flickr" href="#"><span class="zocial-flickr"></span></a>
            <a class="icon email" href="#"><span class="zocial-email"></span></a>
        </div>

    </body>
</html>
