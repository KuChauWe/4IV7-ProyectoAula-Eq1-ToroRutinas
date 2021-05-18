function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron =  /[A-Z a-z 0-9@.]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}

function validars(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron =  /[A-Z a-z 0-9]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}
