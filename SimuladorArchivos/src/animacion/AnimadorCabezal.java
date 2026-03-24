/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animacion;

import javax.swing.Timer;
import gui.PanelDisco; // Ajusta el import si tu panel está en otro paquete
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import estructuras.ListaEnlazada;

public class AnimadorCabezal {
    
    private Timer timer;
    private PanelDisco panelDisco;
    private ListaEnlazada<Integer> rutaBloques;
    private int indiceActual;

    public AnimadorCabezal(PanelDisco panelDisco) {
        this.panelDisco = panelDisco;
    }

    public void animarRuta(ListaEnlazada<Integer> ruta) {
        this.rutaBloques = ruta;
        this.indiceActual = 0;

        // Configuramos el reloj: 500 milisegundos (medio segundo) por cada movimiento
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indiceActual < rutaBloques.size()) {
                    
                    int bloqueDestino = rutaBloques.get(indiceActual);
                    
                    // --- AQUÍ PUEDE HABER ERROR DEPENDIENDO DE TUS NOMBRES ---
                    panelDisco.setPosicionCabezal(bloqueDestino); 
                    panelDisco.repaint();
                    // ---------------------------------------------------------
                    
                    indiceActual++;
                } else {
                    timer.stop(); // Terminó la ruta
                    javax.swing.JOptionPane.showMessageDialog(null, "¡Simulación de disco terminada!");
                }
            }
        });
        
        timer.start(); // Arranca la animación
    }
}