function validarFormSP(){
    var nombre;
    var paterno;
    var materno;
    var correo;
    var motivo;
    nombre = document.getElementById('nombre').value;
    paterno = document.getElementById('paterno').value;
    materno = document.getElementById('materno').value;
    correo = document.getElementById('correo').value;
    motivo = document.getElementById('motivo').value;
    expresion1 = /^[a-zA-ZÀ-ÿ\s]{4,50}$/;
    expresion2 = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    expresion3 = /^[a-zA-Z0-9-ZÀ-ÿ\s]{50,250}$/;

    if(nombre === "" || paterno === "" || materno === "" || correo === "" || motivo === ""){
        alert("Todos los campos de este formulario son obligatorios. Por favor rellenalos");
        return false;
    }
    else if(!expresion1.test(nombre)) {
        alert("El Nombre tiene que ser de 4 a 50 dígitos, solo puede contener letras y acentos");
        return false;
    }
    else if(!expresion1.test(paterno)) {
        alert("El Apellido Paterno tiene que ser de 4 a 50 dígitos, solo puede contener letras y acentos");
        return false;
    }
    else if(!expresion1.test(materno)) {
        alert("El Apellido Materno tiene que ser de 4 a 50 dígitos, solo puede contener letras y acentos");
        return false;
    }
    else if(!expresion2.test(correo)) {
        alert("El correo solo puede contener letras, numeros, puntos, guiones y guion bajo. Debe de terminar con @algo.algo");
        return false;
    }
    else if(!expresion3.test(motivo)) {
        alert("El motivo tiene que ser de 50 a 250 dígitos, solo puede contener letras y acentos");
        return false;
    }
}