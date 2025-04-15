/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Model.Juego;
import Model.Score;
import View.VistaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author meliza
 */
public class ControladorJuego implements ActionListener {

    private Juego modelo;
    private Score score;
    private VistaJuego vista;
    private TemporizadorJuego temporizador;
    private GestorPalabras gestorPalabras;
    private GeneradorLetras generadorLetras;
    private GestorInterfaz gestorInterfaz;

    public ControladorJuego(Juego modelo, VistaJuego vista) {
        this.modelo = modelo;
        this.score = new Score();
        this.vista = vista;
        this.temporizador = new TemporizadorJuego(modelo, vista, this::reiniciarJuego);
        this.gestorPalabras = new GestorPalabras(modelo, vista);
        this.generadorLetras = new GeneradorLetras();
        this.gestorInterfaz = new GestorInterfaz(vista);

        modelo.cargarPalabrasDelNivel();
        iniciarJuego();
        configurarListeners();
    }

    private void configurarListeners() {
        vista.botonVerificar.addActionListener(this);
        vista.botonReiniciar.addActionListener(e -> {
            String palabra = modelo.getPalabraActual();

    temporizador.reiniciar(); 
     List<JLabel> etiquetasCasillas = generadorLetras.generarCasillas(palabra.length());// Reinicia el temporizador
   gestorInterfaz.actualizarCasillas(etiquetasCasillas);    // Llama al método para cargar una nueva palabra
});
        
        
        
        
        vista.botonRetos.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonVerificar) {
            String palabraIngresada = obtenerPalabraIngresada();
            if (gestorPalabras.verificarPalabra(palabraIngresada)) {
                temporizador.detener();
                gestorInterfaz.mostrarMensaje("¡Correcto!");

                int puntajeActual = score.getScore();
                score.setScore(puntajeActual + 10);
                vista.actualizarPuntaje(score);  // Pasa el objeto score, no solo el puntaje
                modelo.eliminarPalabraActual();
                reiniciarJuego();
            } else {
                gestorInterfaz.mostrarMensaje("¡Incorrecto!");
                reiniciarJuego();
            }
        }
    }

    private void iniciarJuego() {
        temporizador.reiniciar();
        gestorPalabras.cargarPalabra();
        actualizarVista();
    }

    private void reiniciarJuego() {
        temporizador.reiniciar();
        gestorPalabras.cargarPalabra();
        score.setScore(0);
        actualizarVista();

    }

    private void actualizarVista() {
        String palabra = modelo.getPalabraActual();

        if (palabra == null || palabra.isEmpty()) {
            gestorInterfaz.mostrarMensaje("No hay más palabras en este nivel.");
            return;
        }

        List<Character> letras = gestorPalabras.desordenarLetras(palabra);
        List<JLabel> etiquetasLetras = generadorLetras.generarLetras(letras);
        List<JLabel> etiquetasCasillas = generadorLetras.generarCasillas(palabra.length());

        gestorInterfaz.actualizarLetras(etiquetasLetras);
        gestorInterfaz.actualizarCasillas(etiquetasCasillas);
        vista.actualizarPuntaje(score);  // Pasa el objeto score, no solo el puntaje
    }

    private String obtenerPalabraIngresada() {
        StringBuilder palabra = new StringBuilder();
        for (int i = 0; i < vista.panelCasillas1.getComponentCount(); i++) {
            JLabel casilla = (JLabel) vista.panelCasillas1.getComponent(i);
            palabra.append(casilla.getText());
        }
        return palabra.toString();
    }

}
