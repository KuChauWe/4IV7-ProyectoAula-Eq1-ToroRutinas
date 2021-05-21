package Modelo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sofo9
 */
public class Rutina {
    int id_ruti;
    String nom_ruti;
    int durDes_ruti;
    int rep_ruti; 
    HashMap<Ejercicio, String> ejer_Pos;
    HashMap<Ejercicio, String> ejer_Dur;
    List<Clasificacion> clas;
    boolean publica;

    
    public int getId_ruti() {
        return id_ruti;
    }

    public void setId_ruti(int id_ruti) {
        this.id_ruti = id_ruti;
    }

    public String getNom_ruti() {
        return nom_ruti;
    }

    public void setNom_ruti(String nom_ruti) {
        this.nom_ruti = nom_ruti;
    }

    public int getDurDes_ruti() {
        return durDes_ruti;
    }

    public void setDurDes_ruti(int durDes_ruti) {
        this.durDes_ruti = durDes_ruti;
    }

    public int getRep_ruti() {
        return rep_ruti;
    }

    public void setRep_ruti(int rep_ruti) {
        this.rep_ruti = rep_ruti;
    }
    
    public HashMap<Ejercicio, String> getEjer_Pos() {
        return ejer_Pos;
    }

    public void setEjer_Pos(HashMap<Ejercicio, String> ejer_Pos) {
        this.ejer_Pos = ejer_Pos;
    }

    public HashMap<Ejercicio, String> getEjer_Dur() {
        return ejer_Dur;
    }

    public void setEjer_Dur(HashMap<Ejercicio, String> ejer_Dur) {
        this.ejer_Dur = ejer_Dur;
    }

    public List<Clasificacion> getClas() {
        return clas;
    }

    public void setClas(List<Clasificacion> clas) {
        this.clas = clas;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    
    
}
