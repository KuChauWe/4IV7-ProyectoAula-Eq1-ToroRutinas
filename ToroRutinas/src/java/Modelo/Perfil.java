package Modelo;

import java.awt.Image;
import java.sql.Date;

/**
 *
* @author sofo9
 */
public class Perfil {
   int id_perf;
   String nom_perf;
   Imagen img_perf;
   String email_per;
   String contra_perf;
   Date fechNaci_perf;
   boolean admin;
   boolean creador;
   
   public void Perfil(){}

    public int getId_perf() {
        return id_perf;
    }

    public void setId_perf(int id_perf) {
        this.id_perf = id_perf;
    }

    public String getNom_perf() {
        return nom_perf;
    }

    public void setNom_perf(String nom_perf) {
        this.nom_perf = nom_perf;
    }

    public Imagen getImg_perf() {
        return img_perf;
    }

    public void setImg_perf(Imagen img_perf) {
        this.img_perf = img_perf;
    }

    public String getEmail_per() {
        return email_per;
    }

    public void setEmail_per(String email_per) {
        this.email_per = email_per;
    }

    public String getContra_perf() {
        return contra_perf;
    }

    public void setContra_perf(String contra_perf) {
        this.contra_perf = contra_perf;
    }

    public Date getFechNaci_perf() {
        return fechNaci_perf;
    }

    public void setFechNaci_perf(Date fechNaci_perf) {
        this.fechNaci_perf = fechNaci_perf;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isCreador() {
        return creador;
    }

    public void setCreador(boolean creador) {
        this.creador = creador;
    }

}