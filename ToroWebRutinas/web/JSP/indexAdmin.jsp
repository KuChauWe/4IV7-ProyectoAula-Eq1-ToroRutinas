<%-- 
    Document   : indexAdmin
    Created on : 17/06/2021, 05:00:09 PM
    Author     : sofo9
--%>

<%@page import="Controlador.MUsuario"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Validación del perfil e intancia-->
<%
    HttpSession sesion = request.getSession();
    boolean sesionIniciada;
    
    int id_perf = 0;
    id_perf = (Integer) sesion.getAttribute("perfil");
    
    if(id_perf == 0 ){
        System.out.println("No se ha iniciado Sesion");
        sesionIniciada = false;
        response.sendRedirect("../index.html");
    }
    sesionIniciada = true;
    Usuario perf = MUsuario.getUsuById(id_perf);
    
    if(!perf.isAdmin()){
        System.out.println("No tienes una cuenta de Admin");
        response.sendRedirect("PerfilUsuario.jsp");
    }
    System.out.println("Ya hay una sesion abierta");   
        
    
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index|Administrador</title>
    <link link rel="stylesheet" href="../CSS/indexAdmin.css">
</head>
<header>
    <div>
        <section>
            <a href="../cerrarAdmin">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-arrow-left-circle" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.5-.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                    </svg>
            </a>
        </section>
    </div>
</header>
<body>
    <div class="main-container">
        <nav id="navTabla">
            <table>
                <tbody>
                    <tr>
                        <th></th>
                        <th colspan="2" id="thCMR"><input class="botones" value="Buscador de rutinas en biblioteca" size="40"></th>
                        <th></th>
                    </tr>
                    <tr>
                        <th id="thBEB"><input class="botones" value="Administrador solicitudes de cuentas" size="40"></th>
                        <th colspan="2" rowspan="3" id="thAIMG"><article id="articleLogoTR"><img src="http://drive.google.com/uc?export=view&id=1335M8yqOnwwhYskz-OfTPPm-B6v4GNxsss5" id="logo_ToroRutinas"></article></th>
                        <th><input class="botones" value="Administrador peticiones ejercicios" size="40"></th>
                    </tr>
                    <tr>
                        <th class="thB13"></th>
                        <th class="thB13"></th>
                    </tr>
                    <tr>
                        <th><input class="botones" value="Buscador de ejercicios en biblioteca" size="40"></th>
                        <th><input class="botones" value="Administrador peticiones rutinas" size="40"></th>
                    </tr>
                </tbody>
            </table>
            </nav>
    </div>
</body>
</html>
