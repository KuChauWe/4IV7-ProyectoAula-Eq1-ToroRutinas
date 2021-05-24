package Controlador;
import Modelo.Imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author GaelFernandez
 */
public class AccionesImagen extends HttpServlet {

        public static int registrarImagen(Imagen e){
        int estatus = 0;
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
    
}
