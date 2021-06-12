function validarForm(){
    var correo;
    var password;
    var password2;
    correo = document.getElementById('correo').value;
    password = document.getElementById('password').value;
    password2 = document.getElementById('password2').value;
    expresion1 = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    expresion2 = /^.{4,12}$/;
    expresion3 = /^.{4,12}$/;

    if(correo === "" || password === "" || password2 === ""){
        alert("Todos los campos de este formulario son obligatorios. Por favor rellenalos");
        return false;
    }
    else if(!expresion1.test(correo)) {
        alert("El correo solo puede contener letras, numeros, puntos, guiones y guion bajo.");
        return false;
    }
    else if(!expresion2.test(password)) {
        alert("La contraseña tiene que ser de 4 a 12 dígitos.");
        return false;
    }
    else if(!expresion3.test(password2)) {
        alert("La contraseña de confirmación tiene que ser de 4 a 12 dígitos.");
        return false;
    }
    if(password !== password2) {
        alert("Ambas contraseñas deben ser iguales.");
        return false;
    } else {
        return true;
    }
}