package Controlador;
import Modelo.Imagen;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author GaelFernandez & sofo9
 */
public class AccionesImagen extends HttpServlet {

    public static int registrarImagen(Imagen e){
        int estatus = 0;
        Blob imagen = null;
        
        
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "insert into MImagen(id_img, nom_img, foto_img,"
                    + "values(?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            //usar getter and setter
            ps.setInt(1, e.getId_img());
            ps.setString(2, e.getNom_img());
            ps.setBlob(3, e.getFoto_img());
            
            estatus = ps.executeUpdate();
            System.out.println("Registro exitoso de la imagen");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al registrar la imagen");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
    }
            
    public static int borrarImagen(int id){
        int estatus = 0;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "delete from MImagen where id_img = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            System.out.println("Imagen eliminada exitosamente");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al borrar la imagen");
            System.out.println(ed.getMessage());
        
        }
        return estatus;
    }
    
    public static Imagen buscarImagenById(int id){
        Imagen e = new Imagen();
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "select * from MImagen where id_img = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                e.setId_img(rs.getInt(1));
                e.setNom_img(rs.getString(2));
                e.setFoto_img(rs.getImage(3));
            }
            
            System.out.println("Imagen encontrada");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar la imagen");
            System.out.println(ed.getMessage());
        
        }
        return e;
    }
    
    public static List<Imagen> buscarAllImagenes(){
        List<Imagen> lista = new ArrayList<Imagen>();
        
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "select * from MImagen";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Imagen e = new Imagen();
                e.setId_img(rs.getInt(1));
                e.setNom_img(rs.getString(2));
                e.setFoto_img(rs.getImage(3));
                lista.add(e);
            }
            
            System.out.println("Imágenes encontradas");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar las imágenes");
            System.out.println(ed.getMessage());
        
        }
        return lista;
    }
    
    public static List<Imagen> getImagenes(int id_ejer){
        List<Integer> ids = null;
        List<Imagen> lista = null;
        try{
            Connection con = ConexionSQL.getConnection();
            String q = "Select * from EImagen where id_ejer = ?";
            
            PreparedStatement ps = con.prepareStatement(q);
            
            ps.setInt(1, id_ejer);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ids.add(rs.getInt("id_ejer"));
            }
            System.out.println("Ids encontrados");
            
            try{
                
                for(int id_img:ids){
                    lista.add(AccionesImagen.buscarImagenById(id_img));
                }
                
            
            }catch(Exception e){
                System.out.println("Erro al conseguir las imagenes");
                System.out.println(e.getMessage());
            
            }
            
        
        }catch(Exception e){
            System.out.println("Erro al conseguir los ids de las imagenes");
            System.out.println(e.getMessage());
        }
        
    
        return lista;
    }
    
    
}
