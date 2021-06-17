package Controlador;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author sofo9
 */
public class MClasificacion {
    public static String getClasById(int id_clas) throws SQLException{
        String nomb_clas = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from MClasificacion where id_clas = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, id_clas);
            
             rs = ps.executeQuery();
            while(rs.next()){
                nomb_clas = rs.getString("nomb_clas");
                System.out.println("Clasificacion encontrada");
            }
            
            
        }catch(Exception e){
            System.out.println("Error al buscar la clasificacion");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
        
        return nomb_clas;
    }
    
    public static int getIdClas(String nomb_clas) throws SQLException{
        int id_clas = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select id_clas from mclasificacion where nomb_clas = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, nomb_clas);
            
            rs = ps.executeQuery();
            while(rs.next()){
                id_clas = rs.getInt("id_clas");
                System.out.println("Se encontro el id de la clasificacion");
            }
            
            
        }catch(Exception e){
            System.out.println("Error al buscar el ID de la clasificacion");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
        
        return id_clas;
    }
    
    //Crud del las tablas de EEjerClas y ERutiClas
    
    // Agregar 
    public static int addRutiClas(int id_ruti, int id_clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Insert into ERutiClas (id_ruti, id_clas) values (?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            ps.setInt(2, id_clas);
            
            estatus = ps.executeUpdate();
            
        
            System.out.println("Se ha registrado correctamente el id_ruti y id_clas en ERutiClas");

        }catch(Exception e){
            System.out.println("Error al registrar el id_ruti y id_clas en ERutiClas");
            System.out.println(e.getMessage());
        
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return estatus;
    }
    public static int addEjerClas(int id_ejer, int id_clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Insert into EEjerClas (id_ejer, id_clas) values (?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            ps.setInt(2, id_clas);
            
            estatus = ps.executeUpdate();
            
        
            System.out.println("Se ha registrado correctamente el id_ejer y id_clas en EEjerClas");

        }catch(Exception e){
            System.out.println("Error al registrar el id_ejer y id_clas en EEjerClas");
            System.out.println(e.getMessage());
        
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return estatus;
    }
    
    //Borrar
    public static int deleteRutiClas(int id_ruti, int id_clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Delete from ERutiClas where id_ruti = ? and id_clas = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            ps.setInt(2, id_clas);
            
            estatus = ps.executeUpdate();
            
        
            System.out.println("Se ha borrado correctamente el id_ruti y id_clas en ERutiClas");

        }catch(Exception e){
            System.out.println("Error al borrar el id_ruti y id_clas en ERutiClas");
            System.out.println(e.getMessage());
        
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return estatus;
    }
    public static int deleteEjerClas(int id_ejer, int id_clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Delete from EEjerClas where id_ejer = ? and id_clas = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            ps.setInt(2, id_clas);
            
            estatus = ps.executeUpdate();
            
        
            System.out.println("Se ha borrado correctamente el id_ejer y id_clas en EEjerClas");

        }catch(Exception e){
            System.out.println("Error al borrar el id_ejer y id_clas en EEjerClas");
            System.out.println(e.getMessage());
        
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return estatus;
    }
    
    
    public static List<String> getClasRuti(int id_ruti) throws SQLException{
        List<String> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select id_clas from ERutiClas where id_ruti = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            
             rs = ps.executeQuery();
            while(rs.next()){
                lista.add(MClasificacion.getClasById(rs.getInt("id_clas")));
            }
        
            System.out.println("Consulta exito de las clasificaciones de la Rutina");

        }catch(Exception e){
            System.out.println("Error al consultar las clasificaciones de la rutina");
            System.out.println(e.getMessage());
        
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return lista;
    }
    public static List<String> getClasEjer(int id_ejer) throws SQLException{
        List<String> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select id_clas from EEjerClas where id_ejer = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            
             rs = ps.executeQuery();
            while(rs.next()){
                lista.add(MClasificacion.getClasById(rs.getInt("id_clas")));
            }
        
            System.out.println("Consulta exito de las clasificaciones del ejercicio");
     
        }catch(Exception e){
            System.out.println("Error al consultar las clasificaciones del ejercicio");
            System.out.println(e.getMessage());
        
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return lista;
    }
     
}
