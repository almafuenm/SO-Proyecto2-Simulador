/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesos;

/**
 *
 * @author alma
 */

import estructuras.ListaEnlazada;

/**
 * Componente encargado de la administración de accesos concurrentes a los recursos del sistema.
 * Implementa una política de exclusión mutua basada en el modelo de Lectores-Escritores, 
 * asegurando que las operaciones críticas sobre los archivos no generen inconsistencias[cite: 66, 108].
 * * Lógica de Diseño: Mantiene un registro dinámico de 'Locks' por archivo para gestionar 
 * el acceso compartido y exclusivo de forma independiente para cada nodo del sistema.
 */
public class GestorLocks {
    private ListaEnlazada<LockArchivo> locksActivos;

    public GestorLocks() {
        this.locksActivos = new ListaEnlazada<>();
    }

    /**
     * Lógica de Búsqueda y Creación (Lazy Initialization).
     * El gestor busca en su lista interna si ya existe un objeto de control para el archivo solicitado.
     * 1. Búsqueda: Recorre la lista de locks activos comparando identificadores (nombres).
     * 2. Instanciación: Si el archivo es nuevo o no tiene restricciones previas, crea un nuevo 
     * LockArchivo y lo integra al registro.
     * * Transición de Estado: El sistema pasa de no tener control sobre un recurso a 
     * establecer una estructura de monitoreo para sus futuros accesos.
     */
    private LockArchivo obtenerOCrearLock(String nombreArchivo) {
        for (int i = 0; i < locksActivos.size(); i++) {
            if (locksActivos.get(i).getNombreArchivo().equals(nombreArchivo)) {
                return locksActivos.get(i);
            }
        }
        LockArchivo nuevoLock = new LockArchivo(nombreArchivo);
        locksActivos.add(nuevoLock);
        return nuevoLock;
    }

    /**
     * Lógica de Acceso Compartido (Lectura).
     * Permite que un hilo solicite permiso para leer un archivo. 
     * Transición de Estado: Si no hay un escritor activo, el estado del lock permite 
     * múltiples lectores simultáneos, optimizando el rendimiento del simulador.
     */
    public boolean solicitarLectura(String nombreArchivo) {
        return obtenerOCrearLock(nombreArchivo).adquirirLockLectura();
    }

    /**
     * Lógica de Liberación de Lectura.
     * Actualiza el conteo de hilos lectores activos sobre el recurso. 
     * Transición de Estado: Si es el último lector en salir, el archivo queda 
     * disponible para una futura solicitud de escritura exclusiva.
     */
    public void liberarLectura(String nombreArchivo) {
        obtenerOCrearLock(nombreArchivo).liberarLockLectura();
    }

    /**
     * Lógica de Acceso Exclusivo (Escritura).
     * Gestiona la solicitud de un hilo para modificar un archivo (CREATE, UPDATE, DELETE).
     * Transición de Estado: El lock transita a un estado de 'Exclusividad Total'. Si existen 
     * lectores o un escritor activo, la solicitud falla o se bloquea para evitar la corrupción de datos.
     */
    public boolean solicitarEscritura(String nombreArchivo) {
        return obtenerOCrearLock(nombreArchivo).adquirirLockEscritura();
    }

    /**
     * Lógica de Liberación de Escritura.
     * Restaura la disponibilidad del archivo para cualquier tipo de operación.
     * Transición de Estado: El recurso vuelve a un estado 'Libre' (Unlocked), permitiendo 
     * que la cola de procesos pendientes reanude su ejecución.
     */
    public void liberarEscritura(String nombreArchivo) {
        obtenerOCrearLock(nombreArchivo).liberarLockEscritura();
    }
}