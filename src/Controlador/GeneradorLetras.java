/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 *
 * @author meliza
 */
public class GeneradorLetras {

    public List<JLabel> generarLetras(List<Character> letras) {
        List<JLabel> listaLetras = new ArrayList<>();

        for (char letra : letras) {
            JLabel lblLetra = new JLabel(String.valueOf(letra));
            lblLetra.setFont(new Font("Liberation Mono", Font.BOLD, 48));
            lblLetra.setForeground(Color.decode("#a3b18a"));
            lblLetra.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            lblLetra.setTransferHandler(new TransferHandler("text"));

            lblLetra.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JLabel label = (JLabel) e.getSource();
                    TransferHandler handler = label.getTransferHandler();
                    if (handler != null) {
                        handler.exportAsDrag(label, e, TransferHandler.COPY);
                    }
                }
            });

            listaLetras.add(lblLetra);
        }

        return listaLetras;
    }

    public List<JLabel> generarCasillas(int cantidad) {
        List<JLabel> listaCasillas = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            JLabel lblCasilla = new JLabel("_");
            lblCasilla.setFont(new Font("Arial", Font.BOLD, 48));
            lblCasilla.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            lblCasilla.setTransferHandler(new TransferHandler("text"));

            lblCasilla.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    JLabel casilla = (JLabel) e.getSource();
                    String texto = casilla.getText();
                    if (!"_".equals(texto)) {
                        casilla.setTransferHandler(null);
                    }
                }
            });

            listaCasillas.add(lblCasilla);
        }

        return listaCasillas;
    }
}
