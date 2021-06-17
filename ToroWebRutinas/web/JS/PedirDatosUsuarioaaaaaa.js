function validarFormPDU(){
    var peso;
    var altura;
    peso = document.getElementById('peso').value;
    altura = document.getElementById('altura').value;
    expresion1 = /^[\d]{2,3}[.]{1}[\d]{1,2}$/;

    if(peso === "" || altura === ""){
        alert("Todos los campos de este formulario son obligatorios. Por favor rellenalos");
        return false;
    }
    else if(!expresion1.test(peso)) {
        alert("El peso solo puede contener desde 2 caracteres hasta 6 caracteres, solo puede contener números y debe llevar un punto. La sintax correcta es: 000.00 (no es necesario poner 3 numeros antes del punto, con 2 basta y despues del punto no es necesario poner 2 numeros, con uno basta");
        return false;
    }
    else if(!expresion1.test(altura)) {
        alert("La altura solo puede contener desde 2 caracteres hasta 6 caracteres, solo puede contener números y debe llevar un punto. La sintax correcta es: 000.00 (no es necesario poner 3 numeros antes del punto, con 2 basta y despues del punto no es necesario poner 2 numeros, con uno basta");
        return false;
    }
}