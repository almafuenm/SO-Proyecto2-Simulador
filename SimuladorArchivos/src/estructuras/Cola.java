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
 * 
 * Clase genérica que implementa una estructura de datos tipo FIFO (First-In, First-Out).
 * Esta implementación es fundamental para la gestión de la cola de procesos (listos y bloqueados) 
 * y la cola de peticiones de E/S del disco, asegurando que las solicitudes se procesen 
 * en el orden en que fueron generadas.
 * * Requisito Técnico: Sustituye el uso de java.util.Queue.
 */
public class Cola<T> {
    private ListaEnlazada<T> lista;

    public Cola() {
        this.lista = new ListaEnlazada<>();
    }

    /**
     * Lógica de Inserción: Agrega un nuevo elemento al final de la estructura.
     * Transición de Estado: La cola pasa de un estado 'n' a 'n+1'. Si estaba vacía, 
     * el nuevo elemento se convierte automáticamente en el frente.
     */
    public void encolar(T dato) {
        lista.add(dato);
    }

    /**
     * Lógica de Extracción (FIFO): Recupera y elimina el elemento que ha permanecido 
     * más tiempo en la cola (posición 0).
     * Transición de Estado: El segundo elemento en la lista pasa a ser el nuevo frente. 
     * Si la cola tenía un solo elemento, esta transita a un estado vacío (isEmpty = true).
     */
    public T desencolar() {
        if (lista.isEmpty()) {
            return null;
        }
        T dato = lista.get(0);
        lista.remove(0);
        return dato;
    }

    /**
     * Lógica de Observación: Permite consultar el elemento en el frente sin 
     * modificar la estructura ni los estados de los procesos contenidos.
     */
    public T frente() {
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }
}