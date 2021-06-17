package Servlets;

import Controlador.MPerfil;
import Modelo.Perfil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofo9
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
@MultipartConfig
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try{
                HttpSession sesion = request.getSession(true);        
           
            
                String nombre = request.getParameter("nombre");
                String apPat = request.getParameter("apellido1");
                String apMat = request.getParameter("apellido2");

                String nomb_perf = nombre+" "+apPat+" "+apMat;
                String email_per = request.getParameter("correo");
                String fecha = request.getParameter("fecha");
                String contra_perf = request.getParameter("password");
                System.out.println(nomb_perf + "\n" + email_per +"\n" + fecha + "\n" + contra_perf);
            
                //Creo la Imagen y la Registro
                String id_img = request.getParameter("img_perf");
                
                if(id_img == null)id_img = "1ub2OWcW0KzUMjE3oME_gxrBsq1EYhxwW";
                
                System.out.println(id_img);

                
                //Creo el Perfil
                Perfil perf = new Perfil();
                perf.setNomb_perf(nomb_perf);
                perf.setEmail_per(email_per);
                perf.setAdmin(false);
                perf.setCrea(false);
                perf.setPass_perf(contra_perf);

                Date fecNaci_perf = Date.valueOf(fecha);
                perf.setDateNaci_perf(fecNaci_perf);
                System.out.println(perf.getDateNaci_perf().toString());

                perf.setId_img(id_img);

                System.out.println("Antes de registrar el perfil");
                try{
                    MPerfil.addPerfil(perf);
                }catch(Exception e){}
                
                int id_perf = 0;
                try{
                    id_perf = MPerfil.getIdPerfil(perf.getEmail_per(), perf.getPass_perf());
                }catch(Exception e){}
               
                
                perf.setId_perf(id_perf);
                System.out.println(perf.getId_perf());
            
                
                sesion.setAttribute("perfil", perf.getId_perf());
                
                
                
                RequestDispatcher  view =  request.getRequestDispatcher("PedirDatosUsuario.jsp");
                view.forward(request, response);
                
                
                
            }catch(Exception e){
                System.out.println("Error al Registrar el Perfil");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                RequestDispatcher  view =  request.getRequestDispatcher("Error/error.html");
                view.forward(request, response);
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        }
               
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
            
            
            
            
            
            
                
        }
            
            

    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
