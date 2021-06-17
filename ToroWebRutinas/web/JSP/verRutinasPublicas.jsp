<%-- 
    Document   : verRutinasPublicas
    Created on : 6/06/2021, 11:06:55 PM
    Author     : sofo9
--%>

<%@page import="Controlador.MEjercicio"%>
<%@page import="Controlador.MClasificacion"%>
<%@page import="Controlador.MRutina"%>
<%@page import="java.sql.Array"%>
<%@page import="Controlador.MUsuario"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
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
        response.sendRedirect("../index.jsp");
    }
    sesionIniciada = true;
    Usuario perf = MUsuario.getUsuById(id_perf);
    

    System.out.println("Ya hay una sesion abierta");   
        
    
%>

<!--Obtengo el id de las clasificaciones  ids_clas-->
<%
    String clases = null;
    String[] listclas =  null;
    
    int[] ids_clas = null; 
    
    try{
        clases = request.getParameter("clas");
        listclas =  clases.split("_");
    }catch(Exception e){}
    
    
    if(listclas != null){
        ids_clas = new int[listclas.length];
        for(int i = 0; i < listclas.length + 1;i++ ){
            int id_clas;
//          Si String_clas no es un número entonces ids_clas será nulo
            try{
                id_clas = Integer.parseInt(listclas[i]);
                ids_clas[i] = id_clas;
                
                
                
            }catch(Exception e){
                break;
            }

        }
    }else{
        ids_clas = new int[3];
        ids_clas[0] = 1; 
        ids_clas[1] = 5;
        ids_clas[2] = 6;
        
    }
    
   String[] nombs_clas = new String[ids_clas.length];
   for(int i = 0; i < ids_clas.length ;i++ ){
       String nomb_clas =  MClasificacion.getClasById(ids_clas[i]);
       System.out.println(nomb_clas);
       nombs_clas[i] = nomb_clas;
   }
%>

<!--Obtengo las interseciones de las rutinas con las clasificaciones-->
<%
    List<Rutina> rutisClas = null;
    List<Rutina> rutis1 = null;
    List<Rutina> rutis2 = null;
    List<Rutina> rutis3 = null;
    
    int tam = ids_clas.length;
    try{
        switch(tam){
            case 1:tam = 1;
                rutis1 = MRutina.getRutisPublicByClas(ids_clas[0]);
                rutisClas = rutis1;
            break;
            case 2:tam = 2;
                rutis1 = MRutina.getRutisPublicByClas(ids_clas[0]);
                rutis2 = MRutina.getRutisPublicByClas(ids_clas[1]);
                rutis1.retainAll(rutis2);
                rutisClas = rutis1;
            break;    
            case 3:tam = 3;
                rutis1 = MRutina.getRutisPublicByClas(ids_clas[0]);
                rutis2 = MRutina.getRutisPublicByClas(ids_clas[1]);
                rutis3 = MRutina.getRutisPublicByClas(ids_clas[2]);
                rutis1.retainAll(rutis2);
                rutis1.retainAll(rutis3);
                rutisClas = rutis1;
            break;
        }
    }catch(Exception e){
        System.out.println("error en el switch");
    }
%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>varRutinasPublicas</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.1/normalize.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/glider-js@1.7.3/glider.css">
    <link rel="stylesheet" href="../CSS/verRutinasP.css">
</head>
<header>
    <span class="nav-bar" id="btnMenu"><% 
            if(sesion.getAttribute("creador") != null){
        %> 
                <a href="indexCreador.jsp">
        <%
            }if(sesion.getAttribute("administrador") != null){
         %> 
                <a href="indexCreador.jsp">
        <%
            }else{   
        %>
             <a href="indexDes.jsp">
        <%
        }
        %><!--mamamarrrrrannnooooooo el link a páginas.-->
            <svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-arrow-left-circle" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-4.5-.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>
            </svg>
        </a>
    <i class="fas fa-bars"> Categorias de rutinas</i></span>
