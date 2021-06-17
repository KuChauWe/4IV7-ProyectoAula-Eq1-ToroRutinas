package Controlador;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sofo9
 */
public class MRutina {
    
    public static int addRutina(Rutina ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        
        try{
             con = ConexionSQL.getConnection();
            String q = "Insert into MRutina (id_perf, nomb_ruti, durDes_ruti, rep_ruti)"
                    + "values (?,?,?,?)";
            
            ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_perf());
            ps.setString(2, ruti.getNomb_ruti());
            ps.setInt(3, ruti.getDurDes_ruti());
            ps.setInt(4, ruti.getRep_ruti());
            estatus = ps.executeUpdate();
            if(estatus != 0){
                System.out.println("Se agrego la rutina en MRutina");
            }
            
            
        }catch(Exception e){
            System.out.println("Error al registrar la Rutina en MRutina");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return estatus;
    }
    
    public static int deleteRutina(int id_ruti) throws SQLException{ 
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "delete from MRutina where id_ruti = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id_ruti);
            
            estatus = ps.executeUpdate();
            if(estatus != 0)System.out.println("Se ha borrado la rutina en MRutina");
        
        }catch(Exception ed){
            System.out.println("Error al borrar la Rutina en MRutina");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return estatus;
     
    }
    
    public static int updateRuti(Rutina ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            con = ConexionSQL.getConnection();
            String q = "Update MRutina set nomb_ruti = ?, durDes_ruti = ?, rep_ruti = ?, isPublic = ? where id_ruti = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, ruti.getNomb_ruti());
            ps.setInt(2, ruti.getDurDes_ruti());
            ps.setInt(3, ruti.getRep_ruti());
            ps.setInt(4, ruti.getId_ruti());
            
            estatus = ps.executeUpdate();
            
            if(estatus != 0)System.out.println("Se ha actualizado correctamente la Rutina en MRutina");
            
            
        }catch(Exception e){
            System.out.println("Error al actualizar la Rutina en MRutina");
            System.out.println(e.getMessage());
            
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        
        
        return estatus;
    }
    
     
    //Consulta
    
    //Sin ejercicios, también usar getEjer(int id_ruti)
    public static Rutina getRutiById(int id_ruti) throws SQLException{
        Rutina ruti = new Rutina();
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from MRutina where id_ruti = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            
            rs = ps.executeQuery();
            while(rs.next()){
                ruti.setId_ruti(rs.getInt("id_ruti"));
                ruti.setId_perf(rs.getInt("id_perf"));
                ruti.setNomb_ruti(rs.getString("nomb_ruti"));
                ruti.setDurDes_ruti(rs.getInt("durDes_ruti"));
                ruti.setRep_ruti(rs.getInt("rep_ruti"));
                ruti.setPublica(rs.getBoolean("isPublic"));
                System.out.println("Se ha encontrado la rutina");
            }
        }catch(Exception e){
            System.out.println("Error al buscar la rutina");
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return ruti;
    }
    
    public static int getIdByRuti(Rutina ruti) throws SQLException{
        int id = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from MRutina where nom_ruti = ?, durDes_ruti = ?, rep_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setString(1, ruti.getNomb_ruti());
            ps.setInt(2, ruti.getDurDes_ruti());
            ps.setInt(3, ruti.getRep_ruti());
            
             rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id_ruti");
            }
            
