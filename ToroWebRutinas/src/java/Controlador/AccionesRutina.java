package Controlador;
import Modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofo9
 */
public class AccionesRutina {
    
    public static int registrarRutina(Rutina ruti){
        List<Integer> listaestatus = null;
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Insert into MRutina (nom_ruti, durDes_ruti, rep_ruti)"
                    + "values (?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, ruti.getNom_ruti());
            ps.setInt(2, ruti.getDurDes_ruti());
            ps.setInt(3, ruti.getRep_ruti());
            listaestatus.add(ps.executeUpdate());
            
            ruti.setId_ruti(AccionesRutina.buscarIdByRutina(ruti));

            listaestatus.add(AccionesRutina.vincularEjercicios(ruti));
            listaestatus.add(AccionesRutina.vincularRutinaClasificaciones(ruti));
            
            if(ruti.isPublica() == true){
                AccionesRutina.agregarABibliotecaPublica(ruti);
                listaestatus.add(1);
            }
            
            
            if(listaestatus.contains(0) != true){
                estatus = 1;
                System.out.println("Registro exitoso de la rutina");
            }
            
            con.close();
            
        }catch(Exception e){
            System.out.println("Error al registrar la Rutina");
            System.out.println(e.getMessage());
        }
        return estatus;
    }
    
    public static int borrarRutina(Rutina ruti){
         List<Integer> estatus = null;
         int esta = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "delete from MRutina where id_ruti = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, ruti.getId_ruti());
            
            estatus.add(ps.executeUpdate());
            estatus.add(AccionesRutina.desvincularAllEjercicios(ruti));
            estatus.add(AccionesRutina.desvincularAllClasificaciones(ruti));
            
            if(ruti.isPublica() == true){
                AccionesRutina.quitarABibliotecaPublica(ruti);
                estatus.add(1);
            }
            
            if(estatus.contains(0) != true){
                esta = 1;
                System.out.println("Rutina eliminado exitosamente");
            }
            
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al borrar la Rutina");
            System.out.println(ed.getMessage());
        
        }
        return esta;
     
    }
    
    public static Rutina buscarRutinaById(int id_ruti){
        Rutina ruti = new Rutina();
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from MRutina where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ruti.setId_ruti(rs.getInt("id_ruti"));
                ruti.setDurDes_ruti(rs.getInt("durDes_ruti"));
                ruti.setRep_ruti(rs.getInt("rep_ruti"));
            }
            rs.close();
            ps.close();
            
            q = "Select * from DEjercicioEnRutina where id_ruti = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            rs = ps.executeQuery();
            
            HashMap<Ejercicio, int[]> ejers = null;
            while(rs.next()){
                Ejercicio ejer = new Ejercicio();
                int[] durPo = null;
                durPo[0] = rs.getInt("posi_ejeRtui");
                durPo[1] = rs.getInt("duracion");
                ejer = AccionesEjercicio.buscarEjercicioById(rs.getInt("id_ejer"));
                
                ejers.put(ejer, durPo); 
            }
            
            ruti.setEjercicios(ejers);
            ruti.setClas(AccionesClasificacion.buscarClasificacionesRutina(ruti));
            ruti.setPublica(AccionesRutina.isInBibliotecaPublica(id_ruti));
            
            
            
            
            System.out.println("");
        }catch(Exception e){
            System.out.println("Error al buscar la rutina");
        }
        
        
        return ruti;
    }
    
    public static int buscarIdByRutina(Rutina ruti){
        int id = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from MRutina where nom_ruti = ?, durDes_ruti = ?, rep_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, ruti.getNom_ruti());
            ps.setInt(2, ruti.getDurDes_ruti());
            ps.setInt(3, ruti.getRep_ruti());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id_ruti");
            }
            
            System.out.println("Rutina encontrada con la Id");
            
            rs.close();
            ps.close();
            con.close();
            
            
        }catch(Exception e){
            System.out.println("Error al buscar la rutina con Id");
            System.out.println(e.getMessage());
        }
        return id;
    }
    
    public static int actualizarRutina(Rutina ruti){
        List<Integer> listaestatus = null;
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Update MRutina set nom_ruti = ?, durDes_ruti = ?, rep_ruti = ? where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, ruti.getNom_ruti());
            ps.setInt(2, ruti.getDurDes_ruti());
            ps.setInt(3, ruti.getRep_ruti());
            ps.setInt(4, ruti.getId_ruti());
            
            listaestatus.add(ps.executeUpdate());
            listaestatus.add(AccionesRutina.desvincularAllEjercicios(ruti));
            listaestatus.add(AccionesRutina.vincularEjercicios(ruti));
            
            if(ruti.isPublica() == true){
                AccionesRutina.agregarABibliotecaPublica(ruti);
                listaestatus.add(1);
            }
            
            if(listaestatus.contains(0) != true){
                System.out.println("Rutina actualizada con exito");
            }
            
            ps.close();
            con.close();
            
            
            
        }catch(Exception e){
            System.out.println("Error al actualizar la Rutina");
            System.out.println(e.getMessage());
            
        }
        
        
        
        return estatus;
    }
    
    
    //########################## Vinculacion y Desvinculacion de Ejercicios (DEjercicioEnRutina) ###############################
    
    private static int vincularEjercicios(Rutina ruti){
        List<Integer> listaestatus = null;
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            for(Ejercicio ejer: ruti.getEjercicios().keySet()){
                String q = "Insert into DEjercicioEnRutina (id_ruti, id_ejer, pos_ejeRuti, duracion)"
                        + "values (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, ruti.getId_ruti());
                ps.setInt(2, ejer.getId_ejer());
                ps.setInt(3, ruti.getEjercicios().get(ejer)[0]);
                ps.setInt(4, ruti.getEjercicios().get(ejer)[1]);
                listaestatus.add(ps.executeUpdate());
            }
            if(listaestatus.contains(0) != true){
                estatus = 1;
                System.out.println("Se vincularon exitosamente los ejercicios");
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Error al vincular los ejercicios con la Rutina");
            System.out.println(e.getMessage());    
        }
        
        return estatus;
    }
    
    public static int desvincularAllEjercicios(Rutina ruti){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            
            String q = "delete from DEjercicioEnRutina where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            System.out.println("Se desvincularon los ejercicios de la Rutina con exito");
            estatus = ps.executeUpdate();
            
           
            ps.close();
            con.close(); 
        }catch(Exception e){
            System.out.println("Error al desvincular los ejercicios con la Rutina");
            System.out.println(e.getMessage());
        }
        
        return estatus;
    
        
    }
    
    //############################# Vinculación y Desvinculación de Clasificacion (ERutinaClasificacion) #######################
        
    public static int vincularRutinaClasificaciones(Rutina ruti){
        List<Integer> listaestatus = null;
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            for(Clasificacion clas: ruti.getClas()){
                String q = "Insert into ERutinaClasificacion (id_ruti, id_clas)"
                        + "values (?,?)";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, ruti.getId_ruti());
                ps.setInt(2, clas.getId_clas());
                listaestatus.add(ps.executeUpdate());
            }
            if(listaestatus.contains(0) != true){
                estatus = 1;
                System.out.println("Se vincularon exitosamente las clasificaciones");
            }
            
            con.close();
        }catch(Exception e){
            System.out.println("Error al vincular las clasificaciones con la Rutina");
            System.out.println(e.getMessage());    
        }
        
        return estatus;
    }
    
    public static int desvincularAllClasificaciones(Rutina ruti){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            
            String q = "delete from ERutinaClasificacion where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            System.out.println("Se desvincularon las clasificaciones de la Rutina con exito");
            estatus = ps.executeUpdate();
            
            ps.close();
            con.close();
            
            
        }catch(Exception e){
            System.out.println("Error al desvincular las clasificaciones con la Rutina");
            System.out.println(e.getMessage());
        }
        
        return estatus;
        
        
        
    }
    
    //############################ Agregar, Borrar y Consultar las Rutinas de la Biblioteca Publica (DRutinaEnBiblioteca) #####################
       
    public static int agregarABibliotecaPublica(Rutina ruti){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Insert into DRutinaEnBiblioteca(id_ruti) "
                    + "values (?)";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            
            estatus = ps.executeUpdate();
            
            System.out.println("Rutina agregada a la Biblioteca publica");
            
            
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al agregar la rutina a la biblioteca publica");
            System.out.println(e.getMessage());
        }
        
        return estatus;
    }
    
    public static int quitarABibliotecaPublica(Rutina ruti){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "delete from DRutinaEnBiblioteca where id_ruti = ? ";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, ruti.getId_ruti());
            
            estatus = ps.executeUpdate();
            
            System.out.println("Rutina bajada de la Biblioteca publica");
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Error al bajar la rutina en la biblioteca publica");
            System.out.println(e.getMessage());
        }
        
        return estatus;
        
        
        
    }
    
    public static boolean isInBibliotecaPublica(int id_ruti){
        boolean inBibliotecaPublica = false;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from DRutinaEnBiblioteca where id_ruti = ?";
            PreparedStatement ps = con.prepareStatement(q);
            ps.setInt(1, id_ruti);
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id_ruti");
                inBibliotecaPublica = true;
            }
            
            System.out.println("Se consulto la rutina en la Biblioteca");
            
            rs.close();
            ps.close();
            con.close();
        }catch(Exception e){
            System.out.println("Error al consultar la rutina en la Biblioteca Publica");
        }
        
        
        return inBibliotecaPublica;
    }
    
    
    
    
    
}

/*
    public static int desvincularEjercicios(Rutina ruti, List<Ejercicio> ejers){
        List<Integer> listaestatus = null;
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            for(Ejercicio ejer: ejers){
                String q = "delete from DEjercicioEnRutina where id_ruti = ? and id_ejer = ?";
                PreparedStatement ps = con.prepareStatement(q);
                ps.setInt(1, ruti.getId_ruti());
                ps.setInt(2, ejer.getId_ejer());
                listaestatus.add(ps.executeUpdate());
            }
            if(listaestatus.contains(0) != true){
                estatus = 1;
                System.out.println("Se desvincularon  exitosamente los ejercicios");
            }
            
            
        }catch(Exception e){
            System.out.println("Error al desvincular los ejercicios con la Rutina");
            System.out.println(e.getMessage());
        }
        
        return estatus;
    }
    */
