<%-- 
    Document   : creadorRutina
    Created on : 17/06/2021, 05:12:33 PM
    Author     : sofo9
--%>

<%@page import="Modelo.Ejercicio"%>
<%@page import="Controlador.MEjercicio"%>
<%@page import="Controlador.MRutina"%>
<%@page import="Modelo.Rutina"%>
<%@page import="java.util.List"%>
<%@page import="Controlador.MUsuario"%>
<%@page import="Modelo.Usuario"%>
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
        response.sendRedirect("../index.jsp");
    }
    sesionIniciada = true;
    Usuario perf = MUsuario.getUsuById(id_perf);
    

    System.out.println("Ya hay una sesion abierta");   
        
    
%>
<!--Obtengo las rutinas de la biblioteca del Usuario-->
<% 
    List<Rutina> rutisB = null;
    try{
        rutisB = MRutina.getRutisUsu(perf.getId_perf());
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Creador de rutinas</title>
    <link rel="stylesheet" href="../CSS/creadorRutinas.css">
    <link rel="shortcut icon" href="./IMG/LogoTororutinas.png">
</head>
<body> 
    
    <div class="main-container">
        <div class="div-buscador">
            <input type="search" class="buscador" placeholder="Buscar">
            <div class="div-casita">
                <!--I M A G E N   P R O V I S I O N A L-->
                <a href="javascript: history.go(-1)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-arrow-left-circle" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.5-.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
                    </svg>
                </a>
            </div>
        </div>
    </div>

    <div class="div-titulo">
        <button type="button" class="boton" id="open">Crear rutina</label>
    </div>
    
    <% 
        if(rutisB == null){
    %>
    <div class="container">
        
        <div class="columna-izq" >
            <div class="div-imagen">
                <img class="imagen" src="http://drive.google.com/uc?export=view&id=1kNWSpnxMzRJrqe_bkRTBh5y5bgnIkQK1">
            </div>
        </div>

       
    </div>
    <br><br>
    <% 
        }else{
            for(int i = 1; i <= rutisB.size() ; i++){
                Rutina ruti = rutisB.get(i);

    //                        Si no existe la rutina entonces término el for
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
                <div class="container">
                    <div class="columna-izq">
                        <div class="div-imagen">
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
                        </div>
                    </div>

                    <div class="columna-der">
                        <div class="div-nombre">
                            <label class="nombre">Nombre de rutina</label>  
                        </div>
                        <div class="div-boton">
                            <button type="button" class="boton" id="open3">Agregar ejercicio a la rutina</button>
                            <br><br><br>
                            <button type="button" class="boton" id="open2">Borrar rutina</button>
                        </div>
                    </div>
                </div>
            <br><br>
    <% 
        }
    }
    
    
    %>
    <!--Ventana emergente para el botón "Crear rutina"-->
    <div class="modal-container" id="modal_container">
        <div class="modal">
            <div>
                <label class="titulo" style="color:rgb(247,96,31);">Escribe el nombre de la rutina</label>
                <br><br>
                <div>
                    <input type="text" class="input" style="margin-bottom: 30px;">
                </div>
            </div>
            <div>
                <button type="button" class="boton" id="close">Cancelar</button>
                <button type="button" class="boton" style="margin-left: 30px;">Crear</button>
            </div>
        </div>
    </div>
    
    <!--Ventana emergente para el botón "Borrar rutina"-->
    <div class="modal-container" id="modal_container2">
        <div class="modal">
            <div>
                <label class="titulo" style="color:rgb(247,96,31);">¿Estás seguro de que quieres eliminar la rutina <br>"Nombre rutina"?</label>
                <br><br>
            </div>
            <div>
                <button type="button" class="boton" id="close2">No</button>
                <button type="button" class="boton" style="margin-left: 30px;">Sí</button>
            </div>
        </div>
    </div>

    <!--Ventana emergente para el botón "Agregar ejercico a la rutina"-->
    <div class="modal-container" id="modal_container3">
        <div class="modal">
            <div>
                <label class="titulo" style="color:rgb(247,96,31);">Buscador de ejercicios</label>
                <br><br>
                <div>
                    <input type="search" class="input" style="margin-bottom: 30px;">
                </div>
            </div>
            <div>
                <button type="button" class="boton" id="close3">Cancelar</button>
                <button type="button" class="boton" style="margin-left: 30px;">Agregar</button>
            </div>
        </div>
    </div>

<br><br><br>
<script src="../JS/VentanaCrutinas.js"></script>
</body>
</html>
