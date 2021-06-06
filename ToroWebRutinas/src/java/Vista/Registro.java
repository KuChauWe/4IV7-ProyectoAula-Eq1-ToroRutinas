/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.AccionesImagen;
import Controlador.AccionesPerfil;
import Modelo.*;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Blob;
import java.sql.Date;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static jdk.nashorn.internal.objects.NativeError.getFileName;

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
            
            HttpSession idsesion = request.getSession(true);        
            //vamos a guardar todos los datos de sesion en "cookie"
            
            Integer perfil = (Integer)idsesion.getAttribute("perfil.ss");
            
            if(perfil == null){
                perfil = new Integer(1);
            }else{
                perfil = new Integer(perfil.intValue()+1);
            }
            
            idsesion.setAttribute("perfil.ss", perfil);
            
            
            Enumeration nombreParametros = idsesion.getAttributeNames();
            
            while(nombreParametros.hasMoreElements()){
                String parametro = (String)nombreParametros.nextElement();
                Object valor = idsesion.getAttribute(parametro);
                System.out.println("Atributos de la sesion: " + parametro 
                        + " Valor: " + valor.toString());
            }
            
            
            
            
            
            
            
            
            
            Integer estatus = 0;
            String nombre = request.getParameter("nomb_perf");
            String apPat = request.getParameter("apellido1");
            String apMat = request.getParameter("apellido2");
            
            String nom_perf = nombre+" "+apPat+" "+apMat;
            String email_per = request.getParameter("correo");
            String fecha = request.getParameter("fecha");
            String contra_perf = request.getParameter("pass_perf");
            System.out.println(nom_perf + "\n" + email_per +"\n" + fecha + "\n" + contra_perf);
            
            try{
                //Creo la Imagen y la Registro
                InputStream inputStream = null;
                Imagen img_pef = new Imagen();
                

                img_pef.setFoto_img(request.getPart("img_perf").getInputStream());
                img_pef.setNom_img(request.getPart("img_perf").getSubmittedFileName());

                
                System.out.println(img_pef.getFoto_img().toString());
                System.out.println(img_pef.getNom_img());


                //Registro de la Imagen

                int id_img = 0;
                try{
                     AccionesImagen.registrarImagen(img_pef);
                }catch(Exception e){}
                try{
                     id_img = AccionesImagen.getId(img_pef);
                }catch(Exception e){}
                
                img_pef.setId_img(id_img);
                
                
                
                System.out.println(img_pef.getId_img());
                //Creo el Perfil
                Perfil perf = new Perfil();
                perf.setNom_perf(nom_perf);
                perf.setEmail_per(email_per);
                perf.setAdmin(false);
                perf.setCreador(false);
                perf.setContra_perf(contra_perf);

                Date fecNaci_perf = Date.valueOf(fecha);
                perf.setFechNaci_perf(fecNaci_perf);
                System.out.println(perf.getFechNaci_perf().toString());

                perf.setImg_perf(img_pef);

                System.out.println("Antes de registrar el perfil");
                try{
                    AccionesPerfil.registrarPerfil(perf);
                }catch(Exception e){}
                
                int id_perf = 0;
                try{
                    AccionesPerfil.getIdPerfil(perf.getEmail_per(), perf.getContra_perf());
                }catch(Exception e){}
               
                
                perf.setId_perf(id_perf);
                System.out.println(perf.getId_perf());
            
            
            }catch(Exception e){
                System.out.println("Error al Registrar el Perfil");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                RequestDispatcher  view =  request.getRequestDispatcher("error.jsp");
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
