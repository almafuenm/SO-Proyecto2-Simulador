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

/**
 * Componente gráfico encargado de la representación visual del Disco (SD).
 * Implementa un mapa de bloques para monitorear el estado físico del almacenamiento 
 * y la ubicación en tiempo real del cabezal de lectura/escritura.
 * * Requisito Funcional: Visualización del disco con bloques ocupados y libres[cite: 32].
 */
public class PanelDisco extends JPanel {
    private SimuladorSD simulador;
    private final int TAMANO_BLOQUE = 20; 
    private final int COLUMNAS = 20; 
    
     * Estado del Sistema: Almacena la ubicación física actual del cabezal de disco.
     * Es actualizada dinámicamente por el Animador según la política de planificación.
    private int posicionCabezal = 0; 

    public PanelDisco() {
        // Constructor de la interfaz gráfica
    }

    public void setSimulador(SimuladorSD simulador) {
        this.simulador = simulador;
        repaint(); 
    }

    /**
     * Lógica de Animación: Actualiza la posición del cabezal y fuerza el redibujado 
     * del componente para reflejar el movimiento físico sobre los bloques.
     * * Transición de Estado: El cabezal se mueve de un bloque 'n' a un bloque 'm' 
     * siguiendo la secuencia dictada por algoritmos como SSTF o SCAN[cite: 53].
     */
    public void setPosicionCabezal(int posicion) {
        this.posicionCabezal = posicion;
        repaint(); 
    }

    /**
     * Lógica de Renderizado: Traduce la estructura lineal del disco a un plano bidimensional.
     * 1. Mapeo: Convierte el índice lineal (i) en coordenadas (x, y) de una cuadrícula.
     * 2. Cromática: Asigna colores basados en el dueño del bloque para trazabilidad visual[cite: 33].
     * 3. Resaltado: Dibuja el indicador del cabezal sobre el bloque activo.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulador == null) return;

        Archivo[] disco = simulador.getDisco();
        
        for (int i = 0; i < simulador.getCapacidad(); i++) {
            // Lógica de cuadrícula: Módulo para columna, división para fila
            int x = (i % COLUMNAS) * TAMANO_BLOQUE;
            int y = (i / COLUMNAS) * TAMANO_BLOQUE;

            // Transición Visual: Blanco para bloques libres, color del archivo para ocupados
            if (disco[i] == null) {
                g.setColor(Color.WHITE); 
            } else {
                g.setColor(disco[i].getColor()); 
            }

            g.fillRect(x, y, TAMANO_BLOQUE, TAMANO_BLOQUE);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, TAMANO_BLOQUE, TAMANO_BLOQUE);
            
            // Lógica de Telemetría: Renderiza el indicador rojo de la posición del cabezal
            if (i == posicionCabezal) {
                g.setColor(Color.RED); 
                g.drawRect(x + 1, y + 1, TAMANO_BLOQUE - 2, TAMANO_BLOQUE - 2);
                g.drawRect(x + 2, y + 2, TAMANO_BLOQUE - 4, TAMANO_BLOQUE - 4);
            }
        }
    }
}