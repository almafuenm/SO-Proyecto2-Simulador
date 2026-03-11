/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author alma
 */
public class Cola<T> {
    private ListaEnlazada<T> lista;

    public Cola() {
        this.lista = new ListaEnlazada<>();
    }

    // Agregar a la cola (entra por el final)
    public void encolar(T dato) {
        lista.add(dato);
    }

    // Sacar de la cola (sale el primero que entró)
    public T desencolar() {
        if (lista.isEmpty()) {
            return null;
        }
        T dato = lista.get(0);
        lista.remove(0);
        return dato;
    }

    // Ver el primero sin sacarlo
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
