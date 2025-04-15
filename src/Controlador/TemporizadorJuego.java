/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Model.Juego;
import View.VistaJuego;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author meliza
 */
public class TemporizadorJuego {
    private Timer temporizador;
    private Juego modelo;
    private VistaJuego vista;
    private Runnable onTiempoAgotado;

    public TemporizadorJuego(Juego modelo, VistaJuego vista, Runnable onTiempoAgotado) {
        this.modelo = modelo;
        this.vista = vista;
       // GestorPalabras gestorPalabras = new GestorPalabras(modelo, vista);

        this.onTiempoAgotado = onTiempoAgotado;
    }

    public void iniciar() {
        if (temporizador != null && temporizador.isRunning()) {
            temporizador.stop();
        }

        temporizador = new Timer(1000, e -> {
            modelo.reducirTiempo();
            vista.actualizarTiempo(modelo.getTiempoRestante());

            if (modelo.getTiempoRestante() == 0) {
                detener();
                 mostrarMensaje("¡Tiempo terminado!");
                //onTiempoAgotado.run();
            }
        });
        temporizador.start();
    }

    public void detener() {
        if (temporizador != null) {
            temporizador.stop();
        }
    }

    public void reiniciar() {
        detener();
        modelo.reiniciarTiempo();
        vista.actualizarTiempo(modelo.getTiempoRestante());
        
        iniciar();
    }
    
    
     private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
    }
   
}
