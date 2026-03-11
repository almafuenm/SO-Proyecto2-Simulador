/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author alma
 */
public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Agregar un elemento al final de la lista
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

    // Obtener un elemento por su índice
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

    // Eliminar un elemento por su índice
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
}
