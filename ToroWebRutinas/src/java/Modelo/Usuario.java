
package Modelo;

/**
 *
 * @author sofo9
 */
public class Usuario extends Perfil{
    int id_perf;
    float altu_usu;
    float peso_usu;
    float calTo_usu;

    
    public Usuario(Perfil per){
        this.setId_perf(per.getId_perf());
        this.setNomb_perf(per.getNomb_perf());
        this.setId_img(per.getId_img());
        this.setEmail_per(per.getEmail_per());
        this.setPass_perf(per.getPass_perf());
        this.setDateNaci_perf(per.getDateNaci_perf());
        this.setAdmin(per.isAdmin());
        this.setCrea(per.isCrea());
    }
    

    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
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

    
    
    
}