            System.out.println("Rutina encontrada con la Id");
            
            
            
        }catch(Exception e){
            System.out.println("Error al buscar la rutina con Id");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return id;
    }
    
    public static List<Rutina> getRutisPublic() throws SQLException{
        List<Rutina> rutis = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from MRutina where isPublic = true";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                int id_ruti = rs.getInt("id_ruti");
                Rutina ruti = MRutina.getRutiById(id_ruti);
                rutis.add(ruti);
            }
            System.out.println("Se han encontrado las rutinas que son publica");
        }catch(Exception e){
            System.out.println("Error al buscar las rutinas publicas");
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return rutis;
    }
    
    
     //########################## ERutiClas ###############################
    
    public static List<Rutina> getRutisPublicByClas(int id_clas) throws SQLException{
        //Creo una lista de todas las ids de rutinas publicas
        List<Rutina> rutisPublics = MRutina.getRutisPublic();
        List<Integer> ids_rutis = null;
        
        //En el caso que no haya rutinas publicas
        if(rutisPublics == null){
            System.out.println("No hay rutinas publicas");
            return null;
        }
        
        for(Rutina ruti: rutisPublics){
            ids_rutis.add(ruti.getId_ruti());
        }
        
        //Creo la lista donde agregar las rutinas que cumplen con la clasificacion
        List<Rutina> rutisClas = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from ERutiClas where id_clas = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1,id_clas);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id_ruti = rs.getInt("id_ruti");
                Rutina ruti = MRutina.getRutiById(id_ruti);
                //Si la ids_rutis contiene a id_ruti significa que la rutina es publica y tiene esa clasificacion
                if(ids_rutis.contains(id_ruti)){
                    rutisClas.add(ruti);
                }
            }
            System.out.println("Se han encontrado las rutinas que son publicas y tiene esa clasificacion");
        }catch(Exception e){
            System.out.println("Error al buscar las rutinas publicas y tiene esa clasificacion");
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        return rutisClas;
    }
    
    
    
    
    //########################## EUsuRuti ###############################
    
    private static int addUsuRuti(int id_perf, int id_ruti, String diaSem) throws SQLException{
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        int estatus = 0;
        try{
            con = ConexionSQL.getConnection();
            
            String q = "Insert into EUsuRuti (id_perf, id_ruti, diaSem)"
                    + "values (?,?,?)";
            
            ps = con.prepareStatement(q);
            
            ps.setInt(1,id_perf);
            ps.setInt(2, id_ruti);
            ps.setString(3, diaSem);
                
           estatus = ps.executeUpdate();
           if(estatus!=0)System.out.println("Se ha agregado la rutina a la Biblioteca Privada correctamente");
            
            
            
            
        }catch(Exception e){
            System.out.println("Error al agregado la rutina a la Biblioteca Privada");
            System.out.println(e.getMessage());    
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    }
    
    public static int deleteUsuRuti(int id_perf, int id_ruti) throws SQLException{
        int estatus = 0;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            
            String q = "delete from EUsuRuti where id_perf = ? and id_ruti = ?";
             ps = con.prepareStatement(q);
            ps.setInt(1, id_perf);
            ps.setInt(2, id_ruti);
            estatus = ps.executeUpdate();
            System.out.println("Se borro la rutina en la Biblioteca Privada");
        }catch(Exception e){
            System.out.println("Error al borrar la rutina en la Biblioteca Privada");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }   
        
        return estatus;
    
        
    }
    
    public static List<Rutina> getRutisUsu(int id_perf) throws SQLException{
        List<Rutina> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from EUsuRuti where id_perf = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_perf);
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id_ruti = rs.getInt("id_ruti");
                Rutina ruti = MRutina.getRutiById(id_ruti);
                ruti.setEjercicios(MEjercicio.getEjers(id_ruti));
                ruti.setDiaSem(rs.getString("diaSem"));
                
                
                lista.add(ruti);
                
            }
            
            System.out.println("Se ha consultado correctamente las rutinas del Usuario");
            
        }catch(Exception e){
            System.out.println("Error al consultar las rutinas del USuario");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
        return lista;
    }
    
    public static List<Rutina> getRutisEstUsu(int id_perf) throws SQLException{
        List<Rutina> lista = null;
        Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
        
        try{
            con = ConexionSQL.getConnection();
            String q = "Select * from EUsuRuti where id_perf = ? and diaSem != ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id_perf);
            ps.setString(2, "na");
            rs = ps.executeQuery();
            
            while(rs.next()){
                int id_ruti = rs.getInt("id_ruti");
                Rutina ruti = MRutina.getRutiById(id_ruti);
                ruti.setEjercicios(MEjercicio.getEjers(id_ruti));
                ruti.setDiaSem(rs.getString("diaSem"));
                
                
                lista.add(ruti);
                
            }
            
            System.out.println("Se ha consultado correctamente las rutinas establecidas del Usuario");
            
        }catch(Exception e){
            System.out.println("Error al consultar las rutinas establecidas del USuario");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
        return lista;
    }

    //########################## ECreaRuti ###############################
    
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

