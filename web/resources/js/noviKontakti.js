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

function brojevi(inputElement) {
    var inputText = inputElement.value;

    var regularExpression = /^[0-9]+$/;

    if (regularExpression.test(inputText)) {
        inputElement.style = "border-color: none; color: black;";

        return true;
    } else {
        inputElement.style = "border-color: red; color: red;";

        return false;
    }
}

function validateEmail(inputElement) {
    var inputText = inputElement.value;

    var regularExpression = /^[a-z]+@[a-z]+\.[a-z]{1,3}$/;

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
    var imeElement = document.forms["kontaktForma"]["ime"];
    if (!validateLength(imeElement, 3)) {
        document.getElementById("imeGreska").innerHTML = "Ime mora biti veće od 3 karaktera.";

        return false;

    } else {
        document.getElementById("imeGreska").innerHTML = "";


    }

    var prezimeElement = document.forms["kontaktForma"]["prezime"];
    if (!validateLength(prezimeElement, 5)) {
        document.getElementById("prezimeGreska").innerHTML = "Prezime mora biti veće od 5 karaktera.";

        return false;

    } else {
        document.getElementById("prezimeGreska").innerHTML = "";


    }

    var emailElement = document.forms["kontaktForma"]["email"];
    if (!validateEmail(emailElement)) {
        document.getElementById("emailGreska").innerHTML = "Nekorektan unos elektronske pošte";

        return false;

    } else {
        document.getElementById("emailGreska").innerHTML = "";
    }


    var pozivniBrojElement = document.forms["kontaktForma"]["pozivniBroj"];
    if (!brojevi(pozivniBrojElement) ) {
            
        document.getElementById("pozivniBrojGreska").innerHTML = "Pozivni broj ima 3 broja.";

        return false;

    } else {
        document.getElementById("pozivniBrojGreska").innerHTML = "";
    }
    
    var brojTelefonaElement = document.forms["kontaktForma"]["brojTelefona"];
    if (!brojevi(brojTelefonaElement)) {
        document.getElementById("brojTelefonaGreska").innerHTML = "Broj telefona se sastoji samo od brojeva.";

        return false;

    } else {
        document.getElementById("brojTelefonaGreska").innerHTML = "";
    }

    var adresaElement = document.forms["kontaktForma"]["adresa"];
    if (!validateLength(adresaElement, 5)) {
        document.getElementById("adresaGreska").innerHTML = "Adresa mora biti veće od 5 karaktera.";

        return false;

    } else {
        document.getElementById("adresaGreska").innerHTML = "";


    }

    var gradElement = document.forms["kontaktForma"]["grad"];
    if (!validateLength(gradElement, 3)) {
        document.getElementById("gradGreska").innerHTML = "Grad mora biti veći od 3 karaktera.";

        return false;

    } else {
        document.getElementById("gradGreska").innerHTML = "";


    }




    return true;
}

      