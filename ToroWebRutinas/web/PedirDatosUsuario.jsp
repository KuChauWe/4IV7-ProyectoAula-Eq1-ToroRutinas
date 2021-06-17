

<%@page import="Controlador.MPerfil"%>
<%@page import="java.awt.Image"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="java.io.InputStream"%>
<%@page import="Modelo.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!--Validación del perfil e intancia-->
<%
    HttpSession sesion = request.getSession();
    boolean sesionIniciada;
    Perfil perf = null;
    int id_perf = 0;
    try{
         id_perf = (Integer) sesion.getAttribute("perfil");
            sesionIniciada = true;
         if(id_perf != 0){
             sesionIniciada = true;
             perf = MPerfil.getPerfilById(id_perf);
             System.out.println("Ya hay una sesion abierta");
         }
    }catch(Exception e){
        System.out.println("No se ha iniciado Sesion");
        sesionIniciada = false;
    }
    
    System.out.println(perf.getId_img());
%>               
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Datos Usuario</title>
    <link rel="shortcut incon" href="./img/ToroRutinasLogo41.png" >
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="CSS/PedirDatosUsuario.css">
    <script src="JS/PedirDatosUsuarioaaaaaa.js"></script>
</head>
<body>
    <main>
        <nav id="encabezado">
            <div id="circulo">
                <section id="sec_foto">
                    <label id="label41"><img class="perfilimg" src="http://drive.google.com/uc?export=view&id=<%=perf.getId_img()%>"></label>
                </section>
            </div>
            <form  action="RegistroUsuario" class="formulario" id="formulario" onsubmit="return validarFormPDU()">
                <!-- Grupo: Peso -->
                <div class="formulario__grupo" id="grupo__nombre">
                    <label for="nombre" class="formulario__label">Ingresa tu peso en kg</label>
                    <div class="formulario__grupo-input">
                        <input type="text" class="formulario__input" name="peso" id="peso" placeholder="00.00">
                    </div>
                </div>
                <!-- Grupo: altura-->
                <div class="formulario__grupo" id="grupo__apellido1">
                        <label for="apellido1" class="formulario__label">Ingresa tu altura en metros</label>
                        <div class="formulario__grupo-input">
                                <input type="text" class="formulario__input" name="altura" id="altura" placeholder="00.00">
                        </div>
                </div>
                

                <div class="formulario__grupo formulario__grupo-btn-enviar">
                    <button type="submit" class="formulario__btn">Enviar</button>
                </div>
            </form>
        </nav>
	</main>

</body>
</html>