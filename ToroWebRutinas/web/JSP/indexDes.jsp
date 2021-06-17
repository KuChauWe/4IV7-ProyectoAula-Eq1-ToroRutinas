<%-- 
    Document   : indexDes
    Created on : 5/06/2021, 11:40:34 PM
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

<!--Calculo las clasificaciones recomendadas segun el Usuario MRutina.getRutisPublicByClas(id_clas)-->
<%
//    Calculo el IMC
    double dou = perf.getPeso_usu()/(Math.sqrt(perf.getAltu_usu()));
    float imc = (float) dou;
    
//    Calculo la edad
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate fechaNac = LocalDate.parse(perf.getDateNaci_perf().toString(), fmt);
    LocalDate ahora = LocalDate.now();

    Period periodo = Period.between(fechaNac, ahora);
    
    int edad = periodo.getYears();
    
    List<Rutina> rutisClas = null;

    if( (imc > 16 && imc < 35) |  edad < 50 ){
        List<Rutina> rutis1 = null;
        List<Rutina> rutis2 = null;
        try{
            rutis1 = MRutina.getRutisPublicByClas(MClasificacion.getIdClas("AltoImpacto"));
            rutis2 = MRutina.getRutisPublicByClas(MClasificacion.getIdClas("FuerzaMuscular"));
            rutis1.retainAll(rutis2);
            rutisClas = rutis1;
        }catch(Exception e){
            System.out.println("Error al conseguir las rutians");
            System.out.println(e.getMessage());
        }
        
        
        
        
        
    }else{
        List<Rutina> rutis1 = null;
        List<Rutina> rutis2 = null;
        List<Rutina> rutis3 = null;
        try{
            rutis1 = MRutina.getRutisPublicByClas(MClasificacion.getIdClas("BajoImpacto"));
            rutis2 = MRutina.getRutisPublicByClas(MClasificacion.getIdClas("Aerobico"));
            rutis3 = MRutina.getRutisPublicByClas(MClasificacion.getIdClas("Equilibrio"));
            
            rutis1.retainAll(rutis2);
            rutis1.retainAll(rutis3);
            rutisClas = rutis1;
        }catch(Exception e){
            System.out.println("Error al conseguir las rutians");
            System.out.println(e.getMessage());
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
    <link rel="stylesheet" href="../CSS/index(desbloqueado).css">
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
                <button class="iniciarSesion btnHeader"><a class="textBtn"  href="consultarRutinasActuales.jsp">Consultar mis rutinas</a></button>
            </section>
            <section>
                <button class="registrarse btnHeader"><a class="textBtn" href="creadorRutina.jsp">Crear rutina</a></button>
            </section>
            <section>
                <button class="registrarse btnHeader"><a class="textBtn" href="verRutinasPublicas.jsp">Ver más rutinas...</a></button>
            </section>
        </nav>
        <nav class="bamrram tercerElemento">
            <section>
                <a class="textBtn perfil" href="PerfilUsuario.jsp"><img class="perfilimg"
                            src="http://drive.google.com/uc?export=view&id=<%=perf.getId_img()%>"></a>
            </section>
        </nav>

</header>
<body>
    <div class="slide-contenedor">
        <nav id="contenedorPrincipal">
            <!--Aqui esta el carusel principarl pra-->
            
                <!--Si no hay Rutinas Publicas con la clasificación-->
            <% 
                if(rutisClas == null){
            %>
                <div class="miSlider fade">
                    <section id="vistaRutina">
                        <img src="http://drive.google.com/uc?export=view&id=1EH-f0E4kDShUBzm2w8QPgYkNC-Mcuihb">
                    </section>
                </div>  
                <!--Si existen rutinas publicas con esa clasificación-->
            <%
                }else{
                    for(int i = 0; i < 4; i++){
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
                        <div class="miSlider fade">
                            <section id="vistaRutina">
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
                }
            %>
    
    <% if(rutisClas != null){ %>    
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
                if(rutisClas == null){
            %>
                    <div class="carousel__elemento" id="primerElemento">
                        <section id="vistaRutinas"><img src="http://drive.google.com/uc?export=view&id=1jPq_K_YwrW-hl6KP7iuBw7uoYDDjHknC"></section>
                    </div>
            <%
                }else{
            %>
                    <div class="carousel__elemento" id="primerElemento">
                        <section id="vistaRutinas"><%=rutisClas.get(4).getNomb_ruti()%></section>
                    </div>

            <%
                
                    for(int i=5; i <= 13; i++){
                        
            %>
                        <div class="carousel__elemento">
                            <section id="vistaRutinas"><%= rutisClas.get(i).getNomb_ruti() %></section>
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
    <%} %>
            
            
            
    <script src="https://cdn.jsdelivr.net/npm/glider-js@1.7.3/glider.min.js"></script>
    <script src="../JS/index(des).js"></script>
</body>
<% 
}catch(Exception e){
    System.out.println(e.getMessage());
    response.sendRedirect("../Error/error.html");
}
%>
</html>
