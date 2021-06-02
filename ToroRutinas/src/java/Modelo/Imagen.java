package Modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    Image foto_img;

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

    public Image getFoto_img() {
        return foto_img;
    }

    public void setFoto_img(Image foto_img) {
        this.foto_img = foto_img;
    }
    
    
    public static Blob convertirImagenABlob ( Image imagen ) {

        
      Blob imagenBlob = null;
      BufferedImage bi = new BufferedImage ( imagen.getWidth ( null ), imagen.getHeight ( null ), BufferedImage.TYPE_INT_ARGB );
      Graphics bg = bi.getGraphics ();
      bg.drawImage ( imagen, 0, 0, null );
      bg.dispose ();

      ByteArrayOutputStream baos = new ByteArrayOutputStream ();
      byte [] imagenByte = baos.toByteArray ();

      try {
         imagenBlob = new SerialBlob ( imagenByte );
      } catch ( SerialException se ) {
         se.printStackTrace ();
      } catch ( SQLException sqle ) {
         sqle.printStackTrace ();
      }
      return imagenBlob;
   }
    
    
    
}