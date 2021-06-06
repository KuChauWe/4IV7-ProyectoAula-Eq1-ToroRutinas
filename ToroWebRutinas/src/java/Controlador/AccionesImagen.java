package Controlador;
import Modelo.*;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author   sofo9
 */
public class AccionesImagen extends HttpServlet {

    public static int registrarImagen(Imagen e) throws SQLException{
        int estatus = 0;
        Blob imagen = null;
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try{
             con = ConexionSQL.getConnection();
            String q = "insert into CImagen(id_img, nom_img, foto_img)"
                    + "values(?,?,?)";
            
             ps = con.prepareStatement(q);
             
             
             
            
            //usar getter and setter
            ps.setInt(1, e.getId_img());
            ps.setString(2, e.getNom_img());
            
                
            ps.setBlob(3, Imagen.getBlobWithInputStream(e.getFoto_img()));
            
            
            
            
            
            estatus = ps.executeUpdate();
            System.out.println("Registro exitoso de la imagen");
        
        }catch(Exception ed){
            System.out.println("Error al registrar la imagen");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
            
    public static int borrarImagen(int id) throws SQLException{
        int estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "delete from MImagen where id_img = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            System.out.println("Imagen eliminada exitosamente");
        
        }catch(Exception ed){
            System.out.println("Error al borrar la imagen");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return estatus;
    }
    
    public static Imagen buscarImagenById(int id) throws SQLException{
        Imagen e = new Imagen();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "select * from cImagen where id_img = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, id);
            
             rs = ps.executeQuery();
            if(rs.next()){
                e.setId_img(rs.getInt("id_img"));
                e.setNom_img(rs.getString(2));
                Blob blob = rs.getBlob("foto_img");
                e.setFoto_img( blob.getBinaryStream());
                
            }
            
            System.out.println("Imagen encontrada");
        
        }catch(Exception ed){
            System.out.println("Error al buscar la imagen");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return e;
    }
    
    public static int getId(Imagen e) throws SQLException{
        System.out.println("Entro al m"); 
        int id_img = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
             con = ConexionSQL.getConnection();
            String q = "select id_img from cImagen where nom_img = ?";
            
             ps = con.prepareStatement(q);
             
             
            
            ps.setString(1, e.getNom_img());
            
             rs = ps.executeQuery();
            if(rs.next()){
                id_img = rs.getInt("id_img");
            }
            
            System.out.println("Id de la Imagen encontrada");
        
        }catch(Exception ed){
            System.out.println("Error al buscar el ID de la imagen");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return id_img;
    }
    
    public static List<Imagen> buscarAllImagenes() throws SQLException{
        List<Imagen> lista = new ArrayList<Imagen>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
             con = ConexionSQL.getConnection();
            String q = "select * from MImagen";
            
             ps = con.prepareStatement(q);
            
            
             rs = ps.executeQuery();
            while(rs.next()){
                Imagen e = new Imagen();
                e.setId_img(rs.getInt("id_img"));
                e.setNom_img(rs.getString("nomb_img"));
                e.setFoto_img((InputStream)rs.getBlob("foto_img"));
                
                lista.add(e);
            }
            
            System.out.println("Imágenes encontradas");
            con.close();
        
        }catch(Exception ed){
            System.out.println("Error al buscar las imágenes");
            System.out.println(ed.getMessage());
        
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        return lista;
    }
    
    public static List<Imagen> getImagenesEjercicio(Ejercicio ejer) throws SQLException{
        List<Integer> ids = null;
        List<Imagen> lista = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
             con = ConexionSQL.getConnection();
            String q = "Select * from EImagen where id_ejer = ?";
            
             ps = con.prepareStatement(q);
            
            ps.setInt(1, ejer.getId_ejer());
            
             rs = ps.executeQuery();
            
            while(rs.next()){
                ids.add(rs.getInt("id_ejer"));
            }
            System.out.println("Ids encontrados");
            
            try{
                
                for(int id_img:ids){
                    lista.add(AccionesImagen.buscarImagenById(id_img));
                }
                
            
            }catch(Exception e){
                System.out.println("Error al conseguir las imagenes");
                System.out.println(e.getMessage());
            
            }
            
        
        }catch(Exception e){
            System.out.println("Erro al conseguir los ids de las imagenes");
            System.out.println(e.getMessage());
        }finally{
            rs.close();
            ps.close();
            con.close();
        }
        
    
        return lista;
    }
    
    
}