<nav class="main-nav">
    <ul class="menu" id="menu">
        <li class="menu__item">
            <a  class="menu__link submenu-btn">Fuerza Muscular <i class="fas fa-chevron-down"></i></a>
            <ul class="submenu">
                <li class="menu__item">
                    <a  class="menu__link sub-submenu-btn" id="botonSuperior">Alto impacto <i class="fas fa-chevron-down"></i></a>
                    <ul class="sub-submenu sub-ntp">
                        <li class="menu__item">
                            <a href="../verRutinasPublicas?clas=1_5_6" class="menu__link">Brazo</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_5_7" class="menu__link">Pierna</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_5_8" class="menu__link">Abdomen</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_5_9" class="menu__link">Pecho</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_5_10" class="menu__link">Espalda</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_5_11" class="menu__link">Glúteos</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_5_12" class="menu__link">Cuerpo completo</a>
                        </li>
                    </ul>
                </li>

                <li class="menu__item">
                    <a  class="menu__link sub-submenu-btn">Bajo impacto <i class="fas fa-chevron-down"></i></a>
                    <ul class="sub-submenu sub-ntp">
                        <li class="menu__item">
                            <a href="../verRutinasPublicas?clas=1_4_6" class="menu__link">Brazo</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_4_7" class="menu__link">Pierna</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_4_8" class="menu__link">Abdomen</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_4_9" class="menu__link">Pecho</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_4_10" class="menu__link">Espalda</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_4_11" class="menu__link">Glúteos</a>
                        </li>
                        <li class="menu__item sub-ntp">
                            <a href="../verRutinasPublicas?clas=1_4_12" class="menu__link">Cuerpo completo</a>
                        </li>
                    </ul>
                </li>

            </ul>
        </li>


        <li class="menu__item">
            <a  class="menu__link submenu-btn">Ejercicio Aeróbico<i class="fas fa-chevron-down"></i></a>
            <ul class="submenu">
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=2_5" class="menu__link sub-submenu-btn" id="botonSuperior">Alto impacto</a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=2_4" class="menu__link sub-submenu-btn">Bajo impacto</a>
                </li>

            </ul>
        </li>


        <li class="menu__item">
            <a href="../verRutinasPublicas?clas=13" class="menu__link submenu-btn">Ejercicios de equilibrio</a>
        </li>


        <li class="menu__item">
            <a  class="menu__link submenu-btn">Estiramiento <i class="fas fa-chevron-down"></i></a>
            <ul class="submenu">
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_6" class="menu__link sub-submenu-btn">En brazo </a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_7" class="menu__link sub-submenu-btn">Pierna </a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_8" class="menu__link sub-submenu-btn">Abdomen </a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_9" class="menu__link sub-submenu-btn">Pecho </a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_10" class="menu__link sub-submenu-btn">Espalda </a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_11" class="menu__link sub-submenu-btn">Glúteos </a>
                </li>
                <li class="menu__item">
                    <a href="../verRutinasPublicas?clas=3_12" class="menu__link sub-submenu-btn">Cuerpo completo </a>
                </li>
            </ul>
        </li>


    </ul>
</nav>
</header>

<body>
<div>
    <div class="main-container"><a class="proposito"><% 
            for(int i = 0; i < ids_clas.length ;i++){
                if(i == ids_clas.length - 1){
                    out.print(nombs_clas[i]);
                }else{
                    out.print(nombs_clas[i] + " - ");
                }
            }
            %></a></div>





<!--Muestro las rutina de rutisClas-->
<% if(rutisClas == null){
%>
        <div class="second-container"  > 
            <div class="carousel">
                <div class="carousel__contenedor">
                    <div class="carousel__lista">
                        <div class="carousel__elemento" id="primerElemento">
                            <section class="fondo" id="vistaRutinas"><img src="http://drive.google.com/uc?export=view&id=1BXZMC8dX5eMNKLx8U5wdjJKAL3xGwDxN"></section>
                        </div>
                    </div>
                </div>
            </div>
            <div role="tablist" class="class__indicadores"></div>
        </div>
<%  
    }else{
%>
        <div class="main-content">
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
            <% 
                Rutina ruti1 = rutisClas.get(0);
                
                ruti1.setEjercicios(MEjercicio.getEjers(ruti1.getId_ruti()));

                List<Ejercicio> ejers1 = (List<Ejercicio>) ruti1.getEjercicios().keySet();
                //Obtengo el id de las imagenes
                List<String> ids_img1 = null;
                //Agrego la primera imagen del  ejercicio, todos lo ejercicios tendrán como minímo dos imágenes
                for(int j=0; j < 5; j++){
                              //el ejercicio.los ids . el primer id
                    ids_img1.add(ejers1.get(j).getIds_img().get(0));   
                }
            %>
                <div class="carousel__elemento" id="primerElemento">
                    <section class="fondo" id="vistaRutinas">
                        <table>
                            <tr>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img1.get(0) %>" alt="">
                                </td>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img1.get(1) %>" alt="">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img1.get(2) %>" alt="">
                                </td>
                                <td>
                                    <img src="http://drive.google.com/uc?export=view&id=<%= ids_img1.get(3) %>" alt="">
                                </td>
                            </tr>
                        </table>
                    </section>
                </div>
            <% 
            
            for(int i = 1; i <= rutisClas.size() ; i++){
                Rutina ruti = rutisClas.get(i);

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
                <div class="carousel__elemento">
                    <section class="fondo" id="vistaRutinas">
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
        </div>
<%  
    }
%>
                    
                    
               

        <script src="https://cdn.jsdelivr.net/npm/glider-js@1.7.3/glider.min.js"></script>
        <script src="../JS/susmain.js"></script>
</body>
<% 
}catch(Exception e){
    System.out.println(e.getMessage());
    response.sendRedirect("../Error/error.html");
}
%>
</html>
