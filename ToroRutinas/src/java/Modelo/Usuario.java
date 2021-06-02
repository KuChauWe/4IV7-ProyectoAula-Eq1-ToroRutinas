package Modelo;

/**
 *
 * @author sofo9
 */
public class Usuario extends Perfil{
    int id_usu;
    float altu_usu;
    float peso_usu;
    float calTo_usu;
    int id_biBlio_usu;
    
    public void Usuario(Perfil per){
        this.setId_perf(per.getId_perf());
        this.setNom_perf(per.getNom_perf());
        this.setImg_perf(per.getImg_perf());
        this.setEmail_per(per.getEmail_per());
        this.setContra_perf(per.getContra_perf());
        this.setFechNaci_perf(per.getFechNaci_perf());
        this.setAdmin(per.isAdmin());
        this.setCreador(per.isCreador());
    }
    

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public float getAltu_usu() {
        return altu_usu;
    }

    public void setAltu_usu(float altu_usu) {
        this.altu_usu = altu_usu;
    }

    public float getPeso_usu() {
        return peso_usu;
    }

    public void setPeso_usu(float peso_usu) {
        this.peso_usu = peso_usu;
    }

    public float getCalTo_usu() {
        return calTo_usu;
    }

    public void setCalTo_usu(float calTo_usu) {
        this.calTo_usu = calTo_usu;
    }

    public int getId_biBlio_usu() {
        return id_biBlio_usu;
    }

    public void setId_biBlio_usu(int id_biBlio_usu) {
        this.id_biBlio_usu = id_biBlio_usu;
    }
    
    
}