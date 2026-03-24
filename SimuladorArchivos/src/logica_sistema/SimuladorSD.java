/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica_sistema;

import sistema_archivos.Archivo;
import estructuras.ListaEnlazada;

public class SimuladorSD {
    private Archivo[] disco; 
    private int capacidad;
    private int posicionCabezal = 0;

    public SimuladorSD(int capacidad) {
        this.capacidad = capacidad;
        // Usamos un arreglo nativo para cumplir la regla de NO usar colecciones de Java
        this.disco = new Archivo[capacidad]; 
    }

    // Busca N bloques libres en el disco y devuelve una ListaEnlazada con sus índices
    public ListaEnlazada<Integer> buscarBloquesLibres(int cantidad) {
        ListaEnlazada<Integer> libres = new ListaEnlazada<>();
        for (int i = 0; i < capacidad; i++) {
            if (disco[i] == null) {
                libres.add(i);
                if (libres.size() == cantidad) break; // Ya encontramos los que necesitamos
            }
        }
        return libres;
    }

    // Ocupa los bloques en el disco y guarda los índices en el archivo (Asignación Encadenada)
    public void asignarBloques(Archivo archivo, ListaEnlazada<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            int index = indices.get(i);
            disco[index] = archivo;
            archivo.getBloquesAsignados().add(index);
        }
    }

    // Libera los bloques cuando se elimina un archivo
    public void liberarBloques(ListaEnlazada<Integer> indices) {
        for (int i = 0; i < indices.size(); i++) {
            disco[indices.get(i)] = null;
        }
    }

    public Archivo[] getDisco() { return disco; }
    public int getCapacidad() { return capacidad; }
    
    public int getPosicionCabezal() { 
    return posicionCabezal; 
}
    public void setPosicionCabezal(int pos) { 
    this.posicionCabezal = pos; 
}
}
