package Modelo;
import java.util.List;

/**
 *
 * @author sofo9
 */
public class Ejercicio {
    int id_ejer;
    int id_perf;
    String nomb_ejer;
    float calPer;
    List<String> ids_img;
    List<String> clas;
    boolean publica;

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }
    
    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
    }

    public float getCalPer() {
        return calPer;
    }

    public void setCalPer(float calPer) {
        this.calPer = calPer;
    }

    public List<String> getClas() {
        return clas;
    }

    public void setClas(List<String> clas) {
        this.clas = clas;
    }

    public int getId_ejer() {
        return id_ejer;
    }

    public void setId_ejer(int id_ejer) {
        this.id_ejer = id_ejer;
    }

    public String getNomb_ejer() {
        return nomb_ejer;
    }

    public void setNomb_ejer(String nom_ejer) {
        this.nomb_ejer = nom_ejer;
    }

    public List<String> getIds_img() {
        return ids_img;
    }

    public void setIds_img(List<String> ids_img) {
        this.ids_img = ids_img;
    }
    
    
    
    
}
