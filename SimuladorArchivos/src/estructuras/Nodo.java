/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author alma
 */

/**
 * Clase genérica que representa la unidad básica (átomo) de las estructuras de datos.
 * En el contexto del sistema de archivos, cada Nodo funciona como un "Bloque" físico 
 * o lógico que contiene información y un puntero hacia el siguiente elemento.
 * * Lógica de Diseño: Implementa la base de la Asignación Encadenada exigida en el proyecto,
 * permitiendo que archivos y colas de procesos crezcan dinámicamente en memoria.
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> siguiente;

    /**
     * Constructor: Inicializa un nodo con un dato específico (ej. un Bloque de disco 
     * o un objeto Proceso). 
     * Estado Inicial: El puntero 'siguiente' nace como null, representando el final 
     * momentáneo de una cadena.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Lógica de Acceso: Recupera la información almacenada (Payload).
     * En la lectura de archivos, este método devuelve el contenido del bloque actual.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Lógica de Modificación: Actualiza el contenido del nodo. 
     * Útil durante el ordenamiento de algoritmos de planificación (como SSTF) 
     * para intercambiar datos entre nodos sin mover los punteros físicos.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Transición de Estado: Permite navegar hacia el siguiente eslabón de la estructura.
     * Es la base del recorrido secuencial necesario para el JTree y la lectura de archivos.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Lógica de Enlace: Establece la conexión física entre dos nodos.
     * Este método es el que "encadena" los bloques del disco. Al ejecutarlo, 
     * se cambia el estado del nodo actual de "Final de lista" a "Nodo intermedio".
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}