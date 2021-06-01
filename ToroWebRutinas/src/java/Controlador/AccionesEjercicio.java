package Controlador;
import Modelo.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;


/**
 *
 * @author sofo9
 */
public class AccionesEjercicio {
    
    public static int RegistrarEjercicio(Ejercicio ejer) throws ServletException, ClassNotFoundException, SQLException{
        List<Integer> listaestatus = null;
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            
             con = ConexionSQL.getConnection();

            String q = "insert into MEjercicio(nomb_ejer, calPerd "
                    + "values (?,?))";

             ps = con.prepareStatement(q);

            ps.setString(1, ejer.getNom_ejer());
            ps.setFloat(2, ejer.getCalPer());
            
            listaestatus.add(ps.executeUpdate());
            
            
            ejer.setId_ejer(AccionesEjercicio.buscarIdByEjercicio(ejer));
            AccionesEjercicio.vincularImagenes(ejer);
            AccionesClasificacion.buscarClasificacionesEjercicio(ejer);
            
            
            
            if(ejer.isPublica() == true){
                AccionesEjercicio.agregarABibliotecaPublica(ejer);
                
            }
            System.out.println("Ejercicio registrado");
            
        }catch(Exception e){
            System.out.println("Error al registrar el ejercicio");
            System.out.println(e.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
    
    
        return estatus;
    }
    
   
    
    public static int actualizarEjercicio(Ejercicio ejer) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            
            String q = "Update MEjercicio set nomb_ejer = ?, calPerd = ?"
                    + "where id_ejer = ?";
             ps = con.prepareStatement(q);
            ps.setString(1, ejer.getNom_ejer());
            ps.setFloat(2, ejer.getCalPer());
            ps.setInt(3, ejer.getId_ejer());
            
            
            
            
        
        }catch(Exception e){
            System.out.println("Error al Modificar el Ejercicio");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    }
    
    public static Ejercicio buscarEjercicioById(int id_ejer) throws SQLException{
        Ejercicio ejer = new Ejercicio();
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "select * from MEjercicio where id_ejer = ? ";
            
             ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            
             rs = ps.executeQuery();
            if(rs.next()){
                ejer.setId_ejer(rs.getInt("id_ejer"));
                ejer.setCalPer(rs.getFloat("calPerd"));
            }
            
            
            ejer.setImg(AccionesImagen.getImagenesEjercicio(ejer));
            
        
        
        }catch(Exception e){
            System.out.println("Error al buscar el ejercicio");
            System.out.println(e.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
    
        return ejer;
    }
    
    public static int buscarIdByEjercicio(Ejercicio ejer) throws SQLException{
        int id = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from MEjercicio where nombre_ejer = ?,calPerd = ?";
             ps = con.prepareStatement(q);
            ps.setString(1, ejer.getNom_ejer());
            ps.setFloat(2, ejer.getCalPer());
            
             rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id_ruti");
            }
            
            System.out.println("Ejercicio encontrado con la Id");
            
            
            
        }catch(Exception e){
            System.out.println("Error al buscar el Ejercicio con Id");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return id;
    }
    
    public static List<Ejercicio> buscarAllEjercicios() throws SQLException{
        List<Ejercicio> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from MEjercicio";
             ps = con.prepareStatement(q);
             rs = ps.executeQuery();
            while(rs.next()){
                Ejercicio ejer = new Ejercicio();
                ejer.setId_ejer(rs.getInt("id_ejer"));
                ejer.setNom_ejer(rs.getString("nombre_ejer"));
                ejer.setCalPer(rs.getFloat("caloPerd"));
                lista.add(ejer);
                System.out.println("Exitosa la consulta de los ejercicios");
            
            }
        
        
        }catch(Exception e){
            System.out.println("Error al consultar los ejercicios");
            System.out.println(e.getMessage());
            
            
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
    
        return lista;
    }
    
    public static List<Ejercicio> buscarEjerciciosRutina(Rutina ruti) throws SQLException{
        List<Ejercicio> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from DEjercicioEnRutina";
             ps = con.prepareStatement(q);
            
             rs = ps.executeQuery();
            while(rs.next()){
                
                Ejercicio ejer = new Ejercicio();
                ejer = AccionesEjercicio.buscarEjercicioById(rs.getInt("id_ejer"));
                lista.add(ejer);
                
            
            }
            System.out.println("Exitosa la consulta de los ejercicios");
        
        }catch(Exception e){
            System.out.println("Error al consultar los ejercicios");
            System.out.println(e.getMessage());
            
            
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
    
        return lista;
    }
    
