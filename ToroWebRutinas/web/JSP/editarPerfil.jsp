<%@page import="java.time.Period"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.Year"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.*"%>
<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--Validación del perfil e intancia-->
<%
    HttpSession sesion = request.getSession();
    boolean sesionIniciada;
    
    int id_perf = 0;
    id_perf = (Integer) sesion.getAttribute("perfil");
    
    if(id_perf == 0){
        System.out.println("No se ha iniciado Sesion");
        sesionIniciada = false;
        response.sendRedirect("../index.html");
    }
    sesionIniciada = true;
    Usuario perf = MUsuario.getUsuById(id_perf);
    

    System.out.println("Ya hay una sesion abierta");   
        
    
%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <link rel="stylesheet" href="../CSS/editarPerfil.css">
    <link rel="shortcut icon" href="./IMG/LogoTororutinas.png">
</head>
<body> 
    
    <div class="main-container">
        <div class="div-casita">
            <!--I M A G E N   P R O V I S I O N A L-->
            <a href="javascript: history.go(-1)"> <!--mamamarrrrrannnooooooo el link a páginas.-->
                <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-arrow-left-circle" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.5-.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                </svg>
            </a>
        </div>
        
    <div id="circulo">
        <img src="http://drive.google.com/uc?export=view&id=<%=perf.getId_img()%>" id="fotoPerfil">
        <div id="circulo-chico"></div>
    </div>

    <div class="div-archivo">
        <input type="file" class="seleccionar-archivo">
    </div>

    <div class="sub-container">
        <form class="formulario">
            <label>Cambiar nombre de usuario: </label>
            <br><br>
            <input type="text" class="input" size="30"  value="<%=perf.getNomb_perf() %>">
            <br><br><br>
            <button type="button" class="boton">Enviar</button> 
        </form>
    </div>
    </div>
</body>
</html>
