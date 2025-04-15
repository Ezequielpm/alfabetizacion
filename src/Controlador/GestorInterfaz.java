/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import View.VistaJuego;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author meliza
 */
public class GestorInterfaz {
    private VistaJuego vista;

    public GestorInterfaz(VistaJuego vista) {
        this.vista = vista;
    }

    public void actualizarLetras(List<JLabel> letras) {
        vista.panelLetras1.removeAll(); 
        for (JLabel lbl : letras) {
            vista.panelLetras1.add(lbl);
        }
        vista.panelLetras1.revalidate();  
        vista.panelLetras1.repaint();
    }

    public void actualizarCasillas(List<JLabel> casillas) {
        vista.panelCasillas1.removeAll(); // 🔹 Limpiar antes de agregar nuevas casillas
        for (JLabel lbl : casillas) {
            vista.panelCasillas1.add(lbl);
        }
        vista.panelCasillas1.revalidate();  // 🔹 Asegurar que los cambios se reflejen
        vista.panelCasillas1.repaint();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(vista, mensaje);
    }
}
