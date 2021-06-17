package Modelo;

/**
 *
 * @author sofo9
 */
public class SoliAdmin_Usua {
    //Admin [1]
    int id_perf1;
    //Usuario [2]
    int id_perf2;
    String motivo;
    boolean forAdmin;
    boolean forCrea;
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
    
    public boolean getForAdmin() {
        return forAdmin;
    }

    public void setForAdmin(boolean forAdmin) {
        this.forAdmin = forAdmin;
    }

    public boolean getForCrea() {
        return forCrea;
    }

    public void setForCrea(boolean forCrea) {
        this.forCrea = forCrea;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
    
    
    
}
