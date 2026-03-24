/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica_sistema;

import estructuras.ListaEnlazada;
import sistema_archivos.Archivo;
import sistema_archivos.Carpeta;
import java.awt.Color;

/**
 * Clase núcleo encargada de la gestión lógica y física del sistema de archivos.
 * Actúa como el controlador principal para las operaciones CRUD, asegurando la 
 * consistencia entre la estructura jerárquica (carpetas/archivos) y el 
 * almacenamiento secundario (SD) [cite: 35-36].
 * * Lógica de Diseño: Implementa una estructura de árbol para la jerarquía de 
 * directorios y utiliza el SimuladorSD para la persistencia de datos.
 */
public class AdministradorArchivos {
    private Carpeta raiz;
    private Carpeta carpetaActual;
    private SimuladorSD disco;

    /**
     * Constructor: Inicializa el entorno operativo.
     * Estado Inicial: Crea un directorio raíz ("root") y posiciona al sistema 
     * en esta ubicación de forma predeterminada[cite: 37].
     */
    public AdministradorArchivos(SimuladorSD disco) {
        this.disco = disco;
        this.raiz = new Carpeta("root");
        this.carpetaActual = this.raiz;
    }

    // --- MÉTODOS CRUD ---

    /**
     * Algoritmo de Creación de Archivos.
     * 1. Validación de Recursos: Consulta al disco para verificar la disponibilidad 
     * de bloques suficientes antes de proceder[cite: 36].
     * 2. Instanciación: Crea el objeto Archivo con metadatos (nombre, dueño, color).
     * 3. Persistencia Física: Ejecuta la 'Asignación Encadenada' en el disco, vinculando 
     * los bloques lógicos con direcciones físicas del SD[cite: 14, 41].
     * 4. Actualización Jerárquica: Inserta el archivo en la lista de la carpeta actual.
     * * Transición de Estado: El disco transita de un estado de mayor disponibilidad 
     * a uno de mayor ocupación; la carpeta actual actualiza su lista de nodos hijos.
     */
    public boolean crearArchivo(String nombre, int tamanoBloques, String dueno, Color color) {
        ListaEnlazada<Integer> bloquesLibres = disco.buscarBloquesLibres(tamanoBloques);

        if (bloquesLibres.size() < tamanoBloques) {
            return false; 
        }

        Archivo nuevoArchivo = new Archivo(nombre, tamanoBloques, dueno, color);
        disco.asignarBloques(nuevoArchivo, bloquesLibres);
        carpetaActual.getArchivos().add(nuevoArchivo);

        return true;
    }

    /**
     * Algoritmo de Eliminación de Archivos.
     * 1. Búsqueda: Recorre secuencialmente la lista de archivos de la carpeta actual 
     * buscando coincidencia por nombre.
     * 2. Liberación de Hardware: Invoca al disco para marcar los bloques previamente 
     * ocupados como 'libres', permitiendo su reutilización futura[cite: 48].
     * 3. Limpieza Lógica: Remueve el nodo del archivo de la estructura jerárquica.
     * * Transición de Estado: El sistema recupera bloques disponibles en el SD y 
     * la estructura de directorios se simplifica al eliminar el nodo correspondiente.
     */
    public boolean eliminarArchivo(String nombre) {
        ListaEnlazada<Archivo> archivos = carpetaActual.getArchivos();
        
        for (int i = 0; i < archivos.size(); i++) {
            Archivo arch = archivos.get(i);
            if (arch.getNombre().equals(nombre)) {
                disco.liberarBloques(arch.getBloquesAsignados());
                archivos.remove(i);
                return true;
            }
        }
        return false; 
    }

    // --- GETTERS ---
    public Carpeta getRaiz() { return raiz; }
    public Carpeta getCarpetaActual() { return carpetaActual; }
    public SimuladorSD getDisco() { return disco; }
}