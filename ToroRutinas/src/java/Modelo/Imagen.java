package Modelo;

import java.awt.Image;

/**
 *
 * @author sofo9
 */
public class Imagen {
    int id_img;
    String nom_img;
    Image foto_img;

    public int getId_img() {
        return id_img;
    }

    public void setId_img(int id_img) {
        this.id_img = id_img;
    }

    public String getNom_img() {
        return nom_img;
    }

    public void setNom_img(String nom_img) {
        this.nom_img = nom_img;
    }

    public Image getFoto_img() {
        return foto_img;
    }

    public void setFoto_img(Image foto_img) {
        this.foto_img = foto_img;
    }
}