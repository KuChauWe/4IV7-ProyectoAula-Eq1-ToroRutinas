<%@page import="Modelo.*"%>
<%@page import="Controlador.AccionesPerfil"%>
<%@page import="Controlador.ConexionSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java" import="java.sql.*, java.sql.Date, java.util.*, java.text.*" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REGISTRARSE</title>
    <link rel="stylesheet" href="./CSS/Registrarse.css">
    <script src="./JS/Registrarse.js"></script>
</head>
<body>
    <div class="main-container">
        <nav id="encabezado">
            <img src="./IMG/perfil.jpg" id="perfil">
    
                Registrate
        </nav>
<%  //Variables de conexion
    Connection con = null;
    Statement set = null;
    ResultSet rs = null;
    
    //Variables del perfil
    String nom_perf = request.getParameter("nom_perf");
    String email_per = request.getParameter("email_per");
    Date fechNaci_perf = null;
    fechNaci_perf.setTime(request.getDateHeader("fechNaci_perf"));
    String contra_perf = request.getParameter("contra_perf");
    try{
        Perfil perf = new Perfil();
        perf.setNom_perf(nom_perf);
        
        int estatus = AccionesPerfil.registrarPerfil(perf);

        if(estatus == 0){
%>
                <h1> 
                    Hubo un error al registrar el perfil 
                </h1>


<%
              
                
        }else{
%>
                <h1>Registro exitoso</h1>
                <br>
                <br>
                <p>
                    Nombre: <%=perf.getNom_perf()%>
                <br><br>
                    Correo electrÃ³nico: <%=perf.getEmail_per()%>
                <br><br>
                    Fecha de nacimiento: <%=perf.getFechNaci_perf().toString()%>
                </p>

    <%
            }
            System.out.println("Se ha registrado correctamente el perfil");    
        
    
    }catch(Exception e){
        System.out.println("Error al conectar con la BD");
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
%>
        <h1>Hubo un error al conectarse con la BD</h1>
<%
    }    
%>        
    </div>
    
</body>
</html>