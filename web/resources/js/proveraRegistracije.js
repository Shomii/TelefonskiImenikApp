function validateLength(inputElement, min) {
    var inputText = inputElement.value;



    if (inputText.length < min) {
        inputElement.value = "";
        inputElement.style = "border-color: red;";

        return false;
    } else {
        inputElement.style = "border-color: none;";

        return true;
    }
}

function validateEmail(inputElement) {
    var inputText = inputElement.value;

//    var regularExpression = /^[a-z]+@[a-z]+\.[a-z]{1,3}$/;
    var regularExpression = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (regularExpression.test(inputText)) {
        inputElement.style = "border-color: none; color: black;";

        return true;
    } else {
        inputElement.style = "border-color: red; color: red;";

        return false;
    }
}
/*$('#contact input[type=text]').on('change invalid', function () {
 var textfield = $(this).get(0);
 
 // 'setCustomValidity not only sets the message, but also marks
 // the field as invalid. In order to see whether the field really is
 // invalid, we have to remove the message first
 textfield.setCustomValidity('');
 
 if (!textfield.validity.valid) {
 textfield.setCustomValidity('Lütfen işaretli yerleri doldurunuz');
 }
 });*/


function validateForm() {
    var imeElement = document.forms["registracionaForma"]["ime"];
    if (!validateLength(imeElement, 3)) {
        document.getElementById("imeGreska").innerHTML = "Ime mora biti veće od 3 karaktera.";

        return false;

    } else {
        document.getElementById("imeGreska").innerHTML = "";


    }

    var prezimeElement = document.forms["registracionaForma"]["prezime"];
    if (!validateLength(prezimeElement, 5)) {
        document.getElementById("prezimeGreska").innerHTML = "Prezime mora biti veće od 5 karaktera.";

        return false;

    } else {
        document.getElementById("prezimeGreska").innerHTML = "";


    }

    var emailElement = document.forms["registracionaForma"]["email"];
    if (!validateEmail(emailElement)) {

        return false;
    }

    var usernameElement = document.forms["registracionaForma"]["username"];
    if (!validateLength(usernameElement, 3)) {
        document.getElementById("usernameGreska").innerHTML = "Korisničko ime mora biti veće od 3 karaktera.";

        return false;

    } else {
        document.getElementById("usernameGreska").innerHTML = "";
    }

    var passwordElement = document.forms["registracionaForma"]["password"];
    if (!validateLength(passwordElement, 3)) {
        document.getElementById("passwordGreska").innerHTML = "Lozinka mora biti veće od 3 karaktera.";

        return false;

    } else {
        document.getElementById("passwordGreska").innerHTML = "";
    }

    return true;
}
