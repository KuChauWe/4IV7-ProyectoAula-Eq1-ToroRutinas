package Modelo;

import java.util.List;

/**
 *
 * @author sofo9
 */
public class BibliotecaUsuario {
    Usuario usu;
    List<Rutina> rutinas;

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<Rutina> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<Rutina> rutinas) {
        this.rutinas = rutinas;
    }
    
}
