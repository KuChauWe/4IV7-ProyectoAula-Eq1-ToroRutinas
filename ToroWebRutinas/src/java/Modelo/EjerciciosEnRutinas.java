package Modelo;

import java.util.List;
import java.util.HashMap;

/**
 *
 * @author sofo9
 */
public class EjerciciosEnRutinas {
    int id_EjerRuti;
    Rutina ruti;
    HashMap<Ejercicio, String> ejer_Pos;
    HashMap<Ejercicio, String> ejer_Dur;
    List<Clasificacion> clas;
    boolean publica;
    
    public void EjercicioEnRutinas(){}

    public int getId_EjerRuti() {
        return id_EjerRuti;
    }

    public void setId_EjerRuti(int id_EjerRuti) {
        this.id_EjerRuti = id_EjerRuti;
    }

    public Rutina getRuti() {
        return ruti;
    }

    public void setRuti(Rutina ruti) {
        this.ruti = ruti;
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
