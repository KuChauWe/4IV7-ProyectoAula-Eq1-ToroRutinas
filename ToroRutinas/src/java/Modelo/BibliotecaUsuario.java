package Modelo;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sofo9
 */
public class BibliotecaUsuario {
    Usuario usu;
    //Rutina y diasemana, si diasemana == null entonces no esta establecida
    HashMap<Rutina, String> rutinas;

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public HashMap<Rutina, String> getRutinas() {
        return rutinas;
    }

    public void setRutinas(HashMap<Rutina, String> rutinas) {
        this.rutinas = rutinas;
    }
    
}