package Controlador;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author sofo9
 */


public class MPerfil extends HttpServlet {

    public static int addPerfil(Perfil e) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "insert into MPerfil(nomb_perf, id_img, email_perf, "
                    + "pass_perf, dateNaci_perf, isAdmin, isCrea) "
                    + "values(?,?,?,?,?,?,?)";
            
             ps = con.prepareStatement(q);
            
            ps.setString(1, e.getNomb_perf());
            ps.setString(2, e.getId_img());
            ps.setString(3, e.getEmail_per());
            ps.setString(4, e.getPass_perf());
            ps.setDate(5, e.getDateNaci_perf());
            ps.setBoolean(6, e.isAdmin());
            ps.setBoolean(7, e.isCrea());
            
            estatus = ps.executeUpdate();
            System.out.println("Registro exitoso del perfil");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al registrar al perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static int updatePerfil(Perfil e, int id_perf) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "Update MPerfil set nomb_perf = ?, email_perf = ?,"
                    + "pass_perf = ?, dateNaci_perf = ?, isAdmin = ?, isCrea = ? "
                    + "where id_perf = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setString(1, e.getNomb_perf());
            ps.setString(2, e.getEmail_per());
            ps.setString(3, e.getPass_perf());
            ps.setDate(4, e.getDateNaci_perf());
            ps.setBoolean(5, e.isAdmin());
            ps.setBoolean(6, e.isCrea());
            ps.setInt(7, id_perf);
            
            estatus = ps.executeUpdate();
            System.out.println("Actualización exitosa del perfil");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al actualizar el perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static int deletePerfil(int id_perf) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "Delete from MPerfil where id_perf = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_perf);
            
            estatus = ps.executeUpdate();
            System.out.println("Perfil eliminado exitosamente");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al borrar al perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static Perfil getPerfilById(int id_perf) throws SQLException{
        Perfil e = new Perfil();
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "select * from MPerfil where id_perf = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_perf);
            
             rs = ps.executeQuery();
            if(rs.next()){
                e.setId_perf(rs.getInt("id_perf"));
                e.setNomb_perf(rs.getString("nomb_perf"));
                e.setPass_perf(rs.getString("pass_perf"));
                e.setAdmin(rs.getBoolean("isAdmin"));
                e.setCrea(rs.getBoolean("isCrea"));
                e.setDateNaci_perf(rs.getDate("dateNaci_perf"));
                e.setEmail_per(rs.getString("email_perf"));
                e.setId_img(rs.getString("id_img"));
               
            }
            
            System.out.println("Perfil encontrado");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar el perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return e;
    }
    
    public static int getIdPerfil(String email_perf, String pass_perf) throws SQLException{
        int id_perf = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

        try{
            con = ConexionSQL.getConnection();
            String q = "select id_perf from MPerfil where email_perf = ? and pass_perf = ?";
            
            ps = con.prepareStatement(q);
            ps.setString(1, email_perf);
            ps.setString(2, pass_perf);
            
            rs = ps.executeQuery();
            if(rs.next()){
                id_perf = rs.getInt("id_perf");
            }
            if(id_perf != 0){
                System.out.println("ID del Perfil encontrado");
            }
            
        
        }catch(Exception ed){
            System.out.println("Error al buscar el ID del perfil");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return id_perf;   
    }
    
    
}
