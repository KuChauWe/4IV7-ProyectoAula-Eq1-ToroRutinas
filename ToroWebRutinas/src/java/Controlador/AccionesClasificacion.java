package Controlador;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
/**
 *
 * @author sofo9
 */
public class AccionesClasificacion {
    
    public static int registrarClasificacion(Clasificacion clas){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Insert into MClasificacion (nom_clas, quemaCalo_clas)"
                    + "values (?,?)";
            
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, clas.getNom_clas());
            ps.setBoolean(2, clas.isQuemaCalo());
            
            estatus = ps.executeUpdate();
            System.out.println("Clasificacion registrada");
            con.close();
        
        }catch(Exception e){
            System.out.println("Error al registrar la clasificacion");
            System.out.println(e.getMessage());
        }
    
        return estatus;
    }
    
    public static int actualizarClasificacion(Clasificacion clas){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Update MClasificacion set nom_clas = ?, quemaCalo_clas = ?"
                    + " where id_clas = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, clas.getNom_clas());
            ps.setBoolean(2, clas.isQuemaCalo());
            ps.setInt(3, clas.getId_clas());
            
            estatus = ps.executeUpdate();
            System.out.println("Actualización exitosa de la clasificacion");
            con.close();
            
            
        
        }catch(Exception e){
            System.out.println("Error al actualizar la clasificacion");
            System.out.println(e.getMessage());
        }
        
        return estatus;
    
    }
    
    public static int borrarClasificacion(Clasificacion clas){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "delete from MClasificacion where id_clas = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, clas.getId_clas());
            
            estatus = ps.executeUpdate();
            System.out.println("Clasificacion borrada con exito");
            con.close();
            
        
        }catch(Exception e){
            System.out.println("Error al borrar la clasificacion");
            System.out.println(e.getMessage());
        
        }
        
        
    
        return estatus;
    }
    
    public static List<Clasificacion> buscarAllClasificaciones(){
        List<Clasificacion> lista = null;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from MClasificacion";
            PreparedStatement ps = con.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
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
            
            
        
        }
    
        return lista;
    }
    
    //Probablemente mande error
    public static List<Clasificacion> buscarClasificacionesRutina(Rutina ruti){
        List<Clasificacion> lista = null;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from ERutinaClasificacion where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 Clasificacion clas = new Clasificacion();
                clas.setId_clas(rs.getInt("id_clas"));
                clas.setNom_clas(rs.getString("nom_clas"));
                clas.setQuemaCalo(rs.getBoolean("quemaCalo_clas"));
                lista.add(clas);
                
            }
        
            System.out.println("Consulta exito de las clasificaciones de la Rutina");

        }catch(Exception e){
            System.out.println("Error al consultar las clasificaciones de la rutina");
            System.out.println(e.getMessage());
        
        
        }
    
        return lista;
    }
    
    //Probablemente mande error
    public static List<Clasificacion> buscarClasificacionesEjercicio(Ejercicio ejer){
        List<Clasificacion> lista = null;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from ERutinaClasificacion where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ejer.getId_ejer());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 Clasificacion clas = new Clasificacion();
                clas.setId_clas(rs.getInt("id_clas"));
                clas.setNom_clas(rs.getString("nom_clas"));
                clas.setQuemaCalo(rs.getBoolean("quemaCalo_clas"));
                lista.add(clas);
                
            }
        
            System.out.println("Consulta exito de las clasificaciones del ejercicio");
     
        }catch(Exception e){
            System.out.println("Error al consultar las clasificaciones del ejercicio");
            System.out.println(e.getMessage());
        
        
        }
    
        return lista;
    }
     
}
