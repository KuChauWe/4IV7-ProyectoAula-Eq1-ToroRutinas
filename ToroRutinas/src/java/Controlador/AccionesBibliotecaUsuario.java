package Controlador;

import Modelo.BibliotecaUsuario;
import Modelo.Rutina;
import Modelo.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

/**
 *
 * @author sofo9
 */
public class AccionesBibliotecaUsuario {
    
    //Sobre musuariobiblioteca
    public static int RegiBibliotecaUsuario(int id_usu)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into musuariobiblioteca (id_usu)"
                    + "values (?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,id_usu);

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Biblioteca Privada del Usuario Exitoso: "+ id_usu);
            

            
        }catch(Exception e){
            System.out.println("Error al registrar la Biblioteca Privada del Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
   
    public static int getIdBibliotecaUsuario (int id_usuario) throws SQLException{
       int id_bibliPrivUsu = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select id_bibliPrivUsu from musuariobiblioteca where id_usu = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_usuario);
        
        rs = ps.executeQuery();
        while(rs.next()){
            id_bibliPrivUsu = rs.getInt("id_bibliPrivUsu");
            
        }
        
        System.out.println("Se ha consultado al usuario con el id: " + id_usuario);
        
        }catch(Exception e){
            System.out.println("Error al consultar al usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_bibliPrivUsu;
        
        
        
    }
    
    
     public static int borrarBibliotecaUsuario(int id_usu, int id_bibliPrivUsu)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "delete from musuariobiblioteca where id_usu = ?, id_bibliPrivUsu = ?"
                    + "values (?,?)";

            ps = con.prepareStatement(q);
            ps.setInt(1,id_usu);
            ps.setInt(2, id_bibliPrivUsu);

            estatus = ps.executeUpdate();
            System.out.println("Se elimino  la Biblioteca Privada del Usuario Exitosamente: "+ id_usu);
            

            
        }catch(Exception e){
            System.out.println("Error al eliminar la Biblioteca Privada del Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
    
    
    //Sobre eusuariobiblioteca
    public static int RegiRutinaBibliotecaUsuario(int id_bibliPrivUsu, int id_ruti, String diasemana)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into eusuariobiblioteca (id_bibliPrivUsu, id_ruti, diasemana)"
                    + "values (?,?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,id_bibliPrivUsu);
            ps.setInt(2, id_ruti);
            ps.setString(3, diasemana);

            estatus = ps.executeUpdate();
            System.out.println("Registro de la rutina en Biblioteca Privada del Usuario con: "+ id_bibliPrivUsu);
            

            
        }catch(Exception e){
            System.out.println("Error al registrar la rutina en Biblioteca Privada del Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }

    public static int BorrarRutinaBibliotecaUsuario(int id_usuario, int id_ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int id_bibliPrivUsu = AccionesBibliotecaUsuario.getIdBibliotecaUsuario(id_usuario);
            
            con = ConexionSQL.getConnection();   

           String q = "DELETE FROM eusuariobiblioteca WHERE id_bibliPrivUsu = ? and id_ruti = ?";

            ps = con.prepareStatement(q);

           ps.setInt(1, id_bibliPrivUsu);
           ps.setInt(2, id_ruti);

           estatus = ps.executeUpdate();
           System.out.println("Rutina con id: "+id_ruti + " Eliminado de la Biblioteca del Usuario");
           }catch(Exception e){
               System.out.println("Error al Eliminar la Rutina en la Biblioteca del Usuario");
               System.out.println(e.getMessage());

        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
        
        
    }

    
    
    //Sobre las tablas de m y e usuariobiblioteca
    public static BibliotecaUsuario getBibliotecaUsuario (int id_usuario) throws SQLException{
       BibliotecaUsuario bibliPrivUsua = new BibliotecaUsuario();
        HashMap<Rutina, String> rutinas = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int id_bibliPrivUsu;
            id_bibliPrivUsu = AccionesBibliotecaUsuario.getIdBibliotecaUsuario(id_usuario);
        
            con = ConexionSQL.getConnection();   

           String q = "Select * from eusuariobiblioteca where id_bibliPrivUsu = ?";
           ps = con.prepareStatement(q);
           ps.setInt(1, id_bibliPrivUsu);

           rs = ps.executeQuery();
           while(rs.next()){
               Rutina ruti = AccionesRutina.buscarRutinaById(rs.getInt("id_ruti"));
               String diasemana = rs.getString("diasemana");
               rutinas.put(ruti, diasemana);
           }
           
           bibliPrivUsua.setRutinas(rutinas);
           bibliPrivUsua.setUsu(AccionesUsuario.buscarUsuarioById(id_usuario));
        
            System.out.println("Se ha consultado la Biblioteca Privada del Usuario con Id_usuario: " + id_usuario);
        
        }catch(Exception e){
            System.out.println("Error al consultar la Biblioteca Privada del Usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return bibliPrivUsua;
        
        
        
    }
    
    public static int ActualizarBibliotecaUsuario(int id_usuario,BibliotecaUsuario bibliUsu) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            
            //Elimino las rutinas relacionadas con la bibliotecaUsuario
            BibliotecaUsuario biblioAntigua = AccionesBibliotecaUsuario.getBibliotecaUsuario(id_usuario);
            
            biblioAntigua.getRutinas().forEach((rutina, diasemana) -> {
                try {
                    AccionesBibliotecaUsuario.BorrarRutinaBibliotecaUsuario(id_usuario, rutina.getId_ruti());
                } catch (Exception ex) {
                    System.out.println("Error al borrar las rutinas antiguas en la Biblioteca del Usuario");
                    System.out.println(ex.getMessage());
                }
            });
            
            
            //Reemplazo las rutinas con las nuevas
            bibliUsu.getRutinas().forEach((rutinas, diasemana) -> {
                try {
                    AccionesBibliotecaUsuario.RegiRutinaBibliotecaUsuario(id_usuario, rutinas.getId_ruti(), diasemana);
                } catch (Exception ex) {
                    System.out.println("Error al reemplazar las rutinas en la Biblioteca del Usuario");
                    System.out.println(ex.getMessage());
                } 
            });
            
            
        
            
           System.out.println("Biblioteca del Usuario con id_usu: "+id_usuario + " Actualizado");
        }catch(Exception e){
            System.out.println("Error al Actualizar la Biblioteca del Usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
    }   
}