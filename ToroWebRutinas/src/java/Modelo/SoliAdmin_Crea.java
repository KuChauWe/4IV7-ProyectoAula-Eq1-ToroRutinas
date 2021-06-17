package Modelo;

/**
 *
 * @author sofo9
 */
public class SoliAdmin_Crea {
    //Admin [1]
    int id_perf1;
    //Creador [2]
    int id_perf2;
    String motivo;
    int id_ejer;
    int id_ruti;
    Boolean approved;
  
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
    
    public int getId_ejer() {
        return id_ejer;
    }

    public void setId_ejer(int id_ejer) {
        this.id_ejer = id_ejer;
    }

    public int getId_ruti() {
        return id_ruti;
    }

    public void setId_ruti(int id_ruti) {
        this.id_ruti = id_ruti;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    
    
    
}
