<%-- 
    Document   : indexDes
    Created on : 5/06/2021, 11:40:34 PM
    Author     : sofo9
--%>

<%@page import="java.util.List"%>
<%@page import="Controlador.*"%>
<%@page import="Modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
     HttpSession sesion = request.getSession();
     String clas = (String) sesion.getAttribute("clasInde");
     
     if(clas == null){
         clas = "Fuerza Muscular";
     }
     
     int id_clas = AccionesClasificacion.getIDClasificacion(clas);
     AccionesClasificacion.buscarClasificacionById(id_clas);
    
     List<Integer> ids_rutis = AccionesRutina.getIdsRutinas(id_clas);
     
     List<Rutina> rutinas = null;
     
     for(int id_ruti:ids_rutis){
         Rutina ruti = AccionesRutina.buscarRutinaById(id_ruti);
         if(AccionesRutina.isInBibliotecaPublica(id_ruti)){
            rutinas.add(ruti);
         }
         
     }        
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>INICIO DESBLOQUEADO</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.1/normalize.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/glider-js@1.7.3/glider.css">
    <link rel="stylesheet" href="../CSS/index(des).css">
</head>
<header>
    <div class="encabezadoIMG">
        <div><img src="../IMG/LogoTororutinas.png" id="ToroPNG"></div>
        <div class="formularioHeader">
            <form class="formSC1">
                <input type="search" class="buscador" placeholder="Buscar" size="30">       <!--esta madre se edita Bv-->
            </form>
        </div>
        <div>
            <form class="formSC2" action="indexDes">
                <label id="labelSeleccionarClasificacion">
                    <select id="se_clasificacion" name="clas">                                          <!--esta madre se edita Bv-->
                        <option value="0">Seleccionar clasificación</option>
                        <option value="1">Fuerza Muscular</option>
                        <option value="2">Aeróbico</option>
                        <option value="3">Equilibrio</option>
                        <option value="4">Estiramiento</option>    
                    </select>
                </label>
                <input type="submit">
            </form>  
        </div>
      </div>
</header>
<body>
    <div class="slide-contenedor">
        <nav id="contenedorPrincipal">
            <!--Aqui esta el carusel principarl pra-->
            
            <% 
                if(rutinas == null){
            %>
                <div class="miSlider fade">
                    <section id="vistaRutina"><h1>No hay ninguna rutina todavía</h1></section>
                </div>  
            <%
                }else{
                    for(int i = 0; i < 4; i++){
            %>
                        <div class="miSlider fade">
                            <section id="vistaRutina"><%= rutinas.get(i).getNom_ruti()  %></section>
                        </div>
            <%
                    }
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
        </div>        <div class="barras">
            <span class="barra active" onclick="posicionSlide(1)"></span>
            <span class="barra" onclick="posicionSlide(2)"></span>
            <span class="barra" onclick="posicionSlide(3)"></span>
        </div>
        </nav>
    </div>
	<div class="second-container">
            
<!--sexoooooooooooooooooooooooooo-->
    <div class="carousel">
        <div class="carousel__contenedor">
            <button aria-label="Anterior" class="carousel__anterior">
                <svg version="1.1" id="Capa_2" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                width="16px" height="16px" viewBox="0 0 16 16" enable-background="new 0 0 16 16" xml:space="preserve">
                <path fill="#FF601F" d="M10,12.796V3.204L4.519,8L10,12.796z M9.341,13.549l-5.48-4.796C3.445,8.39,3.403,7.758,3.766,7.342
                c0.029-0.034,0.061-0.065,0.095-0.095l5.48-4.796c0.415-0.364,1.048-0.322,1.411,0.093C10.912,2.727,11,2.961,11,3.204v9.592
                c0,0.552-0.447,1.001-0.999,1.001C9.758,13.797,9.523,13.709,9.341,13.549z"/>
            </svg>

            </button>

            <div class="carousel__lista">
                <!--Aqui van las rutinas pta-->
            <% 
                if(rutinas == null){
            %>
                    <div class="carousel__elemento" id="primerElemento">
                        <section id="vistaRutinas"><img src="IMG/skiso.jpg"></section>
                    </div>
            <%
                }else{
            %>
                    <div class="carousel__elemento" id="primerElemento">
                        <section id="vistaRutinas"><%=rutinas.get(4).getNom_ruti()%></section>
                    </div>

            <%
                
                    for(int i=5; i <= 13; i++){
                        
            %>
                        <div class="carousel__elemento">
                            <section id="vistaRutinas"><%= rutinas.get(i).getNom_ruti() %></section>
                        </div>
        
            <%
                    }
                }
            %>
            </div>
            <button arial-label="Siguiente" class="carousel__siguiente">
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
    <script src="../JS/index(des).js"></script>
</body>
</html>
