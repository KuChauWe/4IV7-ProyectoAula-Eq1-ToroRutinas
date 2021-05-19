package Modelo;

import java.util.List;

/**
 *
 * @author sofo9
 */
public class BibliotecaCreador {
    Perfil perf;
    List<EjerciciosEnRutinas> rutinas;
    List<Ejercicio> ejercicios;

    public Perfil getPerf() {
        return perf;
    }

    public void setPerf(Perfil perf) {
        this.perf = perf;
    }

    public List<EjerciciosEnRutinas> getRutinas() {
        return rutinas;
    }

    public void setRutinas(List<EjerciciosEnRutinas> rutinas) {
        this.rutinas = rutinas;
    }

    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(List<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }
    
}
