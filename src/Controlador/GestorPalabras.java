/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Model.Juego;
import View.VistaJuego;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import main.java.Controlador.GeneradorLetras;

/**
 *
 * @author meliza
 */
public class GestorPalabras {
    private Juego modelo;
    private VistaJuego vista;
    private GeneradorLetras letras;

    public GestorPalabras(Juego modelo, VistaJuego vista) {
        this.modelo = modelo;
        this.vista = vista;
        letras= new GeneradorLetras();
    }

    public void cargarPalabra() {
        List<String> palabras = modelo.getPalabrasNivel();

        if (palabras.isEmpty()) {
            manejarNivelCompletado();
            return;
        }

        String palabra = palabras.get(0);
        modelo.setPalabraActual(palabra);
        letras.generarLetras(desordenarLetras(palabra));
        letras.generarCasillas(palabra.length());
       // vista.mostrarPalabraDesordenada(desordenarLetras(palabra));
        //vista.mostrarCasillas(palabra.length());
    }

    public List<Character> desordenarLetras(String palabra) {
        List<Character> letras = palabra.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        Collections.shuffle(letras);
        return letras;
    }

    public boolean verificarPalabra(String palabraIngresada) {
        return palabraIngresada.equals(modelo.getPalabraActual());
    }

    private void manejarNivelCompletado() {
        JOptionPane.showMessageDialog(vista, "¡Felicidades! Has completado este nivel.");
        modelo.avanzarNivel();
        modelo.cargarPalabrasDelNivel();

        if (modelo.getPalabrasNivel().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "¡Juego completado!");
        }
    }
}
