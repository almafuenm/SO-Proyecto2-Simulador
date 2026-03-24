/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animacion;

import javax.swing.Timer;
import gui.PanelDisco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import estructuras.ListaEnlazada;


/**
 * Motor de animación encargado de la representación temporal del movimiento del cabezal.
 * Utiliza un temporizador asíncrono para emular el retardo físico (seek time) que 
 * ocurre en un disco real cuando el cabezal se desplaza entre cilindros.
 * * Lógica de Diseño: Implementa el uso de Timers para mantener la responsividad 
 * de la GUI mientras se ejecuta la simulación de hardware[cite: 69].
 */
public class AnimadorCabezal {
    
    private Timer timer;
    private PanelDisco panelDisco;
    private ListaEnlazada<Integer> rutaBloques;
    private int indiceActual;

    /**
     * Constructor: Vincula el animador con el componente visual del disco.
     */
    public AnimadorCabezal(PanelDisco panelDisco) {
        this.panelDisco = panelDisco;
    }

    /**
     * Algoritmo de Animación de Ruta.
     * 1. Preparación: Recibe la secuencia de bloques calculada por el planificador 
     * (FIFO, SSTF, etc.) e inicializa el puntero de ejecución.
     * 2. Ejecución Asíncrona: Inicia un Timer que actúa como un hilo secundario, 
     * evitando que el hilo principal de la interfaz se congele.
     * 3. Ciclo de Movimiento: Cada 500ms, el cabezal "viaja" a la siguiente dirección física.
     * 4. Finalización: Al agotar la ruta, detiene el reloj y notifica al usuario 
     * que la operación de E/S ha concluido.
     * * Transición de Estado: El simulador pasa de un estado 'Planificando' a un estado 
     * 'En Movimiento', actualizando la posición roja en el PanelDisco paso a paso.
     */
    public void animarRuta(ListaEnlazada<Integer> ruta) {
        this.rutaBloques = ruta;
        this.indiceActual = 0;

        // Lógica de Retardo: Simula la latencia mecánica del disco (0.5 segundos por bloque)
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Condición de carrera: Mientras existan bloques pendientes en la ruta
                if (indiceActual < rutaBloques.size()) {
                    
                    int bloqueDestino = rutaBloques.get(indiceActual);
                    
                    // Sincronización Visual: Actualiza el estado del cabezal en el panel
                    panelDisco.setPosicionCabezal(bloqueDestino); 
                    panelDisco.repaint();
                    
                    indiceActual++;
                } else {
                    // Estado Final: Limpieza de recursos y notificación de éxito
                    timer.stop(); 
                    javax.swing.JOptionPane.showMessageDialog(null, "¡Simulación de disco terminada!");
                }
            }
        });
        
        timer.start(); 
    }
}