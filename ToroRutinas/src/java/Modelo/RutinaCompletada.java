package Modelo;

import java.sql.Date;
import java.util.HashMap;

/**
 *
 * @author sofo9
 */
public class RutinaCompletada {
    int id_RutiCom;
    int id_usu;
    //Key rutina y Value fecha de completado
    HashMap<Rutina,Date> RutinaYFecha; 

    public int getId_RutiCom() {
        return id_RutiCom;
    }

    public void setId_RutiCom(int id_RutiCom) {
        this.id_RutiCom = id_RutiCom;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public HashMap<Rutina, Date> getRutinaYFecha() {
        return RutinaYFecha;
    }

    public void setRutinaYFecha(HashMap<Rutina, Date> RutinaYFecha) {
        this.RutinaYFecha = RutinaYFecha;
    }   
}