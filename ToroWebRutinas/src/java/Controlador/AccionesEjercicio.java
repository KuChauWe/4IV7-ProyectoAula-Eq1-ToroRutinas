package Controlador;
import Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author sofo9
 */
public class AccionesEjercicio {
    
    public static int RegistrarEjercicio(Ejercicio ejer) throws ServletException, ClassNotFoundException, SQLException{
        int estatus = 0;
        try{
            
            Connection con = ConexionSQL.getConnection();

            String q = "insert into MEjercicio(nomb_ejer, calPerd "
                    + "values (?,?))";

            PreparedStatement ps = con.prepareStatement(q);

            ps.setString(1, ejer.getNom_ejer());
            ps.setFloat(2, ejer.getCalPer());

            estatus = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error al registrar al perfil");
            System.out.println(e.getMessage());
        
        }
        
    
    
        return estatus;
    }
    
    public static int actualizarEjercicio(Ejercicio ejer, int id_ejer){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            
            String q = "Update MEjercicio set nomb_ejer = ?, calPerd = ?"
                    + "where id_ejer = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, ejer.getNom_ejer());
            ps.setFloat(2, ejer.getCalPer());
            ps.setInt(3, ejer.getId_ejer());
            
            
            
            
        
        }catch(Exception e){
            System.out.println("Error al Modificar el Ejercicio");
            System.out.println(e.getMessage());
        }
        
        return estatus;
    }
    
    public static Ejercicio buscarEjercicioById(int id_ejer){
        Ejercicio ejer = new Ejercicio();
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "select * from MEjercicio where id_ejer = ? ";
            
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ejer.setId_ejer(rs.getInt("id_ejer"));
                ejer.setCalPer(rs.getFloat("calPerd"));
            }
            
            ejer.setImg(AccionesImagen.getImagenes(id_ejer));
            
        
        
        }catch(Exception e){
            System.out.println("Error al buscar el ejercicio");
            System.out.println(e.getMessage());
        
        }
    
        return ejer;
    }
    
}
