package Controlador;

import Modelo.Rutina;
import Modelo.Historial;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author sofo9
 */
public class DHistorial {
    public static int addHist(int id_perf, HashMap<Integer, ArrayList> rutiDateIsCom)
    throws ServletException, IOException, SQLException, ClassNotFoundException{
        List<Integer> listaestatus = null;
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
        
            final Connection conn = ConexionSQL.getConnection();   

            rutiDateIsCom.forEach((id_ruti, iniCom) -> {
                 String q = "Insert into dHistorial (id_perf, id_ruti, dateInicio, isComplete)"
                    + "values (?,?,?,?)";
                try {
                    final PreparedStatement pss = conn.prepareStatement(q);
                    ps.setInt(1,id_perf);
                    ps.setInt(2, id_ruti);
                    ps.setDate(3, (Date) iniCom.get(0));
                    ps.setBoolean(4, (boolean) iniCom.get(1));
                    
                    listaestatus.add(ps.executeUpdate());
                    ps.close();
                } catch (SQLException ex) {
                    System.out.println("Errro al inserta en el forEach");
                }
            }); 
            if(listaestatus.contains(0) != true){
                   estatus = 1;
                   System.out.println("Registro exitos en el Historial");
                   
               }
            
            
            
        }catch(Exception e){
            System.out.println("Error al registrar el Historial del Perfil ");
            System.out.println(e.getMessage());
        }finally{
            con.close();
            ps.close();
            rs.close();
        }

        return estatus;
            
    }
   
    public static Historial getHist(int id_perf) throws SQLException{
        Historial rutiCom = null;
        HashMap<Integer, ArrayList> rutiDateIsCom = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
        
         con = ConexionSQL.getConnection();   
        
        String q = "Select * from DHistorial where id_perf = ?";
        ps = con.prepareStatement(q);
        ps.setInt(1, id_perf);
        
        rs = ps.executeQuery();
        
        while(rs.next()){
            int id_ruti = rs.getInt("id_ruti");
            
            ArrayList dateCom = new ArrayList();
            dateCom.add(rs.getDate("dateInicio"));
            dateCom.add(rs.getBoolean("isComplete"));
            
            rutiDateIsCom.put(id_ruti, dateCom);
            
            
        }
        if( rutiDateIsCom != null){
            rutiCom = new Historial();
            rutiCom.setId_perf(id_perf);
            rutiCom.setrRutiDateIsCom(rutiDateIsCom);
        }
        
        System.out.println("Se ha consultado el historial del perfil con el id: " + id_perf);
        
        }catch(Exception e){
            System.out.println("Error al consultar el historial del perfil");
            System.out.println(e.getMessage());
            
        }finally{
            con.close();
            ps.close();
            rs.close();
        }
        return rutiCom; 
    }
    
    

     


}
