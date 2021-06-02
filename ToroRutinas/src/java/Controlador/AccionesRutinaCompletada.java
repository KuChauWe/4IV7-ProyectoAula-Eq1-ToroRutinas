package Controlador;

import Modelo.Rutina;
import Modelo.RutinaCompletada;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author sofo9
 */
public class AccionesRutinaCompletada {
    public static int RegiRutinaCompletada(int id_usu, int id_ruti, Date fech_rutiCom)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into rutinascompletadas (id_usu, id_ruti, fech_rutiCom)"
                    + "values (?, ?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,id_usu);
            ps.setInt(2, id_ruti);
            ps.setDate(3, fech_rutiCom);

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Rutina Completada del Usuario Exitoso: "+ id_usu);
            

            
        }catch(Exception e){
            System.out.println("Error al registrar la Rutina Completada del Usuario ");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
   
    public static int getIdRutinaCompletada(int id_usu, int id_ruti, Date fech_rutiCom) throws SQLException{
       int id_RutiCom = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select id_RutiCom from rutinascompletadas where id_usu = ? and id_ruti = ? and fech_rutiCom = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_usu);
        ps.setInt(2, id_ruti);
        ps.setDate(3, fech_rutiCom);
        
        rs = ps.executeQuery();
        while(rs.next()){
            id_RutiCom = rs.getInt("id_RutiCom");
            
        }
        
        System.out.println("Se ha consultado al Id de la Rutina completada por el usuario, usuario con el id: " + id_usu);
        
        }catch(Exception e){
            System.out.println("Error al consultar al Id de la Rutina completada por el usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_RutiCom;
        
        
        
    }
    
    public static RutinaCompletada getRutinaCompletada(int id_RutiCom) throws SQLException{
       RutinaCompletada rutiCom = new RutinaCompletada();
       HashMap<Rutina, Date> rutinaFecha = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from rutinascompletadas where id_RutiCom = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_RutiCom);
        
        rs = ps.executeQuery();
        
        int id_usu = 0;
        while(rs.next()){
            id_usu = rs.getInt("id_usu");
            Rutina ruti = AccionesRutina.buscarRutinaById(rs.getInt("id_ruti"));
            Date fech_rutiCom = rs.getDate("fech_rutiCom");
            
            rutinaFecha.put(ruti, fech_rutiCom);
            
            
        }
        rutiCom.setId_usu(id_usu);
        rutiCom.setRutinaYFecha(rutinaFecha);
        rutiCom.setId_RutiCom(id_RutiCom);
        
        System.out.println("Se ha consultado Rutina Completada por el usuario, con el id: " + id_RutiCom);
        
        }catch(Exception e){
            System.out.println("Error al consultar la Rutina Completada por el usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return rutiCom;
        
        
        
    }
    
    public static List<RutinaCompletada> getAllRutinasCompletadas(int id_usu) throws SQLException{
         List<RutinaCompletada> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from rutinascompletadas where id_usu = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_usu);
        
        rs = ps.executeQuery();
        
        
        List<Integer> ids_rutoCom = null;
        while(rs.next()){
            int id_rutiCom = rs.getInt("id_rutiCom");
            
            RutinaCompletada rutiCom = AccionesRutinaCompletada.getRutinaCompletada(id_rutiCom);
            lista.add(rutiCom);
            
            
        }
        
        System.out.println("Se ha consultado las Rutinas Completadas por el usuario, con el id_usu: " + id_usu);
        
        }catch(Exception e){
            System.out.println("Error al consultar  las Rutinas Completadas por el usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
        
        
        
    }
    
    
    public static int borrarRutinaCompletada(int id_RutiCom)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "delete from rutinascompletadas where id_RutiCom = ?"
                    + "values (?,?)";

            ps = con.prepareStatement(q);
            ps.setInt(1,id_RutiCom);

            estatus = ps.executeUpdate();
            System.out.println("Se elimino la Rutina Completada del Usuario Exitosamente, id_RutiCom: "+ id_RutiCom);
            

            
        }catch(Exception e){
            System.out.println("Error al eliminar la Rutina Completada del Usuario ");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
}