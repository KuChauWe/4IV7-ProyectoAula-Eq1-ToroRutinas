
package Modelo;
import java.sql.Date;

/**
 *
* @author sofo9
 */
public class Perfil {
   int id_perf;
   String nomb_perf;
   String id_img;
   String email_per;
   String pass_perf;
   Date dateNaci_perf;
   boolean admin;
   boolean crea;
   

    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
    }

    public String getNomb_perf() {
        return nomb_perf;
    }

    public void setNomb_perf(String nom_perf) {
        this.nomb_perf = nom_perf;
    }

    public String getId_img() {
        return id_img;
    }

    public void setId_img(String id_img) {
        this.id_img = id_img;
    }

    public String getEmail_per() {
        return email_per;
    }

    public void setEmail_per(String email_per) {
        this.email_per = email_per;
    }

    public String getPass_perf() {
        return pass_perf;
    }

    public void setPass_perf(String pass_perf) {
        this.pass_perf = pass_perf;
    }

    public Date getDateNaci_perf() {
        return dateNaci_perf;
    }

    public void setDateNaci_perf(Date dateNaci_perf) {
        this.dateNaci_perf = dateNaci_perf;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isCrea() {
        return crea;
    }

    public void setCrea(boolean creador) {
        this.crea = creador;
    }

}
