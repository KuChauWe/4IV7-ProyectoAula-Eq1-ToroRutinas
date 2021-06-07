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
public class AccionesClasificacion {
    
    public static int registrarClasificacion(Clasificacion clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Insert into MClasificacion (nom_clas, quemaCalo_clas)"
                    + "values (?,?)";
            
             ps = con.prepareStatement(q);
            ps.setString(1, clas.getNom_clas());
            ps.setBoolean(2, clas.isQuemaCalo());
            
            estatus = ps.executeUpdate();
            System.out.println("Clasificacion registrada");
            con.close();
        
        }catch(Exception e){
            System.out.println("Error al registrar la clasificacion");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return estatus;
    }
    
    public static int actualizarClasificacion(Clasificacion clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Update MClasificacion set nom_clas = ?, quemaCalo_clas = ?"
                    + " where id_clas = ?";
             ps = con.prepareStatement(q);
            ps.setString(1, clas.getNom_clas());
            ps.setBoolean(2, clas.isQuemaCalo());
            ps.setInt(3, clas.getId_clas());
            
            estatus = ps.executeUpdate();
            System.out.println("Actualización exitosa de la clasificacion");
            con.close();
            
            
        
        }catch(Exception e){
            System.out.println("Error al actualizar la clasificacion");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
        
        return estatus;
    
    }
    
    public static int borrarClasificacion(Clasificacion clas) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "delete from MClasificacion where id_clas = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, clas.getId_clas());
            
            estatus = ps.executeUpdate();
            System.out.println("Clasificacion borrada con exito");
            con.close();
            
        
        }catch(Exception e){
            System.out.println("Error al borrar la clasificacion");
            System.out.println(e.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
        
        
    
        return estatus;
    }
    
    public static Clasificacion buscarClasificacionById(int id_clas) throws SQLException{
        Clasificacion clas = null;
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
                clas.setId_clas(rs.getInt("id_clas"));
                clas.setNom_clas(rs.getString("nomb_clas"));
                clas.setQuemaCalo(rs.getBoolean("quemaCalo_clas"));
            }
            
            
        }catch(Exception e){
            System.out.println("Error al buscar la clasificacion");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
        
        return clas;
    }
    
   public static int getIDClasificacion(String clas) throws SQLException{
        int id_clas = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select id_clas from mclasificacion where nom_clas = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, id_clas);
            
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
    
    public static List<Clasificacion> buscarAllClasificaciones() throws SQLException{
        List<Clasificacion> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from MClasificacion";
             ps = con.prepareStatement(q);
             rs = ps.executeQuery();
            while(rs.next()){
                Clasificacion clas = new Clasificacion();
                clas.setId_clas(rs.getInt("id_clas"));
                clas.setNom_clas(rs.getString("nom_clas"));
                clas.setQuemaCalo(rs.getBoolean("quemaCalo_clas"));
                lista.add(clas);
            
            
            }
        
        
        }catch(Exception e){
            System.out.println("Error al consultar las clasificaciones");
            System.out.println(e.getMessage());
            
            
        
        }finally{
            rs.close();
            ps.close();
            con.close();
	}
    
        return lista;
    }
    
    public static List<Clasificacion> buscarClasificacionesRutina(Rutina ruti) throws SQLException{
        List<Clasificacion> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from ERutinaClasificacion where id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            
             rs = ps.executeQuery();
            while(rs.next()){
                Clasificacion clas = new Clasificacion();
                clas = AccionesClasificacion.buscarClasificacionById(rs.getInt("id_clas"));
                lista.add(clas);
                
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
    
    
    
    //Probablemente mande error
    public static List<Clasificacion> buscarClasificacionesEjercicio(Ejercicio ejer) throws SQLException{
        List<Clasificacion> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from ERutinaClasificacion where id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, ejer.getId_ejer());
            
             rs = ps.executeQuery();
            while(rs.next()){
                Clasificacion clas = new Clasificacion();
                clas = AccionesClasificacion.buscarClasificacionById(rs.getInt("id_clas"));
                lista.add(clas);
                
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
