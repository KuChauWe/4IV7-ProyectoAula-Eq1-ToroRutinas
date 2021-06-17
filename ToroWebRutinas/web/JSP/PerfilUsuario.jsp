<%-- 
    Document   : PerfilUsuario
    Created on : 16/06/2021, 07:36:52 PM
    Author     : sofo9
--%>

<%@page import="Controlador.MUsuario"%>
<%@page import="Modelo.Usuario"%>
<% 
try{
%>
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil Usuario</title>
    <link rel="stylesheet" href="../CSS/PerfilUsuario.css">
</head>
<header>
    <div class="main-header">
       
        <nav class="bamrram segundoElemento">
            <section>
                <section class="bamrrams">
                    <a href="indexDes.jsp">
                    <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-arrow-left-circle" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.5-.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                    </svg>
                    </a>
                </section>
            </section>
        </nav>
    </div>
</header>
<body>
    <div class="main-container">
        <nav id="contenedorPrincipal">
            <img src="http://drive.google.com/uc?export=view&id=<%=perf.getId_img()%>" id="fotoPerfil">
            <br>
            <div class="divNM" id="divNM">
                <div>
                    <label id="nombre_usuario"><%=perf.getNomb_perf() %></label>
                </div>
            </div>
            <div class="divBotones" id="divEP">
                <button id="EP" class="botones" onclick="location.href='editarPerfil.jsp'">Editar Perfil</button>
            </div>
            <div class="divBotones" id="divHDR">
                <button id="HDR" class="botones" onclick="location.href='historialRutinas.jsp'">Historial de Rutinas</button>
            </div>
            <div class="divBotones" id="divCI">
                <button id="CI" class="botones" onclick="location.href='consultarInfo.jsp'">Consultar Información</button>
            </div>
            <div class="divBotones" id="divCS">
                <button id="CS" class="botones" onclick="location.href='../inicioCreador'">Modo Creador</button>
            </div>
            <div class="divBotones" id="divCS">
                <button id="CS" class="botones" onclick="location.href='../inicioAdmin'">Modo Administrador</button>
            </div>
            <div class="divBotones" id="divCS">
                <button id="CS" class="botones" onclick="location.href='../CerrarSesion'">Cerrar Sesión</button>
            </div>

        </nav>
    </div>
</body>

<% 
}catch(Exception e){
    System.out.println(e.getMessage());
    response.sendRedirect("../Error/error.html");
}
%>
</html>



