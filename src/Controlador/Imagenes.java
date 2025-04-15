/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author naomi
 */
import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Imagenes {
    private ImageIcon imagen;
    private JLabel labelImagen = new JLabel();

    public Imagenes() {
        
    }

    
    public void imagenAct(String rutaImagen, JPanel panel) {
        ImageIcon nuevaImagen = null;
        URL url = getClass().getClassLoader().getResource(rutaImagen);
        if (url != null) {
            nuevaImagen = new ImageIcon(url);
        } else {
            File file = new File("src/" + rutaImagen);
            if (file.exists()) {
                nuevaImagen = new ImageIcon(file.getAbsolutePath());
            }
        }

        if (nuevaImagen != null) {
            Image image = nuevaImagen.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
            imagen = new ImageIcon(image);

            SwingUtilities.invokeLater(() -> {
                labelImagen.setIcon(imagen);

                if (labelImagen.getParent() == null) {
                    panel.add(labelImagen);
                }

                // Asegurar que el layout se actualiza correctamente
                panel.revalidate();
                panel.repaint();
            });
        } else {
            System.out.println("No se pudo cargar la imagen: " + rutaImagen);
        }
    }
}

