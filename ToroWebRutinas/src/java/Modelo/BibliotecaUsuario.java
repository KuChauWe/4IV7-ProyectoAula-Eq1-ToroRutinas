package Modelo;

import java.util.List;

/**
 *
 * @author sofo9
 */
public class BibliotecaUsuario {
    Usuario usu;
    List<EjerciciosEnRutinas> rutinas;

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<EjerciciosEnRutinas> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<EjerciciosEnRutinas> rutinas) {
        this.rutinas = rutinas;
    }
    
}
