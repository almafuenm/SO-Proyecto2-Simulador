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

public class GestorProcesos {
    private Cola<Proceso> colaListos;
    private Cola<Proceso> colaBloqueados;
    private ListaEnlazada<Proceso> procesosTerminados;
    private Proceso procesoEnEjecucion;

    public GestorProcesos() {
        this.colaListos = new Cola<>();
        this.colaBloqueados = new Cola<>();
        this.procesosTerminados = new ListaEnlazada<>();
        this.procesoEnEjecucion = null;
    }

    // Agregar un proceso nuevo al sistema y pasarlo a LISTO
    public void agregarProceso(Proceso p) {
        p.setEstado(Proceso.Estado.LISTO);
        colaListos.encolar(p);
    }

    // Simula el Dispatcher: toma el primer proceso de la cola y lo ejecuta
    public void despachar() {
        if (procesoEnEjecucion == null && !colaListos.isEmpty()) {
            procesoEnEjecucion = colaListos.desencolar();
            procesoEnEjecucion.setEstado(Proceso.Estado.EJECUTANDO);
        }
    }

    // Manda el proceso actual a la cola de bloqueados (ej. esperando que un Lock se libere)
    public void bloquearProcesoActual() {
        if (procesoEnEjecucion != null) {
            procesoEnEjecucion.setEstado(Proceso.Estado.BLOQUEADO);
            colaBloqueados.encolar(procesoEnEjecucion);
            procesoEnEjecucion = null;
        }
    }

    // Termina el proceso actual
    public void terminarProcesoActual() {
        if (procesoEnEjecucion != null) {
            procesoEnEjecucion.setEstado(Proceso.Estado.TERMINADO);
            procesosTerminados.add(procesoEnEjecucion);
            procesoEnEjecucion = null;
        }
    }

    // Getters para que la Interfaz Gráfica pueda leer las colas después
    public Cola<Proceso> getColaListos() { return colaListos; }
    public Cola<Proceso> getColaBloqueados() { return colaBloqueados; }
    public ListaEnlazada<Proceso> getProcesosTerminados() { return procesosTerminados; }
    public Proceso getProcesoEnEjecucion() { return procesoEnEjecucion; }
}
