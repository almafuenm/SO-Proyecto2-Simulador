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
 * Implementación de una Lista Enlazada Simple Genérica.
 * Esta clase constituye el motor de almacenamiento del simulador, permitiendo la 
 * "Asignación Encadenada" de bloques en el disco (SD). Cada archivo en el sistema 
 * se representa internamente como una instancia de esta lista.
 * * Requisito Técnico: Sustituye a java.util.ArrayList y permite gestionar la 
 * jerarquía de archivos y el ordenamiento de peticiones de E/S.
 */
public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    /**
     * Lógica de Inserción: Agrega un elemento al final de la cadena de nodos.
     * Si la lista está vacía, establece la cabeza; de lo contrario, recorre 
     * los punteros hasta el último nodo para enlazar el nuevo dato.
     * Transición de Estado: Incrementa el tamaño de la estructura y actualiza 
     * el enlace del último nodo existente.
     */
    public void add(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamaño++;
    }

    /**
     * Lógica de Acceso: Realiza un recorrido secuencial (O(n)) desde la cabeza 
     * hasta alcanzar el índice solicitado.
     * Es crucial para la lectura de archivos y la visualización de datos en el JTable.
     */
    public T get(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * Lógica de Eliminación: Desvincula un nodo de la cadena mediante la 
     * actualización de punteros.
     * Transición de Estado: Al eliminar un nodo intermedio, el nodo 'anterior' 
     * apunta directamente al 'siguiente' del nodo eliminado, liberando la referencia 
     * para el recolector de basura de Java.
     */
    public void remove(int index) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (index == 0) {
            cabeza = cabeza.getSiguiente();
        } else {
            Nodo<T> anterior = cabeza;
            for (int i = 0; i < index - 1; i++) {
                anterior = anterior.getSiguiente();
            }
            Nodo<T> aEliminar = anterior.getSiguiente();
            anterior.setSiguiente(aEliminar.getSiguiente());
        }
        tamaño--;
    }

    public int size() {
        return tamaño;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }
    
    /**
     * Lógica de Modificación: Actualiza el dato contenido en un nodo específico.
     * Este método es vital para los algoritmos de planificación de disco (SSTF, SCAN), 
     * ya que permite reordenar las peticiones en la cola de E/S mediante intercambios 
     * de valores (sort).
     */
    public void set(int index, T valor) {
        if (index < 0 || index >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < index; i++) {
            actual = actual.getSiguiente();
        }
        actual.setDato(valor);
    }
}