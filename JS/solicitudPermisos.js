function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron =  /[A-Z a-z 0-9@.]+[^\s]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}

function validarna(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron =  /^(?=.{1,10}$)[a-zA-ZáéíóúüñÁÉÍÓÚÑ]+(?:[\s][a-zA-ZáéíóúüñÁÉÍÓÚÑ]+)*$/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}

function validard(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron =  /[A-Z a-z 0-9. áéíóúüñÁÉÍÓÚÑ ! ¡ ¿ ? ( ) ]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}