    //############################## Vincular y Desvincular Imagenes (EImagen) #####################################
    
     public static int vincularImagenes(Ejercicio ejer) throws SQLException{
        List<Integer> listaestatus = null;
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            for(Imagen img: ejer.getImg()){
                String q = "Insert into EImagen (id_img, id_ejer)"
                        + "values (?,?)";
                 ps = con.prepareStatement(q);
                ps.setInt(1, img.getId_img());
                ps.setInt(2, ejer.getId_ejer());
                listaestatus.add(ps.executeUpdate());
            }
            if(listaestatus.contains(0) != true){
                estatus = 1;
            }
            
            
            System.out.println("Se vincularon exitosamente las imagenes");
            
            
        }catch(Exception e){
            System.out.println("Error al vincular las imagenes con el ejercicio");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        
        return estatus;
    }
     
     public static int desvincularImagenes(Ejercicio ejer) throws SQLException{
        List<Integer> listaestatus = null;
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            for(Imagen img: ejer.getImg()){
                String q = "delete from EImagen where id_ejer = ?";
                
                 ps = con.prepareStatement(q);
                ps.setInt(1, ejer.getId_ejer());
                listaestatus.add(ps.executeUpdate());
            }
            if(listaestatus.contains(0) != true){
                estatus = 1;
            }
            
            
            System.out.println("Se desvincularon exitosamente las imagenes");
            
            
        }catch(Exception e){
            System.out.println("Error al desvincular las imagenes con el ejercicio");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        
        return estatus;
     }

     //############################# Vinculación y Desvinculación de Clasificacion (ERutinaClasificacion) #######################
        
    public static int vincularClasificaciones(Ejercicio ejer) throws SQLException{
        List<Integer> listaestatus = null;
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            for(Clasificacion clas:ejer.getClas()){
                String q = "Insert into EEjercicioClasificacion (id_ruti, id_clas)"
                        + "values (?,?)";
                 ps = con.prepareStatement(q);
                ps.setInt(1, ejer.getId_ejer());
                ps.setInt(2, clas.getId_clas());
                listaestatus.add(ps.executeUpdate());
                ps.close();
            }
            if(listaestatus.contains(0) != true){
                estatus = 1;
                System.out.println("Se vincularon exitosamente las clasificaciones con lo ejercicios");
            }
            
        }catch(Exception e){
            System.out.println("Error al vincular las clasificaciones con la Rutina");
            System.out.println(e.getMessage());    
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    }
    
    public static int desvincularAllClasificaciones(Rutina ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            
            String q = "delete from EEjercicioClasificacion where id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            System.out.println("Se desvincularon las clasificaciones de la Rutina con exito");
            estatus = ps.executeUpdate();
            
            
            
        }catch(Exception e){
            System.out.println("Error al desvincular las clasificaciones con la Rutina");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
        
        
        
    }




    //############################ Agregar, Borrar y Consultar Ejercicio de la Biblioteca Publica (DEjercicioEnBiblioteca) #####################
       
    public static int agregarABibliotecaPublica(Ejercicio ejer) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Insert into DEjercicioEnBiblioteca(id_ruti) "
                    + "values (?)";
             ps = con.prepareStatement(q);
            ps.setInt(1, ejer.getId_ejer());
            
            estatus = ps.executeUpdate();
            
            System.out.println("Rutina agregada a la Biblioteca publica");
            
        }catch(Exception e){
            System.out.println("Error al agregar la rutina a la biblioteca publica");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    }
    
    public static int quitarABibliotecaPublica(Ejercicio ejer) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "delete from DRutinaEnBiblioteca where id_ruti = ? ";
             ps = con.prepareStatement(q);
            ps.setInt(1, ejer.getId_ejer());
            
            estatus = ps.executeUpdate();
            
            System.out.println("Rutina bajada de la Biblioteca publica");
            
        }catch(Exception e){
            System.out.println("Error al bajar la rutina en la biblioteca publica");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
        
        
        
    }
    
    public static boolean isInBibliotecaPublica(int id_ejer) throws SQLException{
        boolean inBibliotecaPublica = false;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from DEjercicioEnBiblioteca where id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            
             rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_ejer");
                inBibliotecaPublica = true;
            }
            
            System.out.println("Se consulto la rutina en la Biblioteca");
            
        }catch(Exception e){
            System.out.println("Error al consultar la rutina en la Biblioteca Publica");
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        
        return inBibliotecaPublica;
    }
    
}
