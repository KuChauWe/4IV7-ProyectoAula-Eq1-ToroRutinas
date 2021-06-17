package Controlador;
import Modelo.*;
import java.io.IOException;
import java.sql.Connection;
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
public class MEjercicio {
    
    public static int addEjer(Ejercicio ejer) throws ServletException, ClassNotFoundException, SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            
            con = ConexionSQL.getConnection();

            String q = "insert into MEjercicio(id_perf, nomb_ejer, calPerd, isPublic) "
                    + "values (?,?,?,?))";

             ps = con.prepareStatement(q);

            ps.setInt(1, ejer.getId_perf());
            ps.setString(2, ejer.getNomb_ejer());
            ps.setFloat(3, ejer.getCalPer());
            ps.setBoolean(4, ejer.isPublica());
            
            estatus = ps.executeUpdate();
            
            
            if(estatus != 0)System.out.println("Ejercicio registrado");
            
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
    
    public static int updateEjer(int id_ejer ,Ejercicio ejer) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            
            String q = "Update MEjercicio set nomb_ejer = ?, calPerd = ?, isPublic = ?"
                    + "where id_ejer = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, ejer.getNomb_ejer());
            ps.setFloat(2, ejer.getCalPer());
            ps.setBoolean(3, ejer.isPublica());
            ps.setInt(4, id_ejer);
            
            estatus = ps.executeUpdate();
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
    
    public static Ejercicio getEjerById(int id_ejer) throws SQLException{
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
                ejer.setId_perf(rs.getInt("id_perf"));
                ejer.setCalPer(rs.getFloat("calPerd"));
                ejer.setPublica(rs.getBoolean("isPublic"));
                System.out.println("Se ha consultado el ejercicio con exito");
            }
            
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
    
