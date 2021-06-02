package Modelo;

/**
 *
 * @author sofo9
 */
public class SolicitudAdminUsuario {
    int id_soliAU;
    int id_usu;
    int id_perf;
    String motivo;
    boolean paraAdmin;
    boolean paraCread;
    boolean aceptado;

    public int getId_soliAU() {
        return id_soliAU;
    }

    public void setId_soliAU(int id_soliAU) {
        this.id_soliAU = id_soliAU;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isParaAdmin() {
        return paraAdmin;
    }

    public void setParaAdmin(boolean paraAdmin) {
        this.paraAdmin = paraAdmin;
    }

    public boolean isParaCread() {
        return paraCread;
    }

    public void setParaCread(boolean paraCread) {
        this.paraCread = paraCread;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
    
    
    
}