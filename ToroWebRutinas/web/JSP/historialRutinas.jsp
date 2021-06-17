<%-- 
    Document   : historialRutinas
    Created on : 17/06/2021, 01:31:52 PM
    Author     : sofo9
--%>
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

<!--Obtengo la lista del historial a mostrar-->
<% 
    Historial hists = null;
    try{
        hists = DHistorial.getHist(perf.getId_perf());
    }catch(Exception e){
        System.out.println("no hay ningun historial todavía");
    }
    


%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial</title>
    <link rel="stylesheet" href="../CSS/historialRutinas.css">
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
    </div>
    <div class="container">
        <div class="div-titulo">
            <label class="titulo">Tu historial</label>
        </div>
    </div>
    
    <% 
        if(hists == null){
    %>
        <div class="container">
            <div class="div-titulo">
                <label class="titulo"> <img class="imagen" src="http://drive.google.com/uc?export=view&id=1VspDWokkK_IUK5VOVwdkyTt1i2Df6jHA"></label>
            </div>
        </div>
        
        
    <%  
        }else{
            List<Integer> ids_rutis = null;
            try{
                 ids_rutis =   (List) hists.getRutiDateIsCom().keySet();
            

            for(int id_ruti:ids_rutis){
                Rutina ruti = null;
                try{
                    ruti = MRutina.getRutiById(id_ruti);
                    ruti.setEjercicios(MEjercicio.getEjers(ruti.getId_ruti()));
                }catch(Exception e){}

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
                        <label class="nombre"><%= ruti.getNomb_ruti() %></label>  
                    </div>
                    <div class="div-info">
                        <label class="info">
                            Iniciado el: <%= hists.getRutiDateIsCom().get(id_ruti).get(0) %>
                            <%
                                if( (boolean) hists.getRutiDateIsCom().get(id_ruti).get(1) == true){
                                    try{
                                        out.print("Pudiste acabar está rutina");
                                    }catch(Exception e){ }
                                    
                                }else{
                                    try{
                                        out.print("No puediste acabar está rutina");
                                    }catch(Exception e){}
                                    
                                }
                            %>
                        </label>  
                    </div>
                </div>
            </div>
    
    
    <%          
            }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    %>
<br><br><br>
</body>
</html>
