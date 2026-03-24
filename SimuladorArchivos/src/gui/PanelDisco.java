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
    private final int TAMANO_BLOQUE = 20; // Tamaño de cada cuadrito en píxeles
    private final int COLUMNAS = 20; // Cuántos bloques por fila

    public PanelDisco() {
        // Constructor vacío para que NetBeans lo reconozca en la paleta
    }

    // Vinculamos la interfaz con la lógica
    public void setSimulador(SimuladorSD simulador) {
        this.simulador = simulador;
        repaint(); // Forzamos a que se vuelva a dibujar la cuadrícula
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulador == null) return;

        Archivo[] disco = simulador.getDisco();
        
        // Dibujamos el arreglo simulando una cuadrícula 2D
        for (int i = 0; i < simulador.getCapacidad(); i++) {
            // Calcular coordenadas X y Y
            int x = (i % COLUMNAS) * TAMANO_BLOQUE;
            int y = (i / COLUMNAS) * TAMANO_BLOQUE;

            if (disco[i] == null) {
                g.setColor(Color.WHITE); // Bloque Libre
            } else {
                g.setColor(disco[i].getColor()); // Bloque Ocupado
            }

            // Dibujar el bloque relleno y luego su borde negro
            g.fillRect(x, y, TAMANO_BLOQUE, TAMANO_BLOQUE);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, TAMANO_BLOQUE, TAMANO_BLOQUE);
        }
    }
}