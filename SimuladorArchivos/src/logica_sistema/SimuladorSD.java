/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica_sistema;

import sistema_archivos.Archivo;
import estructuras.ListaEnlazada;

/**
 * Clase que emula el comportamiento físico del Almacenamiento Secundario (SD).
 * Gestiona un espacio lineal de bloques donde se almacenan las referencias de los archivos,
 * permitiendo simular la persistencia y la ubicación física del cabezal de lectura/escritura [cite: 41-42].
 * * Lógica de Diseño: Utiliza un arreglo nativo para representar los sectores del disco, 
 * facilitando la implementación de la Asignación Encadenada exigida en el proyecto[cite: 14].
 */
public class SimuladorSD {
    private Archivo[] disco; 
    private int capacidad;
    private int posicionCabezal = 0;

    /**
     * Constructor: Inicializa la superficie del disco simulado.
     * Estado Inicial: Todos los bloques se establecen como null (vacíos), listos 
     * para recibir operaciones de escritura del administrador de archivos.
     */
    public SimuladorSD(int capacidad) {
        this.capacidad = capacidad;
        this.disco = new Archivo[capacidad]; 
    }

    /**
     * Algoritmo de Búsqueda de Espacio: Realiza un recorrido lineal sobre el arreglo 
     * para identificar bloques disponibles.
     * Lógica de Selección: Detiene la búsqueda en cuanto se satisface la cantidad 
     * exacta solicitada por el proceso de creación.
     * Transición de Estado: Devuelve una lista de índices que representan las 
     * futuras coordenadas físicas donde se escribirá el archivo.
     */
    public ListaEnlazada<Integer> buscarBloquesLibres(int cantidad) {
        ListaEnlazada<Integer> libres = new ListaEnlazada<>();
        for (int i = 0; i < capacidad; i++) {
            if (disco[i] == null) {
                libres.add(i);
                if (libres.size() == cantidad) break; 
            }
        }
        return libres;
    }

    /**
     * Algoritmo de Escritura (Asignación Encadenada): Vincula físicamente el 
     * objeto Archivo con los bloques del disco[cite: 41].
     * Lógica de Enlace: No solo escribe la referencia en el arreglo 'disco', sino que 
     * sincroniza el objeto Archivo para que este mantenga su propia lista de bloques asignados.
     * Transición de Estado: Los bloques seleccionados pasan de un estado null 
     * (Libre) a un estado ocupado por un objeto Archivo específico.
     */
    public void asignarBloques(Archivo archivo, ListaEnlazada<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            disco[index] = archivo;
            archivo.getBloquesAsignados().add(index);
        }
    }

    /**
     * Algoritmo de Liberación: Restaura la disponibilidad de los bloques.
     * Lógica de Borrado: Elimina las referencias del archivo en el arreglo físico, 
     * permitiendo que el sistema de archivos vuelva a considerar estos índices como vacíos.
     * Transición de Estado: Los índices indicados regresan al estado null.
     */
    public void liberarBloques(ListaEnlazada<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            disco[indices.get(i)] = null;
        }
    }

    public Archivo[] getDisco() { return disco; }
    public int getCapacidad() { return capacidad; }
    
    /**
     * Telemetría del Cabezal: Gestiona la posición física actual en el disco.
     * Esta variable es el objetivo principal de los algoritmos de planificación (SSTF, SCAN, etc.), 
     * determinando el "costo" de desplazamiento en cada operación de E/S.
     */
    public int getPosicionCabezal() { 
        return posicionCabezal; 
    }
    
    public void setPosicionCabezal(int pos) { 
        this.posicionCabezal = pos; 
    }
}