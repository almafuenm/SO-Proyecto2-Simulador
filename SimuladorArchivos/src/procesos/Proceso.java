/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesos;

/**
 *
 * @author alma
 */

/**
 * Representación lógica de un hilo de ejecución (Thread) dentro del simulador.
 * Esta clase funciona como el PCB (Process Control Block), almacenando toda la 
 * información necesaria para que el Kernel gestione la concurrencia y la 
 * telemetría del sistema.
 * * Lógica de Diseño: Implementa un modelo de 5 estados para reflejar fielmente 
 * el comportamiento de un proceso en un sistema operativo real.
 */
public class Proceso {
    /**
     * Ciclo de Vida del Proceso:
     * - NUEVO: Recién instanciado, no ha entrado en la cola de planificación.
     * - LISTO: En espera de que el Dispatcher le asigne tiempo de CPU.
     * - EJECUTANDO: Posee el control del procesador y realiza operaciones.
     * - BLOQUEADO: Esperando por un recurso (ej. un Lock de archivo o E/S).
     * - TERMINADO: Finalizó su ejecución y liberó recursos.
     */
    public enum Estado {
        NUEVO, LISTO, EJECUTANDO, BLOQUEADO, TERMINADO
    }

    /**
     * Definición de Carga de Trabajo (Payload):
     * Clasifica el tipo de operación de E/S que el proceso intentará realizar 
     * sobre el sistema de archivos.
     */
    public enum Operacion {
        CREAR, LEER, ACTUALIZAR, ELIMINAR
    }

    // Lógica de Identificación: Generador estático para garantizar IDs únicos 
    // y correlativos sin colisiones entre hilos.
    private static int contadorId = 1; 
    
    private int id;
    private Estado estado;
    private Operacion operacion;
    private String nombreArchivo;
    private int tamañoBloques; 

    /**
     * Constructor: Inicializa la unidad de trabajo.
     * Transición de Estado: El proceso nace en estado 'NUEVO'.
     * Lógica: Se le asigna un ID único y se definen los parámetros de la 
     * operación de E/S que el usuario solicitó.
     */
    public Proceso(Operacion operacion, String nombreArchivo, int tamañoBloques) {
        this.id = contadorId++;
        this.estado = Estado.NUEVO;
        this.operacion = operacion;
        this.nombreArchivo = nombreArchivo;
        this.tamañoBloques = tamañoBloques;
    }

    public int getId() { return id; }
    
    public Estado getEstado() { return estado; }

    /**
     * Transición de Estados: Permite al GestorProcesos mover el proceso a través 
     * de las diferentes colas (Listos, Bloqueados) según la disponibilidad de recursos.
     */
    public void setEstado(Estado estado) { this.estado = estado; }
    
    public Operacion getOperacion() { return operacion; }
    
    public String getNombreArchivo() { return nombreArchivo; }
    
    public int getTamañoBloques() { return tamañoBloques; }

    /**
     * Lógica de Telemetría: Facilita la representación textual del proceso 
     * en el Dashboard y en los logs de auditoría del sistema.
     */
    @Override
    public String toString() {
        return "P" + id + " [" + operacion + " " + nombreArchivo + "] - " + estado;
    }
}