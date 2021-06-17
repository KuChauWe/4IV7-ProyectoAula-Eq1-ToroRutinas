package Modelo;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sofo9
 */
public class Rutina {
    int id_ruti;
    int id_perf;
    String nomb_ruti;
    int durDes_ruti;
    int rep_ruti; 
    
    //int[0]~turno del ejercicio y int[1]~tiempo del ejercicio  
    HashMap<Ejercicio, int[]> ejercicios;
    List<String> clas;
    boolean publica;
    
    //Solo para EUsuRuti
    String diaSem;

    
    
    public String getDiaSem() {
        return diaSem;
    }

    public void setDiaSem(String diaSem) {
        this.diaSem = diaSem;
    }

    
    public int getId_ruti() {
        return id_ruti;
    }

    public void setId_ruti(int id_ruti) {
        this.id_ruti = id_ruti;
    }
    
    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
    }

    public String getNomb_ruti() {
        return nomb_ruti;
    }

    public void setNomb_ruti(String nomb_ruti) {
        this.nomb_ruti = nomb_ruti;
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

    public List<String> getClas() {
        return clas;
    }

    public void setClas(List<String> clas) {
        this.clas = clas;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    
    
}
