
package Controlador;

import Modelo.SoliAdmin_Crea;
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
public class DSoliAdmin_Crea {
    
    public static int addSoli(SoliAdmin_Crea soli)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into dsoliAdmin-Crea (id_perf1, id_perf2, motivo, id_ruti, id_ejer, isApproved)"
                    + "values (?,?,?,?,?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,soli.getId_perf1());
            ps.setInt(2, soli.getId_perf2());
            ps.setString(3, soli.getMotivo());
            ps.setInt(4, soli.getId_ruti());
            ps.setInt(5, soli.getId_ejer());
            ps.setBoolean(6, soli.isApproved());

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Solicitud Admin-Creador Exitoso: "+ soli.getId_perf1() + ", " + soli.getId_perf2());
            

            
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
    
    public static int updateSoli(int id_perf1, int id_perf2, SoliAdmin_Crea soli)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "Update dsoliAdmin-Crea set motivo = ?, id_ruti = ?, id_ejer = ?, isApproved = ?)"
                    + "values (?,?,?,?)";

            ps = con.prepareStatement(q);

            
            ps.setString(1, soli.getMotivo());
            ps.setInt(2, soli.getId_ruti());
            ps.setInt(3, soli.getId_ejer());
            ps.setBoolean(4, soli.isApproved());

            estatus = ps.executeUpdate();
            System.out.println("Solicitud Admin-Creador Actualizado con Exito: "+ soli.getId_perf1() + ", " + soli.getId_perf2());
            

            
        }catch(Exception e){
            System.out.println("Error al actualizar la  Solicitud Admin-Usuario");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }

    public static List<SoliAdmin_Crea> getSolisAdmin(int id_perf1) throws SQLException{
        List<SoliAdmin_Crea> solis = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from Dsoliadmin-usu where id_perf1 = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf1);
        rs = ps.executeQuery();
        while(rs.next()){
            SoliAdmin_Crea soli = new SoliAdmin_Crea();
            soli.setId_perf1(id_perf1);
            soli.setId_perf2(rs.getInt("id_perf2"));
            soli.setMotivo(rs.getString("motivo"));
            soli.setId_ruti(rs.getInt("id_ruti"));
            soli.setId_ejer(rs.getInt("id_ejer"));
            soli.setApproved(rs.getBoolean("isApproved"));
            
            solis.add(soli);
        }
        
        System.out.println("Se ha consultado las solicitudes del Admin");
        
        }catch(Exception e){
            System.out.println("Error al consultar las solicitudes del Admin");
            
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return solis;
        
        
        
    }
    
    public static List<SoliAdmin_Crea> getSolisCrea(int id_perf2) throws SQLException{
        List<SoliAdmin_Crea> solis = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from Dsoliadmin-usu where id_perf2 = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf2);
        rs = ps.executeQuery();
        while(rs.next()){
            SoliAdmin_Crea soli = new SoliAdmin_Crea();
            soli.setId_perf1(rs.getInt("id_perf1"));
            soli.setId_perf2(id_perf2);
            soli.setMotivo(rs.getString("motivo"));
            soli.setId_ruti(rs.getInt("id_ruti"));
            soli.setId_ejer(rs.getInt("id_ejer"));
            soli.setApproved(rs.getBoolean("isApproved"));
            
            solis.add(soli);
        }
        
        System.out.println("Se ha consultado las solicitudes del Creador");
        
        }catch(Exception e){
            System.out.println("Error al consultar las solicitudes del Creador");
            
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return solis;
        
        
        
    }
    
    
    
    
}
