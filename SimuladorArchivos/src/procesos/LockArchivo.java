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
 * Clase que representa un semáforo lógico de sincronización para un archivo específico.
 * Implementa la lógica de exclusión mutua (Mutex) necesaria para gestionar el acceso 
 * concurrente, diferenciando entre permisos de lectura compartida y escritura exclusiva[cite: 13].
 * * Lógica de Diseño: Utiliza el modificador 'synchronized' de Java para garantizar 
 * la atomicidad de las operaciones, evitando condiciones de carrera entre hilos[cite: 67, 69].
 */
public class LockArchivo {
    private String nombreArchivo;
    private int lectores;
    private boolean escribiendo;

    /**
     * Constructor: Inicializa el estado de control para un archivo nuevo.
     * Estado Inicial: El recurso nace en un estado 'UNLOCKED' (Libre), con cero 
     * lectores activos y el indicador de escritura en falso.
     */
    public LockArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.lectores = 0;
        this.escribiendo = false;
    }

    public String getNombreArchivo() { return nombreArchivo; }

    /**
     * Lógica de Adquisición de Lectura (Lock Compartido).
     * Regla de Negocio: Permite el acceso simultáneo a múltiples hilos siempre y 
     * cuando no exista un hilo de escritura activo sobre el recurso[cite: 13].
     * * Transición de Estado: Incrementa el contador de lectores. El archivo pasa 
     * a un estado de 'LECTURA_ACTIVA'.
     * @return true si el acceso fue concedido; false si hay una escritura en curso.
     */
    public synchronized boolean adquirirLockLectura() {
        if (escribiendo) {
            return false; 
        }
        lectores++;
        return true;
    }

    /**
     * Lógica de Liberación de Lectura.
     * Transición de Estado: Decrementa el contador de hilos concurrentes. 
     * Cuando el contador llega a cero, el archivo transita de nuevo al estado 
     * 'LIBRE', permitiendo que los escritores puedan competir por el recurso.
     */
    public synchronized void liberarLockLectura() {
        if (lectores > 0) {
            lectores--;
        }
    }

    /**
     * Lógica de Adquisición de Escritura (Lock Exclusivo).
     * Regla de Negocio: Un hilo solo puede modificar el archivo si no existen 
     * lectores activos ni otros hilos escribiendo en ese instante[cite: 13].
     * * Transición de Estado: El archivo entra en un estado de 'BLOQUEO_TOTAL'. 
     * Ninguna otra solicitud (lectura o escritura) será procesada hasta que se libere.
     * @return true si el acceso exclusivo fue concedido; false si el recurso está ocupado.
     */
    public synchronized boolean adquirirLockEscritura() {
        if (lectores > 0 || escribiendo) {
            return false; 
        }
        escribiendo = true;
        return true;
    }

    /**
     * Lógica de Liberación de Escritura.
     * Transición de Estado: Restablece el indicador de escritura a falso. 
     * El recurso transita al estado 'LIBRE', notificando implícitamente al 
     * Gestor de Procesos que las solicitudes en espera pueden ser reevaluadas.
     */
    public synchronized void liberarLockEscritura() {
        escribiendo = false;
    }
}