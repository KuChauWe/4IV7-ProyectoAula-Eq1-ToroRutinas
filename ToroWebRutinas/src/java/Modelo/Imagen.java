package Modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

/**
 *
 * @author sofo9
 */
public class Imagen {
    int id_img;
    String nom_img;
    InputStream  foto_img;

    public int getId_img() {
        return id_img;
    }

    public void setId_img(int id_img) {
        this.id_img = id_img;
    }

    public String getNom_img() {
        return nom_img;
    }

    public void setNom_img(String nom_img) {
        this.nom_img = nom_img;
    }

    public InputStream  getFoto_img() {
        return foto_img;
    }

    public void setFoto_img(InputStream  foto_img) {
        this.foto_img = foto_img;
    }
    
    
   
    public static Blob getBlobWithInputStream(InputStream myinputstream) throws IOException{
        byte[] contents;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count;
        while ((count = myinputstream.read(buffer)) != -1){output.write(buffer, 0, count);}//debugger says myinputstream has blksize 16384, buffcount 12742, and max 127394 here
        contents = output.toByteArray();
        Blob blob = null;
        try {blob = new SerialBlob(contents);} 
        catch (SerialException e) {e.printStackTrace();}
        catch (SQLException e) {e.printStackTrace();}
        return blob;
    }
    
    
    public static byte[] getbytesInputStream(InputStream in) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            int reads = in.read(); 
            
            while(reads != -1){ 
                baos.write(reads); 
                reads = in.read(); 
            } 
            return baos.toByteArray();
        
        
    }
    
    public static Image getImage(InputStream in) throws IOException{
        BufferedImage bImage = ImageIO.read(in);
        Image imag = bImage.getScaledInstance(128, 128, 0);
        
       return imag; 
    }
    
}
