/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesos;

/**
 *
 * @author alma
 */

import estructuras.Cola;
import estructuras.ListaEnlazada;

/**
 * Clase núcleo encargada de la administración del ciclo de vida de los procesos.
 * Implementa la lógica del Planificador de Corto Plazo y el Despachador (Dispatcher), 
 * gestionando el tránsito de los procesos entre las diferentes colas del sistema 
 * (Listos, Bloqueados y Terminados) [cite: 40, 56].
 * * Lógica de Diseño: Utiliza estructuras de datos tipo 'Cola' para garantizar un 
 * procesamiento ordenado y justo de las peticiones de los usuarios.
 */
public class GestorProcesos {
    private Cola<Proceso> colaListos;
    private Cola<Proceso> colaBloqueados;
    private ListaEnlazada<Proceso> procesosTerminados;
    private Proceso procesoEnEjecucion;

    /**
     * Constructor: Inicializa las estructuras de control del núcleo.
     * Estado Inicial: El sistema arranca con todas las colas vacías y sin ningún 
     * proceso asignado al procesador (procesoEnEjecucion = null).
     */
    public GestorProcesos() {
        this.colaListos = new Cola<>();
        this.colaBloqueados = new Cola<>();
        this.procesosTerminados = new ListaEnlazada<>();
        this.procesoEnEjecucion = null;
    }

    /**
     * Lógica de Admisión de Procesos.
     * Transición de Estado: El proceso pasa de un estado inicial 'NUEVO' al estado 'LISTO'.
     * Al ser encolado, el proceso queda a la espera de que el Dispatcher le asigne 
     * tiempo de ejecución en el procesador[cite: 57].
     */
    public void agregarProceso(Proceso p) {
        p.setEstado(Proceso.Estado.LISTO);
        colaListos.encolar(p);
    }

    /**
     * Algoritmo de Despacho (Dispatcher).
     * Lógica: Si el procesador está ocioso, extrae el primer proceso de la cola de listos 
     * siguiendo una política FIFO (First-In, First-Out).
     * Transición de Estado: El proceso seleccionado cambia su estado de 'LISTO' a 'EJECUTANDO'.
     * En este punto, el proceso toma el control para realizar operaciones de E/S o 
     * solicitar locks de archivos[cite: 58, 108].
     */
    public void despachar() {
        if (procesoEnEjecucion == null && !colaListos.isEmpty()) {
            procesoEnEjecucion = colaListos.desencolar();
            procesoEnEjecucion.setEstado(Proceso.Estado.EJECUTANDO);
        }
    }

    /**
     * Lógica de Bloqueo de Procesos.
     * Ocurre cuando un proceso en ejecución solicita un recurso no disponible 
     * (ej. un lock de escritura ocupado por otro usuario) .
     * Transición de Estado: El proceso pasa de 'EJECUTANDO' a 'BLOQUEADO' y es 
     * movido a la cola de espera, liberando el procesador para que el Dispatcher 
     * pueda asignar la CPU a otro proceso listo.
     */
    public void bloquearProcesoActual() {
        if (procesoEnEjecucion != null) {
            procesoEnEjecucion.setEstado(Proceso.Estado.BLOQUEADO);
            colaBloqueados.encolar(procesoEnEjecucion);
            procesoEnEjecucion = null;
        }
    }

    /**
     * Lógica de Finalización de Procesos.
     * Se ejecuta cuando la operación de E/S ha concluido exitosamente y el proceso 
     * ya no requiere recursos del sistema.
     * Transición de Estado: El proceso cambia a 'TERMINADO', se registra en el 
     * historial de procesos finalizados y el procesador queda libre nuevamente.
     */
    public void terminarProcesoActual() {
        if (procesoEnEjecucion != null) {
            procesoEnEjecucion.setEstado(Proceso.Estado.TERMINADO);
            procesosTerminados.add(procesoEnEjecucion);
            procesoEnEjecucion = null;
        }
    }

    // Getters para la sincronización con la Interfaz Gráfica (Dashboard de Telemetría)
    public Cola<Proceso> getColaListos() { return colaListos; }
    public Cola<Proceso> getColaBloqueados() { return colaBloqueados; }
    public ListaEnlazada<Proceso> getProcesosTerminados() { return procesosTerminados; }
    public Proceso getProcesoEnEjecucion() { return procesoEnEjecucion; }
}