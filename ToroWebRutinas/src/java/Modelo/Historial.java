package Modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sofo9
 */
public class Historial {
    int id_perf;
    
    //Key rutina y ArrayList [0] Date dateInicio, [1] Boolean isComplete
    HashMap<Integer,ArrayList> rutiDateIsCom;

    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
    }


    public HashMap<Integer,ArrayList> getRutiDateIsCom() {
        return rutiDateIsCom;
    }

    public void setrRutiDateIsCom(HashMap<Integer,ArrayList> rutiDateIsCom) {
        this.rutiDateIsCom = rutiDateIsCom;
    }
    
    
    
}
