package Controlador;

import Modelo.SolicitudAdminCreador;
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
public class AccionesSolicitudAdminCreador {
    
    public static int RegiSolicitud(SolicitudAdminCreador soli)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            
            if(soli.getRuti() == null){
                con = ConexionSQL.getConnection();   

            String q = "insert into esolicitudadmincreador (id_perf1, id_perf2, motivo_soli, id_ejer, aceptado)"
                    + "values (?, ?, ?, ?, ?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,soli.getId_perf1());
            ps.setInt(2, soli.getId_perf2());
            ps.setString(3, soli.getMotivo());
            ps.setInt(4, soli.getEjer().getId_ejer());
            ps.setBoolean(5, soli.isAceptado());

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Solicitud Admin-Creador Exitoso: "+ soli.getId_perf1() + ", " + soli.getId_perf2());
            
            }if(soli.getEjer() == null){
            
                con = ConexionSQL.getConnection();   

                String q = "insert into esolicitudadmincreador (id_perf1, id_perf2, motivo_soli, id_ruti, aceptado)" 
                    + "values (?, ?, ?, ?, ?,?)";

                ps = con.prepareStatement(q);

                ps.setInt(1,soli.getId_perf1());
                ps.setInt(2, soli.getId_perf2());
                ps.setString(3, soli.getMotivo());
                ps.setInt(4, soli.getRuti().getId_ruti());
                ps.setBoolean(5, soli.isAceptado());

                estatus = ps.executeUpdate();
                System.out.println("Registro de la Solicitud Admin-Creador Exitoso: "+ soli.getId_perf1() + ", " + soli.getId_perf2());

            }else{
                System.out.println("Debes de asignar un ejercicio o rutina para que se haga publica");
            }
            
        
            

            
        }catch(Exception e){
            System.out.println("Error al registrar la  Solicitud Admin-Creador");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
    
    public static int getIdSolicitud(int id_perf1, int id_perf2) throws SQLException{
       int id_soliAC = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select id_soliAC from esolicitudadmincreador where id_perf1 = ? and id_perf2 = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf1);
        ps.setInt(2, id_perf2);
        
        rs = ps.executeQuery();
        while(rs.next()){
            id_soliAC = rs.getInt("id_soliAC");
            
        }
        
        System.out.println("Se ha consultado el Id de la  Solicitud Admin-creador");
        
        }catch(Exception e){
            System.out.println("Error al consultar al Id de la Solicitud Admin-Creador");
            
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return id_soliAC;
        
        
        
    }
    
    public static SolicitudAdminCreador getSolicitud(int id_soliAC) throws SQLException{
        SolicitudAdminCreador soli = new SolicitudAdminCreador();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from esolicitudadmincreador where id_soliAC = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_soliAC);
        
        rs = ps.executeQuery();
        
        int id_usu = 0;
        while(rs.next()){
           soli.setId_perf1(rs.getInt("id_perf1"));
           soli.setId_perf2(rs.getInt("id_perf2"));
           soli.setMotivo(rs.getString("motivo_soli"));
           soli.setAceptado(rs.getBoolean("aceptado"));
           
           
           try{
               soli.setRuti(AccionesRutina.buscarRutinaById(rs.getInt("id_ruti")));
           }catch(Exception e){
               System.out.println("No hay rutina ligada a esta solicitud");
           }
           try{
               soli.setEjer(AccionesEjercicio.buscarEjercicioById(rs.getInt("id_ruti")));
           }catch(Exception e){
               System.out.println("No hay ejercicio ligada a esta solicitud");
           }
           
            
            
        }
        
        System.out.println("Se ha consultad Solicitud Admin-Creador, con el id: " + id_soliAC);
        
        }catch(Exception e){
            System.out.println("Error al consultar la Solicitud Admin-Creador");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return soli;
        
        
        
    }
    
    public static List<SolicitudAdminCreador> getAllSolicitudes(int id_perf1) throws SQLException{
         List<SolicitudAdminCreador> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from esolicitudadmincreador where id_perf1 = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf1);
        
        rs = ps.executeQuery();
        
        
        while(rs.next()){
            int id_soliAC = rs.getInt("id_soliAC");
            
            SolicitudAdminCreador soli = AccionesSolicitudAdminCreador.getSolicitud(id_soliAC);
            lista.add(soli);
            
            
        }
        
        System.out.println("Se ha consultado las Solicitudes Admin-Creador, con el id_perf1: " + id_perf1);
        
        }catch(Exception e){
            System.out.println("Error al consultar  las Solicitudes Admin-Creador");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return lista;
        
        
        
    }
     
    public static int borrarSolicitud(int id_soliAC)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "delete from esolicitudadmincreador where id_soliAC = ?"
                    + "values (?,?)";

            ps = con.prepareStatement(q);
            ps.setInt(1,id_soliAC);

            estatus = ps.executeUpdate();
            System.out.println("Se elimino la Solicitud Admin-Creador, con el id: " + id_soliAC);
            

            
        }catch(Exception e){
            System.out.println("Error al eliminar la Solicitud Admin-Creador ");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
}