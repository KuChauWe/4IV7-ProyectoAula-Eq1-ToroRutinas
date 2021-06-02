package Controlador;

import Modelo.*;
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
public class AccionesSolicitudAdminUsuario {
    
    public static int RegiSolicitud(SolicitudAdminUsuario soli)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into esolicitudadminusuario (id_usu, id_perf, motivo_soli, paraAdmin, paraCrea, aceptado)"
                    + "values (?, ?, ?, ?, ?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,soli.getId_usu());
            ps.setInt(2, soli.getId_perf());
            ps.setString(3, soli.getMotivo());
            ps.setBoolean(4, soli.isParaAdmin());
            ps.setBoolean(5, soli.isParaCread());
            ps.setBoolean(6, soli.isAceptado());

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Solicitud Admin-Usuario Exitoso: "+ soli.getId_perf() + ", " + soli.getId_usu());
            

            
        }catch(Exception e){
            System.out.println("Error al registrar la  Solicitud Admin-Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
    
    public static int getIdSolicitud(int id_usu, int id_perf) throws SQLException{
       int id_soliAU = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select id_soliAU from esolicitudadminusuario where id_usu = ? and id_perf = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_usu);
        ps.setInt(2, id_perf);
        
        rs = ps.executeQuery();
        while(rs.next()){
            id_soliAU = rs.getInt("id_soliAU");
            
        }
        
        System.out.println("Se ha consultado el Id de la  Solicitud Admin-Usuario");
        
        }catch(Exception e){
            System.out.println("Error al consultar al Id de la Solicitud Admin-Usuario");
            
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_soliAU;
        
        
        
    }
    
    public static SolicitudAdminUsuario getSolicitud(int id_soliAU) throws SQLException{
        SolicitudAdminUsuario soli = new SolicitudAdminUsuario();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from esolicitudadminusuario where id_soliAU = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_soliAU);
        
        rs = ps.executeQuery();
        
        int id_usu = 0;
        while(rs.next()){
           soli.setId_perf(rs.getInt("id_usu"));
           soli.setId_perf(rs.getInt("id_perf"));
           soli.setMotivo(rs.getString("motivo_soli"));
           soli.setParaAdmin(rs.getBoolean("paraAdmin"));
           soli.setParaCread(rs.getBoolean("paraCrea"));
           soli.setAceptado(rs.getBoolean("aceptado"));
            
            
        }
        
        System.out.println("Se ha consultad Solicitud Admin-Usuario, con el id: " + id_soliAU);
        
        }catch(Exception e){
            System.out.println("Error al consultar laSolicitud Admin-Usuario");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return soli;
        
        
        
    }
    
    public static List<SolicitudAdminUsuario> getAllSolicitudes(int id_perf) throws SQLException{
         List<SolicitudAdminUsuario> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from esolicitudadminusuario where id_perf = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf);
        
        rs = ps.executeQuery();
        
        
        while(rs.next()){
            int id_soliAU = rs.getInt("id_soliAU");
            
            SolicitudAdminUsuario soli = AccionesSolicitudAdminUsuario.getSolicitud(id_soliAU);
            lista.add(soli);
            
            
        }
        
        System.out.println("Se ha consultado las Solicitudes Admin-Usua, con el id_perf: " + id_perf);
        
        }catch(Exception e){
            System.out.println("Error al consultar  las Solicitudes Admin-Usua");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
        
        
        
    }
     
    public static int borrarSolicitud(int id_soliAU)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "delete from esolicitudadminusuario where id_soliAU = ?"
                    + "values (?,?)";

            ps = con.prepareStatement(q);
            ps.setInt(1,id_soliAU);

            estatus = ps.executeUpdate();
            System.out.println("Se elimino la Solicitud Admin-Usua, con el id: " + id_soliAU);
            

            
        }catch(Exception e){
            System.out.println("Error al eliminar la Solicitud Admin-Usua ");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
}