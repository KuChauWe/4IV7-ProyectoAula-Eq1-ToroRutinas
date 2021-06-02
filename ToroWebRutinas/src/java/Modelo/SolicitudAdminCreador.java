package Modelo;

/**
 *
 * @author sofo9
 */
public class SolicitudAdminCreador {
    int soli_AC;
    //Admin [1]
    int id_perf1;
    //Creador [2]
    int id_perf2;
    
    String motivo;
    
    Ejercicio ejer;
    Rutina ruti;

    public Ejercicio getEjer() {
        return ejer;
    }

    public void setEjer(Ejercicio ejer) {
        this.ejer = ejer;
    }

    public Rutina getRuti() {
        return ruti;
    }

    public void setRuti(Rutina ruti) {
        this.ruti = ruti;
    }
    
    
    boolean aceptado;

    public int getSoli_AC() {
        return soli_AC;
    }

    public void setSoli_AC(int soli_AC) {
        this.soli_AC = soli_AC;
    }

    public int getId_perf1() {
        return id_perf1;
    }

    public void setId_perf1(int id_perf1) {
        this.id_perf1 = id_perf1;
    }

    public int getId_perf2() {
        return id_perf2;
    }

    public void setId_perf2(int id_perf2) {
        this.id_perf2 = id_perf2;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
    
    
    
}
