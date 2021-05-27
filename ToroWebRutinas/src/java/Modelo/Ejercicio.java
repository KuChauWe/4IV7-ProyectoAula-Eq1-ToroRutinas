package Modelo;
import java.util.List;
import Modelo.*;

/**
 *
 * @author sofo9
 */
public class Ejercicio {
    int id_ejer;
    String nom_ejer;
    float calPer;
    List<Imagen> img;
    List<Clasificacion> clas;
    boolean publica;

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }
    
    
    public void Ejercicio(){}

    public float getCalPer() {
        return calPer;
    }

    public void setCalPer(float calPer) {
        this.calPer = calPer;
    }

    public List<Clasificacion> getClas() {
        return clas;
    }

    public void setClas(List<Clasificacion> clas) {
        this.clas = clas;
    }

    public int getId_ejer() {
        return id_ejer;
    }

    public void setId_ejer(int id_ejer) {
        this.id_ejer = id_ejer;
    }

    public String getNom_ejer() {
        return nom_ejer;
    }

    public void setNom_ejer(String nom_ejer) {
        this.nom_ejer = nom_ejer;
    }

    public List<Imagen> getImg() {
        return img;
    }

    public void setImg(List<Imagen> img) {
        this.img = img;
    }
    
    
    
    
}
