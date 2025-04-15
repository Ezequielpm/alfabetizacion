/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meliza
 */
public class Juego {

    private List<String> palabrasNivel;
    private int nivelActual;
    private int tiempoRestante;
    private String palabraActual;
    private List<List<String>> niveles;

    public Juego() {
        nivelActual = 1;
        tiempoRestante = 10;
        palabrasNivel = new ArrayList<>();
        niveles = new ArrayList<>();
        cargarNiveles();
    }

    private void cargarNiveles() {

        niveles.add(List.of("casa", "cama", "baño", "silla", "mesa"));
        niveles.add(List.of("cocina", "habitación", "jardín", "sábana", "almohada"));
        niveles.add(List.of("ventilador", "mueble", "comedor", "azotea"));

    }

    public void cargarPalabrasDelNivel() {
        if (nivelActual - 1 < niveles.size()) {
            palabrasNivel = new ArrayList<>(niveles.get(nivelActual - 1));
        } else {
            palabrasNivel.clear();
        }
    }

    public void avanzarNivel() {
        nivelActual++;
        cargarPalabrasDelNivel();
    }

    public List<String> getPalabrasNivel() {
        return palabrasNivel;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }
// Reduce el tiempo restante en 1 segundo

    public void reducirTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public void reiniciarTiempo() {
        tiempoRestante = 10;
    }

    public String getPalabraActual() {
        return palabraActual;
    }

    public void setPalabraActual(String palabra) {
        this.palabraActual = palabra;
    }

    public void eliminarPalabraActual() {
        if (!palabrasNivel.isEmpty()) {
            palabrasNivel.remove(0);
        }
    }

    /* public boolean verificarPalabra(String original, String ordenada) {
        return original.equals(ordenada);
    }*/

 /*public void setTiempoRestante(int tiempo) {

    }*/
}
