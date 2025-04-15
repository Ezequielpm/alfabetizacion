/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juego2;

import Controlador.ControladorJuego;
import Model.Juego;
import View.VistaJuego;

/**
 *
 * @author meliza
 */
public class Juego2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

Juego modelo = new Juego();
        VistaJuego vista = new VistaJuego();
        new ControladorJuego(modelo, vista);
    }
    
}