    public static int getIdByEjer(Ejercicio ejer) throws SQLException{
        int id = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select id_ejer from MEjercicio where id_perf = ? and nomb_ejer = ? and calPerd = ? and isPublic = ?";
            ps = con.prepareStatement(q);
            
            ps.setInt(1, ejer.getId_perf());
            ps.setString(2, ejer.getNomb_ejer());
            ps.setFloat(3, ejer.getCalPer());
            ps.setBoolean(4, ejer.isPublica());
            
             rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id_ruti");
                System.out.println("Se ha encontrado el id de la rutina");
            }
            
            
            
            
            
        }catch(Exception e){
            System.out.println("Error al buscar el Id del ejercicio");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return id;
    }
    
    
    //############################## EImgEjer #####################################
    
    public static int addImgs(int id_ejer, List<String> ids_img) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            for(String id_img: ids_img){
                String q = "Insert into EImgEjer (id_ejer, id_img)"
                        + "values (?,?)";
                ps = con.prepareStatement(q);
                
                ps.setInt(1,id_ejer);
                ps.setString(2, id_img);
                estatus = ps.executeUpdate();
            }

            System.out.println("Se vincularon exitosamente las imagenes en EImgEjer");
            
            
        }catch(Exception e){
            System.out.println("Error al vincular las imagenes con el ejercicio en EImgEjer");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        
        return estatus;
    }
     
    public static int deleteImg(int id_ejer) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            con = ConexionSQL.getConnection();
            
            String q = "delete from EImgEjer where id_ejer = ?";
                
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            estatus = ps.executeUpdate();
            
            System.out.println("Se desvincularon exitosamente las imagenes en EImgEjer");
            
            
        }catch(Exception e){
            System.out.println("Error al desvincular las imagenes con el ejercicio en EImgEjer");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        
        return estatus;
     }
     
    public static List<String> getImgs(int id_ejer) throws SQLException{
        List<String> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        
        try{
            con = ConexionSQL.getConnection();
            String q = "Select id_img from EImgEjer where id_ejer = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ejer);
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(rs.getString("id_img"));
            }
            
            System.out.println("Se ha consultado correctamente las imagenes");
            
        }catch(Exception e){
            System.out.println("Error al consultar las imagenes del ejercicio");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
        return lista;
    }

     
    
    //########################## DEjerRuti ###############################
    
    private static int addEjer(int id_ruti, HashMap<Ejercicio, int[]> ejers) throws SQLException{
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        List<Integer> listaestatus = null;
        int estatus = 0;
        try{
            final Connection com = ConexionSQL.getConnection();
            ejers.forEach((ejer, turnoYTiempo) -> {
                String q = "Insert into DEjerRuti (id_ruti, id_ejer, turno, tiempo)"
                        + "values (?,?,?,?)";
                try {
                    final PreparedStatement pss = com.prepareStatement(q);
                    ps.setInt(1,id_ruti);
                    ps.setInt(2, ejer.getId_ejer());
                    ps.setInt(3, turnoYTiempo[0]);
                    ps.setInt(4, turnoYTiempo[1]);
                    listaestatus.add(ps.executeUpdate());
                    pss.close();
                } catch (Exception e) {
                    System.out.println("Error al vincular un ejercicio");
                    System.out.println(e.getMessage());
                }
                
            });
            
            if(listaestatus.contains(0) != true){
                estatus = 1;
                System.out.println("Se vincularon exitosamente los ejercicios");
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Error al vincular los ejercicios con la Rutina");
            System.out.println(e.getMessage());    
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    }
    
    public static int deleteEjer(int id_ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            
            String q = "delete from DEjerRuti where id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            System.out.println("Se borraron los ejercicios de la Rutina en DEjerRuti");
            estatus = ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println("Error al borrar los ejercicios con la Rutina en DEjerRuti");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    
        
    }
    
    public static HashMap<Ejercicio, int[]> getEjers(int id_ruti) throws SQLException{
        HashMap<Ejercicio, int[]> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from DEjerRuti where id_ruti = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Ejercicio ejer = MEjercicio.getEjerById(rs.getInt("id_ejer"));
                int[] turnoYTiempo = new int[2];
                turnoYTiempo[0] = rs.getInt("turno");
                turnoYTiempo[1] = rs.getInt("tiempo");
                
                lista.put(ejer, turnoYTiempo);
                
            }
            
            System.out.println("Se ha consultado correctamente los ejercicios de la rutina en DEjerRuti");
            
        }catch(Exception e){
            System.out.println("Error al consultar los ejercicios de la rutina en DEjerRuti");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
        return lista;
    }
    
    //########################## ECreaEjer ###############################
    
    private static int addCreaRuti(int id_perf, int id_ruti) throws SQLException{
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        int estatus = 0;
        try{
            con = ConexionSQL.getConnection();
            
            String q = "Insert into ECreaRuti (id_perf, id_ruti)"
                    + "values (?,?)";
            
            ps = con.prepareStatement(q);
            
            ps.setInt(1,id_perf);
            ps.setInt(2, id_ruti);
                
           estatus = ps.executeUpdate();
           if(estatus!=0)System.out.println("Se ha agregado la rutina a la Biblioteca Privada Creador correctamente");
            
            
            
            
        }catch(Exception e){
            System.out.println("Error al agregado la rutina a la Biblioteca Privada Creador");
            System.out.println(e.getMessage());    
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    }
    
    public static int deleteCreaRuti(int id_perf, int id_ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            
            String q = "delete from ECreaRuti where id_perf = ? and id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, id_perf);
            ps.setInt(2, id_ruti);
            estatus = ps.executeUpdate();
            System.out.println("Se borro la rutina en la Biblioteca Privada Creador");
        }catch(Exception e){
            System.out.println("Error al borrar la rutina en la Biblioteca Privada Creador");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    
        
    }
    
    public static List<Rutina> getRutisCrea(int id_perf) throws SQLException{
        List<Rutina> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from ECreaRuti where id_perf = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_perf);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id_ruti = rs.getInt("id_ruti");
                Rutina ruti = MRutina.getRutiById(id_ruti);
                ruti.setEjercicios(MEjercicio.getEjers(id_ruti));
    
                lista.add(ruti); 
            }
            
            System.out.println("Se ha consultado correctamente las rutinas del Creador");
            
        }catch(Exception e){
            System.out.println("Error al consultar las rutinas del Creador");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
        return lista;
    }
    
}
