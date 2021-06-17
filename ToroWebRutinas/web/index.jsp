<%-- 
    Document   : index
    Created on : 16/06/2021, 10:10:57 AM
    Author     : sofo9
--%>

<%@page import="Controlador.MEjercicio"%>
<%@page import="java.util.Set"%>
<%@page import="Modelo.Ejercicio"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.stream.IntStream"%>
<%@page import="java.sql.Array"%>
<%@page import="Modelo.Rutina"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.MRutina"%>
<%@page import="Controlador.MPerfil"%>
<%@page import="Modelo.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
try{
%>

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
%>


<!--Obtengo rutinas publicas (14)al azar -->
<%
    List<Integer> num = null;
    List<Rutina> rutisPublics = MRutina.getRutisPublic();
    
    Collections.shuffle(rutisPublics);
    Random r = new Random(); 
    //La lista a mostrar
    List<Rutina> rutiMostrar = null;
    
    for(int i = 0; i < 15 + 1; i++){
        if(r.nextBoolean()){
            rutiMostrar.add(rutisPublics.get(i));
            num.add(i);
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.1/normalize.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/glider-js@1.7.3/glider.css">
    <link rel="stylesheet" href="CSS/indexpro.css">
</head>
<header>
    <div class="main-header">
        <nav class="bamrram primerElemento">
            <section>
                <img src="http://drive.google.com/uc?export=view&id=1335M8yqOnwwhYskz-OfTPPm-B6v4GNx5" id="ImagenToro">
            </section>
        </nav>
        <nav class="bamrram segundoElemento">
            <section>
                <button class="iniciarSesion btnHeader"><a class="textBtn" href="IniciarSesion2.html">Iniciar Sesión</a></button>
            </section>
            <section>
                <button class="registrarse btnHeader"><a class="textBtn" href="Registrarse2.html">Registrarse</a></button>
            </section>
        </nav>
    </div>
</header>
<body>


<!--El slider de arriba aaaaaaaaaaaxdddddddddddddddd:v-->
<div class="primerSlider">
    <div class="slide-contenedor">
        <nav id="contenedorPrincipal">
        <!--Muestro las primeras 4-->
        <%
            for(int i=0; i < 5; i++){
                Rutina ruti = rutiMostrar.get(i);
                
                
//              Si no existe la rutina entonces término el for
                if(ruti == null)break;
                
                ruti.setEjercicios(MEjercicio.getEjers(ruti.getId_ruti()));
                
                List<Ejercicio> ejers = (List<Ejercicio>) ruti.getEjercicios().keySet();
                //Obtengo el id de las imagenes
                List<String> ids_img = null;
                //Agrego la primera imagen del  ejercicio, todos lo ejercicios tendrán como minímo dos imágenes
                for(int j=0; j < 5; j++){
                              //el ejercicio.los ids . el primer id
                    ids_img.add(ejers.get(j).getIds_img().get(0));   
                }
        %>
                 <div class="miSlider fade">
                    <section class="vistaRutina">
<!--@@@@@@@@@@@@@@@@@@@@@@@Aqui va el link que no han echo hspm@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->
                        <table>
                            <tr>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img.get(0) %>" alt="">
                                </td>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img.get(1) %>" alt="">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img.get(2) %>" alt="">
                                </td>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img.get(3) %>" alt="">
                                </td>
                            </tr>

                        </table>
                    </section>
                </div>
                
        <%       
            }
        %>
       
        <div class="direcciones">
            <a href="#" class="atras" onclick="avanzaSlide(-1)">
                <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
                   <path fill="#FF601F" d="M10,12.796V3.204L4.519,8L10,12.796z M9.341,13.549l-5.48-4.796C3.445,8.39,3.403,7.758,3.766,7.342
                   c0.029-0.034,0.061-0.065,0.095-0.095l5.48-4.796c0.415-0.364,1.048-0.322,1.411,0.093C10.912,2.727,11,2.961,11,3.204v9.592
                   c0,0.552-0.447,1.001-0.999,1.001C9.758,13.797,9.523,13.709,9.341,13.549z"/>
                </svg>
            </a>
            <a href="#" class="adelante" onclick="avanzaSlide(1)">
                <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
                <path fill="#FF601F" d="M4.519,3.204v9.592L10,8L4.519,3.204z M5.178,2.451l5.48,4.796c0.416,0.363,0.458,0.995,0.095,1.411
                c-0.029,0.033-0.061,0.065-0.095,0.095l-5.479,4.796c-0.415,0.364-1.048,0.322-1.411-0.093c-0.16-0.183-0.249-0.417-0.249-0.66
                V3.204c0-0.552,0.447-1.001,0.999-1.001C4.761,2.203,4.996,2.291,5.178,2.451z"/>
            </svg>
            </a>
        </div>        
        <div class="barras">
            <span class="barra active" onclick="posicionSlide(1)"></span>
            <span class="barra" onclick="posicionSlide(2)"></span>
            <span class="barra" onclick="posicionSlide(3)"></span>
        </div>
        </nav>
    </div>
</div>


<!--sexoooooooooooooooooooooooooo-->


<div class="second-container"> 
    <div class="carousel">
        <div class="carousel__contenedor">
            <button aria-label="Anterior" class="carousel__anterior btnSup">
                <svg version="1.1" id="Capa_2" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
                <path fill="#FF601F" d="M10,12.796V3.204L4.519,8L10,12.796z M9.341,13.549l-5.48-4.796C3.445,8.39,3.403,7.758,3.766,7.342
                c0.029-0.034,0.061-0.065,0.095-0.095l5.48-4.796c0.415-0.364,1.048-0.322,1.411,0.093C10.912,2.727,11,2.961,11,3.204v9.592
                c0,0.552-0.447,1.001-0.999,1.001C9.758,13.797,9.523,13.709,9.341,13.549z"/>
            </svg>

            </button>

            <div class="carousel__lista">
                <div class="carousel__elemento" id="primerElemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/skiso.jpg"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/holamundo.jpeg"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/Deciivo.png"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/Flecha.png"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/FlechaDerecha.png"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/FlechaIzquierd.png"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/LogoEmpresa.jpeg"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/perfil.jpg"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/toro.png"></section>
                </div>
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas"><img src="IMG/ToroRutinasLogo22.png"></section>
                </div>
            </div>
            <button arial-label="Siguiente" class="carousel__siguiente btnSup">
                <svg version="1.1" id="Capa_2" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
	                width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
                    <path fill="#FF601F" d="M4.519,3.204v9.592L10,8L4.519,3.204z M5.178,2.451l5.48,4.796c0.416,0.363,0.458,0.995,0.095,1.411
	                c-0.029,0.033-0.061,0.065-0.095,0.095l-5.479,4.796c-0.415,0.364-1.048,0.322-1.411-0.093c-0.16-0.183-0.249-0.417-0.249-0.66
	                V3.204c0-0.552,0.447-1.001,0.999-1.001C4.761,2.203,4.996,2.291,5.178,2.451z"/>
                </svg>
            </button>
        </div>
    </div>
        <div role="tablist" class="class__indicadores"></div>
    </div>



	<script src="https://cdn.jsdelivr.net/npm/glider-js@1.7.3/glider.min.js"></script>
    <script src="JS/indexpro.js"></script>
</body>
<% 
}catch(Exception e){
    System.out.println(e.getMessage());
    response.sendRedirect("indexvacio.html");


}


%>
</html>
