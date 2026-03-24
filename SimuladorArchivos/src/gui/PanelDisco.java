/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import logica_sistema.SimuladorSD;
import sistema_archivos.Archivo;

public class PanelDisco extends JPanel {
    private SimuladorSD simulador;
    private final int TAMANO_BLOQUE = 20; 
    private final int COLUMNAS = 20; 
    
    // --- NUEVA VARIABLE: Guarda dónde está el cabezal ---
    private int posicionCabezal = 0; 

    public PanelDisco() {
        // Constructor vacío
    }

    public void setSimulador(SimuladorSD simulador) {
        this.simulador = simulador;
        repaint(); 
    }

    // --- NUEVO MÉTODO: El Animador llama a este método para mover el cabezal ---
    public void setPosicionCabezal(int posicion) {
        this.posicionCabezal = posicion;
        repaint(); // Obliga a redibujar el panel para mostrar el movimiento
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulador == null) return;

        Archivo[] disco = simulador.getDisco();
        
        for (int i = 0; i < simulador.getCapacidad(); i++) {
            int x = (i % COLUMNAS) * TAMANO_BLOQUE;
            int y = (i / COLUMNAS) * TAMANO_BLOQUE;

            if (disco[i] == null) {
                g.setColor(Color.WHITE); // Libre
            } else {
                g.setColor(disco[i].getColor()); // Ocupado
            }

            g.fillRect(x, y, TAMANO_BLOQUE, TAMANO_BLOQUE);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, TAMANO_BLOQUE, TAMANO_BLOQUE);
            
            // --- NUEVO DIBUJO: Resaltar el bloque donde está el cabezal ---
            if (i == posicionCabezal) {
                g.setColor(Color.RED); // Lo pintamos de rojo para que destaque
                // Le hacemos un borde más grueso por dentro para que se note la animación
                g.drawRect(x + 1, y + 1, TAMANO_BLOQUE - 2, TAMANO_BLOQUE - 2);
                g.drawRect(x + 2, y + 2, TAMANO_BLOQUE - 4, TAMANO_BLOQUE - 4);
            }
        }
    }
}