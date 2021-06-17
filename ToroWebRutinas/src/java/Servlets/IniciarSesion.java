/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.MPerfil;
import Controlador.MUsuario;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sofo9
 */
public class IniciarSesion extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String email_perf = request.getParameter("correo");
            System.out.println(email_perf);
            String pas_perf = request.getParameter("password");
            System.out.println(pas_perf);
            
            int id_perf = 0;
            try{
                
                id_perf = MPerfil.getIdPerfil(email_perf, pas_perf);
                
            }catch(Exception e){
                System.out.println("Error al conseguir el Id del perfil");
                response.sendRedirect("Error/error.html");
            }
            
            System.out.println(id_perf);
            if(id_perf == 0){
                response.sendRedirect("Error/error.html");
            }
            
            
            
            Usuario usu = null;
            try{
                usu = MUsuario.getUsuById(id_perf);
                
            }catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Error al conseguir al Usuario Relacionado con el Id del Perfil");
                response.sendRedirect("Error/error.html");
            }
           
            if(usu == null){
                response.sendRedirect("Error/error.html");
            }
            
            
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("perfil", usu.getId_perf());
            
            response.sendRedirect("JSP/indexDes.jsp");
        }catch(Exception e){
            System.out.println("Error al Iniciar Sesion");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(IniciarSesion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
