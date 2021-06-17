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
public class DSoliAdmin_Usu {
    
    public static int addSoli(SoliAdmin_Usua soli)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "insert into dsoliAdmin-Usu (id_perf1, id_perf2, motivo, forAdmin, forCrea, isApproved)"
                    + "values (?,?,?,?,?,?)";

            ps = con.prepareStatement(q);

            ps.setInt(1,soli.getId_perf1());
            ps.setInt(2, soli.getId_perf2());
            ps.setString(3, soli.getMotivo());
            ps.setBoolean(4, soli.getForAdmin());
            ps.setBoolean(5, soli.getForCrea());
            ps.setBoolean(6, soli.isApproved());

            estatus = ps.executeUpdate();
            System.out.println("Registro de la Solicitud Admin-Usuario Exitoso: "+ soli.getId_perf1() + ", " + soli.getId_perf2());
            

            
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
    
    public static int updateSoli(int id_perf1, int id_perf2, SoliAdmin_Usua soli)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            con = ConexionSQL.getConnection();   

            String q = "Update dsoliAdmin-Usu set motivo = ?, forAdmin = ?, forCrea = ?, isApproved = ?)"
                    + "values (?,?,?,?)";

            ps = con.prepareStatement(q);

            
            ps.setString(1, soli.getMotivo());
            ps.setBoolean(2, soli.getForAdmin());
            ps.setBoolean(3, soli.getForCrea());
            ps.setBoolean(4, soli.isApproved());

            estatus = ps.executeUpdate();
            System.out.println("Solicitud Admin-Usuario Actualizado con Exito: "+ soli.getId_perf1() + ", " + soli.getId_perf2());
            

            
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

    public static List<SoliAdmin_Usua> getSolisAdmin(int id_perf1) throws SQLException{
        List<SoliAdmin_Usua> solis = null;
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
            SoliAdmin_Usua soli = new SoliAdmin_Usua();
            soli.setId_perf1(id_perf1);
            soli.setId_perf2(rs.getInt("id_perf2"));
            soli.setMotivo(rs.getString("motivo"));
            soli.setForAdmin(rs.getBoolean("forAdmin"));
            soli.setForCrea(rs.getBoolean("forCrea"));
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
    
    public static List<SoliAdmin_Usua> getSolisUsu(int id_perf2) throws SQLException{
        List<SoliAdmin_Usua> solis = null;
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
            SoliAdmin_Usua soli = new SoliAdmin_Usua();
            soli.setId_perf1(rs.getInt("id_perf1"));
            soli.setId_perf2(id_perf2);
            soli.setMotivo(rs.getString("motivo"));
            soli.setForAdmin(rs.getBoolean("forAdmin"));
            soli.setForCrea(rs.getBoolean("forCrea"));
            soli.setApproved(rs.getBoolean("isApproved"));
            
            solis.add(soli);
        }
        
        System.out.println("Se ha consultado las solicitudes del Usuario");
        
        }catch(Exception e){
            System.out.println("Error al consultar las solicitudes del Usuario");
            
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return solis;
        
        
        
    }
    
}
