package Controlador;
import Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GaelFernandez
 */


public class AccionesPerfil extends HttpServlet {

    public static int registrarPerfil(Perfil e){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "insert into MPerfil(id_perf, nomb_perf, id_img, email_per, "
                    + "contra_perf, fechNaci_perf, admin, creador) "
                    + "values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, e.getId_perf());
            ps.setString(2, e.getNom_perf());
            ps.setInt(3, e.getImg_perf().getId_img());
            ps.setString(4, e.getEmail_per());
            ps.setString(5, e.getContra_perf());
            ps.setDate(6, e.getFechNaci_perf());
            ps.setBoolean(7, e.isAdmin());
            ps.setBoolean(8, e.isCreador());
            
            estatus = ps.executeUpdate();
            System.out.println("Registro exitoso del perfil");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al registrar al perfil");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
    }
    
    public static int actualizarPerfil(Perfil e){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "update MPerfil set nomb_perf = ?, email_per = ?,"
                    + "contra_perf = ?, fechNaci_perf = ? "
                    + "where id_perf = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setString(1, e.getNom_perf());
            ps.setString(2, e.getEmail_per());
            ps.setString(3, e.getContra_perf());
            ps.setDate(4, e.getFechNaci_perf());
            ps.setInt(5, e.getId_perf());
            
            estatus = ps.executeUpdate();
            System.out.println("Actualización exitosa del perfil");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al actualizar el perfil");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
    }
    
    public static int borrarPerfil(int id){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "delete from MPerfil where id_perf = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            System.out.println("Perfil eliminado exitosamente");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al borrar al perfil");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
    }
    
    public static Perfil buscarPerfilById(int id){
        Perfil e = new Perfil();
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "select * from MPerfil where id_perf = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e.setId_perf(rs.getInt("id_perf"));
                e.setNom_perf(rs.getString(2));
                e.setContra_perf(rs.getString(3));
                
                Imagen img = new Imagen();
                
                img = AccionesImagen.buscarImagenById(rs.getInt("id_img"));
                
                e.setImg_perf(img);
                
                
                e.setEmail_per(rs.getString(4));
                e.setFechNaci_perf(rs.getDate(5));
            }
            
            System.out.println("Perfil encontrado");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar el perfil");
            System.out.println(ed.getMessage());
        
        }
        return e;
    }
    
    public static List<Perfil> buscarAllPerfiles(){
        List<Perfil> lista = new ArrayList<Perfil>();
        
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "select * from MPerfil";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Perfil e = new Perfil();
                e.setId_perf(rs.getInt(1));
                e.setNom_perf(rs.getString(2));
                e.setContra_perf(rs.getString(3));
                e.setEmail_per(rs.getString(4));
                e.setFechNaci_perf(rs.getDate(5));
                lista.add(e);
            }
            
            System.out.println("Perfiles encontrados");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar los perfiles");
            System.out.println(ed.getMessage());
        
        }
        return lista;
    }
    
}
