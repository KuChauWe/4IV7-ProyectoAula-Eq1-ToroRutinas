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
    //int[0]~posicion y int[1]~dur_ejer
    HashMap<Ejercicio, int[]> ejercicios;
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
    
    public  HashMap<Ejercicio, int[]> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios( HashMap<Ejercicio, int[]> ejercicios) {
        this.ejercicios = ejercicios;
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