function validarFormR(){
    var nombre;
    var apellido1;
    var apellido2;
    var correo;
    var password;
    var password2;
    nombre = document.getElementById('nombre').value;
    apellido1 = document.getElementById('apellido1').value;
    apellido2 = document.getElementById('apellido2').value;
    correo = document.getElementById('correo').value;
    password = document.getElementById('password').value;
    password2 = document.getElementById('password2').value;
    expresion1 = /^[a-zA-ZÀ-ÿ\s]{4,50}$/;
    expresion2 = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    expresion3 = /^.{4,12}$/;
    expresion4 = /^.{4,12}$/;

    if(nombre === "" || apellido1 === "" || apellido2 === "" || correo === "" || password === "" || password2 === ""){
        alert("Todos los campos de este formulario son obligatorios. Por favor rellenalos");
        return false;
    }
    else if(!expresion1.test(nombre)) {
        alert("El Nombre tiene que ser de 4 a 50 dígitos, solo puede contener letras y acentos");
        return false;
    }
    else if(!expresion1.test(apellido1)) {
        alert("El Apellido Paterno tiene que ser de 4 a 50 dígitos, solo puede contener letras y acentos");
        return false;
    }
    else if(!expresion1.test(apellido2)) {
        alert("El Apellido Materno tiene que ser de 4 a 50 dígitos, solo puede contener letras y acentos");
        return false;
    }
    else if(!expresion2.test(correo)) {
        alert("El correo solo puede contener letras, numeros, puntos, guiones y guion bajo. Debe de terminar con @algo.algo");
        return false;
    }
    else if(!expresion3.test(password)) {
        alert("La contraseña tiene que ser de 4 a 12 dígitos.");
        return false;
    }
    else if(!expresion4.test(password2)) {
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