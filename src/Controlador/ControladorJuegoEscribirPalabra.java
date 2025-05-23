/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import View.JuegoEscribirPalabra;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author ezequielpena
 */
public class ControladorJuegoEscribirPalabra implements ActionListener {

    int puntajeGanado = 0;
    JuegoEscribirPalabra objJuegoEscribirPalabra;
    String[] listaPalabrasFacil
            = {"correr", "pintar", "leer", "escribir", "bailar",
                "nadar", "cantar", "jugar", "dibujar", "saltar", "cocinar", "caminar", "estudiar",
                "actuar", "explorar"
            };
    ArrayList<String> palabrasAdivinadasFacil = new ArrayList<>();
    String palabraActual = "";

    public ControladorJuegoEscribirPalabra(JuegoEscribirPalabra objJuegoEscribirPalabra) {
        this.objJuegoEscribirPalabra = objJuegoEscribirPalabra;
        this.objJuegoEscribirPalabra.campoRespuesta.addActionListener(this);
        this.objJuegoEscribirPalabra.botonComprobar.addActionListener(this);
        this.objJuegoEscribirPalabra.botonReproducirSonido.addActionListener(this);
        this.objJuegoEscribirPalabra.botonCambiarPalabra.addActionListener(this);
        this.objJuegoEscribirPalabra.botonInstrucciones.addActionListener(this);
        this.objJuegoEscribirPalabra.campoRespuesta.setBackground(new Color(0, 0, 0, 0));
        reiniciarPuntaje();
        inicializarPalabra();
        System.out.println(palabraActual);

    }

    private void inicializarPalabra() {
        palabraActual = elegirPalabraAleatoria(listaPalabrasFacil);
        reproducirSonido(palabraActual);
        restablecerCursor();
    }

    private String elegirPalabraAleatoria(String[] arrayDePalabras) {
        return arrayDePalabras[(int) (Math.random() * arrayDePalabras.length)];
    }

    private boolean comprobarRespuesta() {
        return palabraActual.toUpperCase().equals(this.objJuegoEscribirPalabra.campoRespuesta.getText().toUpperCase());
    }

    private void cambiarPalabra() {
        if(!(this.listaPalabrasFacil.length==this.palabrasAdivinadasFacil.size())){
        palabraActual = elegirPalabraAleatoria(listaPalabrasFacil);
        if (palabrasAdivinadasFacil.contains(palabraActual)) {
            cambiarPalabra();
        }}
    }

    private void limpiarCampoRespuesta() {
        this.objJuegoEscribirPalabra.campoRespuesta.setText("");
        restablecerCursor();

    }

    private void restablecerCursor() {
        this.objJuegoEscribirPalabra.campoRespuesta.requestFocusInWindow();
    }

    private void reiniciarPuntaje() {
        this.objJuegoEscribirPalabra.puntuacion.setText("0");
    }

    private void actualizarVistaPuntaje() {
        this.objJuegoEscribirPalabra.puntuacion.setText(String.valueOf(puntajeGanado));
    }
    
    private void mensajeNivelCompletado(){
        reproducirSonido("nivel_completado");
    }
    
    private void proponerReto(){
        int palabrasAdivinadas = this.palabrasAdivinadasFacil.size();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.objJuegoEscribirPalabra.botonComprobar || e.getSource() == this.objJuegoEscribirPalabra.campoRespuesta) {
            if (comprobarRespuesta()) {
                reproducirSonido("puntos");
                puntajeGanado += 20;
                actualizarVistaPuntaje();
                mensajeExitoso();
                palabrasAdivinadasFacil.add(palabraActual);
                cambiarPalabra();
                if(!(this.listaPalabrasFacil.length==this.palabrasAdivinadasFacil.size())){
                    reproducirSonido(palabraActual);
                }
                
                limpiarCampoRespuesta();
                System.out.println(palabraActual);
                if(this.listaPalabrasFacil.length==this.palabrasAdivinadasFacil.size()){
                    mensajeNivelCompletado();
                }
            } else {
                mensajeFracaso();
            }

            return;
        }
        if (e.getSource() == this.objJuegoEscribirPalabra.botonReproducirSonido) {
            reproducirSonido(palabraActual);
            return;
        }
        if (e.getSource() == this.objJuegoEscribirPalabra.botonCambiarPalabra) {
            cambiarPalabra();
            limpiarCampoRespuesta();
            restablecerCursor();
            reproducirSonido(palabraActual);
            return;
        }
        if (e.getSource() == this.objJuegoEscribirPalabra.botonInstrucciones) {
            desactivarBotones();
            reproducirSonido("instrucciones");
            try {
                Thread.sleep(44000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControladorJuegoEscribirPalabra.class.getName()).log(Level.SEVERE, null, ex);
            }
            activarBotones();
            return;
        }
    }

    private void desactivarBotones() {
        for (Object obj : this.objJuegoEscribirPalabra.getComponents()) {
            if (obj instanceof JButton boton) {
                boton.setEnabled(false);
            }
        }
    }

    private void activarBotones() {
        for (Object obj : this.objJuegoEscribirPalabra.getComponents()) {

            if (obj instanceof JButton boton) {
                boton.setEnabled(false);
            }
        }
    }

    /*
    mensajes que se muestran en caso de que el usuario acerte o se equivoque
    idealmente debería reproducirse un audio en lugar de mostrar un mensaje ya
    que la persona se supone que no sabe leer aun
     */
    private void mensajeExitoso() {
        JOptionPane.showMessageDialog(objJuegoEscribirPalabra, "Correcto!");
    }

    private void mensajeFracaso() {
        JOptionPane.showMessageDialog(objJuegoEscribirPalabra, "Vuelve a intentarlo!");
    }

    public void reproducirSonido(String nombreAudio) {
        String rutaArchivo = "../resources/audios_juegos/escribir_palabra/facil/" + nombreAudio + ".wav";
        try {
            // Carga el archivo de sonido como un InputStream desde el classpath
            InputStream audioSrc = getClass().getResourceAsStream(rutaArchivo);
            if (audioSrc == null) {
                System.err.println("No se encontró el archivo de sonido: " + rutaArchivo);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
            Clip clipBoton = AudioSystem.getClip();
            clipBoton.open(audioStream);
            clipBoton.start(); // Reproduce el sonido
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
