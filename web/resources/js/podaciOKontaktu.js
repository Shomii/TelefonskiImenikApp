//onclick show selected user for applayng changes
function getData() {
    var choosen = $('#kontakt');
    var array = [$('#ifirstName'), $('#ilastName'), $('#iemail'), $('#iphoneNo'),
        $('#ijmbg'), $('#iadresa'), $('#imesto'), $('#ioperater'), $('#irola')
    ];
    for (i = 0; i < array.length; i++) {
        array[i].val("");
    }
    $.ajax({
        type: 'GET',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: {name: choosen.val()},
        url: getContextPath() + '/podaciOKontaktu',
        success: function (data) {
            $('#ifirstName').val(data.ime);
            $('#ilastName').val(data.prezime);
            $('#iemail').val(data.email);
            $('#iphoneNo').val(data.brTelefona);
            $('#ijmbg').val(data.jmbg);
            $('#iadresa').val(data.adresa);
            $('#imesto').val(data.mesto);
            $('#ioperater').val(data.operater);
//            $('#irola').val(data.rola);
        }
    });
}
;