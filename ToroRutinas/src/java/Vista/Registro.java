package Vista;

import Controlador.AccionesPerfil;
import Modelo.Perfil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Registro extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String nom_perf = request.getParameter("nom_perf");
            String email_per = request.getParameter("email_per");
            Date fechNaci_perf = request.getParameter("fechNaci_perf");
            Boolean sexo = request.getParameter("sexo");
            String contra_perf = request.getParameter("contra_perf");
            
            Perfil e = new Perfil();
            
            e.setNom_perf(nom_perf);
            e.setEmail_per(email_per);
            e.setFechNaci_perf((java.sql.Date) fechNaci_perf);
            e.setSexo(sexo);
            e.setContra_perf(contra_perf);
             
            int estatus = AccionesPerfil.registrarPerfil(e);
            
            if(estatus > 0){
                response.sendRedirect("Registrarse.jsp");
            }else{
                response.sendRedirect("error.jsp");
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
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