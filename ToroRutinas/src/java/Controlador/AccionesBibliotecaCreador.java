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
public class AccionesBibliotecaCreador {
    
    //Sobre musuariobiblioteca
    public static int RegiBibliotecaPerfil(int id_perf)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into MPerfilBiblioteca (id_perf)"
                    + "values (?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,id_perf);

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Biblioteca Privada del Creador Exitoso: "+ id_perf);
            

            
        }catch(Exception e){
            System.out.println("Error al registrar la Biblioteca Privada del creador");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
   
    public static int getIdBibliotecaPerfil(int id_perf) throws SQLException{
       int id_bibliPrivPerf = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select id_bibliPrivPerf from mperfilbiblioteca where id_perf = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf);
        
        rs = ps.executeQuery();
        while(rs.next()){
            id_bibliPrivPerf = rs.getInt("id_bibliPrivPerf");
            
        }
        
        System.out.println("Se ha consultado al Id de la Biblioteca del Creador, perfil con el id: " + id_perf);
        
        }catch(Exception e){
            System.out.println("Error al consultar al Id de la Biblioteca del Creador");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_bibliPrivPerf;
        
        
        
    }
    
    
     public static int borrarBibliotecaPerfil(int id_perf, int id_bibliPrivPerf)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "delete from mperfilbiblioteca where id_perf = ?, id_bibliPrivPerf = ?"
                    + "values (?,?)";

            ps = con.prepareStatement(q);
            ps.setInt(1,id_perf);
            ps.setInt(2, id_bibliPrivPerf);

            estatus = ps.executeUpdate();
            System.out.println("Se elimino la Biblioteca Privada del Perfil Exitosamente, id_perfil: "+ id_perf);
            

            
        }catch(Exception e){
            System.out.println("Error al eliminar la Biblioteca Privada del Perfil");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
    
    
    //Sobre eperfilbiblioteca
    public static int RegiRutinaBibliotecaPerfil(int id_bibliPrivPerf, int id_ruti)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into eperfilbiblioteca (id_bibliPrivPerf, id_ruti)"
                    + "values (?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,id_bibliPrivPerf);
            ps.setInt(2, id_ruti);

            estatus = ps.executeUpdate();
            System.out.println("Registro de la rutina en Biblioteca Privada del Perfil con: "+ id_bibliPrivPerf);
            

            
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

    public static int BorrarRutinaBibliotecaPerfil(int id_perf, int id_ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int id_bibliPrivPerf = AccionesBibliotecaCreador.getIdBibliotecaPerfil(id_perf);
            
            con = ConexionSQL.getConnection();   

           String q = "DELETE FROM eperfilbiblioteca WHERE id_bibliPrivPerf = ? and id_ruti = ?";

            ps = con.prepareStatement(q);

           ps.setInt(1, id_bibliPrivPerf);
           ps.setInt(2, id_ruti);

           estatus = ps.executeUpdate();
           System.out.println("Rutina con id: "+id_ruti + " Eliminado de la Biblioteca del Creador");
           }catch(Exception e){
               System.out.println("Error al Eliminar la Rutina en la Biblioteca del Creador");
               System.out.println(e.getMessage());

        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
        
        
    }

    //Sobre eperfil2biblioteca
    public static int RegiEjercicioBibliotecaPerfil(int id_bibliPrivPerf, int id_ejer)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into eperfil2biblioteca (id_bibliPrivPerf, id_ejer)"
                    + "values (?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,id_bibliPrivPerf);
            ps.setInt(2, id_ejer);

            estatus = ps.executeUpdate();
            System.out.println("Registro del ejercicio en Biblioteca Privada del Perfil con: "+ id_bibliPrivPerf);
            

            
        }catch(Exception e){
            System.out.println("Error al registrar el ejercicio en Biblioteca Privada del Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }

    public static int BorrarEjercicioBibliotecaPerfil(int id_bibliPrivPerf, int id_ejer) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{            
            con = ConexionSQL.getConnection();   

           String q = "DELETE FROM eperfil2biblioteca WHERE id_bibliPrivPerf = ? and id_ruti = ?";

            ps = con.prepareStatement(q);

           ps.setInt(1, id_bibliPrivPerf);
           ps.setInt(2, id_ejer);

           estatus = ps.executeUpdate();
           System.out.println("Ejercicio con id: "+id_ejer + " Eliminado de la Biblioteca del Creador");
           }catch(Exception e){
               System.out.println("Error al Eliminar el ejercicio en la Biblioteca del Creador");
               System.out.println(e.getMessage());

        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
        
        
        
    }

    
    
    
    
    //Sobre las 3 tablas Bv
    public static BibliotecaCreador getBibliotecaPerfil (int id_perf) throws SQLException{
       BibliotecaCreador bibliPrivPerf = new BibliotecaCreador();
        List<Rutina> rutinas = null;
        List<Ejercicio> ejercicios = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            int id_bibliPrivPerf;
            id_bibliPrivPerf = AccionesBibliotecaCreador.getIdBibliotecaPerfil(id_perf);
            
            
            //Conexion a eperfilbiblioteca
            con = ConexionSQL.getConnection();   

           String q = "Select * from eperfilbiblioteca where id_bibliPrivPerf = ?";
           ps = con.prepareStatement(q);
           ps.setInt(1, id_bibliPrivPerf);

           rs = ps.executeQuery();
           while(rs.next()){
               Rutina ruti = AccionesRutina.buscarRutinaById(rs.getInt("id_ruti"));
               String diasemana = rs.getString("diasemana");
               rutinas.add(ruti);
           }
           
           bibliPrivPerf.setRutinas(rutinas);
           bibliPrivPerf.setPerf(AccionesPerfil.buscarPerfilById(id_perf));
        
           
           rs.close();
           ps.close();
           
           //Conexion a eperfil2biblioteca
            q = "Select * from eperfil2biblioteca where id_bibliPrivPerf = ?";
           ps = con.prepareStatement(q);
           ps.setInt(1, id_bibliPrivPerf);

           rs = ps.executeQuery();
           while(rs.next()){
               Ejercicio ejer = AccionesEjercicio.buscarEjercicioById(rs.getInt("id_ejer"));
               ejercicios.add(ejer);
           }
           
           bibliPrivPerf.setEjercicios(ejercicios);
           
           
            System.out.println("Se ha consultado la Biblioteca Privada del Creador con Id_perfil: " + id_perf);
        
        }catch(Exception e){
            System.out.println("Error al consultar las Rutinas de Biblioteca Privada del Perfil");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return bibliPrivPerf;
        
        
        
    }
    
    public static int ActualizarBibliotecaPerfil(int id_perf,BibliotecaCreador bibliPerf) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            
            
            //Elimino las rutinas relacionadas con la bibliotecaPerfil
            BibliotecaCreador biblioAntigua = AccionesBibliotecaCreador.getBibliotecaPerfil(id_perf);
            
            for(Rutina ruti:biblioAntigua.getRutinas()){
                AccionesBibliotecaCreador.BorrarRutinaBibliotecaPerfil(id_perf, ruti.getId_ruti());
            }
            
            
            //Reemplazo las rutinas con las nuevas
            for(Rutina ruti:bibliPerf.getRutinas()){
                AccionesBibliotecaCreador.RegiRutinaBibliotecaPerfil(id_perf, ruti.getId_ruti());
            }
            
            
            System.out.println("Se actualizaron las rutinas en la biblioteca del creador");
            try{
                
            
                //Elimino los ejercicios relacionadas con la bibliotecaPerfil

                for(Ejercicio ejer:biblioAntigua.getEjercicios()){
                    AccionesBibliotecaCreador.BorrarEjercicioBibliotecaPerfil(id_perf, ejer.getId_ejer());
                }


                //Reemplazo los ejercicios con los nuevas
                for(Ejercicio ejer:bibliPerf.getEjercicios()){
                    AccionesBibliotecaCreador.RegiRutinaBibliotecaPerfil(id_perf, ejer.getId_ejer());
                }
                
                System.out.println("Se actualizaron correctamente los ejercicios y rutinas de la biblioteca del creador");
            
            }catch(Exception e){
                System.out.println("Error al Actualizar los ejercicios de la Biblioteca del Creador");
                System.out.println(e.getMessage());
            }
            
           System.out.println("Biblioteca del Creador con id_perf: "+id_perf + " Actualizado");
        }catch(Exception e){
            System.out.println("Error al Actualizar la Biblioteca del Creador");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        
        
        return estatus;
    } 
}