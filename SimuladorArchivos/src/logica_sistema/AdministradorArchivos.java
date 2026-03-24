/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica_sistema;

import estructuras.ListaEnlazada;
import sistema_archivos.Archivo;
import sistema_archivos.Carpeta;
import java.awt.Color;

public class AdministradorArchivos {
    private Carpeta raiz;
    private Carpeta carpetaActual;
    private SimuladorSD disco;

    public AdministradorArchivos(SimuladorSD disco) {
        this.disco = disco;
        // Inicializamos la estructura jerárquica con una carpeta raíz
        this.raiz = new Carpeta("root");
        this.carpetaActual = this.raiz;
    }

    // --- MÉTODOS CRUD ---

    // CREAR ARCHIVO [cite: 38, 39]
    public boolean crearArchivo(String nombre, int tamanoBloques, String dueno, Color color) {
        // 1. Verificar si hay bloques libres suficientes en el SD [cite: 36]
        ListaEnlazada<Integer> bloquesLibres = disco.buscarBloquesLibres(tamanoBloques);

        if (bloquesLibres.size() < tamanoBloques) {
            return false; // No hay espacio disponible en el disco [cite: 36]
        }

        // 2. Crear el objeto Archivo [cite: 41]
        Archivo nuevoArchivo = new Archivo(nombre, tamanoBloques, dueno, color);

        // 3. Asignar los bloques en el disco duro mediante asignación encadenada [cite: 14, 41]
        disco.asignarBloques(nuevoArchivo, bloquesLibres);

        // 4. Agregarlo a la carpeta actual para mantener la jerarquía
        carpetaActual.getArchivos().add(nuevoArchivo);

        return true;
    }

    // ELIMINAR ARCHIVO [cite: 46]
    public boolean eliminarArchivo(String nombre) {
        ListaEnlazada<Archivo> archivos = carpetaActual.getArchivos();
        
        for (int i = 0; i < archivos.size(); i++) {
            Archivo arch = archivos.get(i);
            if (arch.getNombre().equals(nombre)) {
                // 1. Liberar los bloques asignados en el SD [cite: 48]
                disco.liberarBloques(arch.getBloquesAsignados());
                
                // 2. Remover el archivo de la carpeta
                archivos.remove(i);
                return true;
            }
        }
        return false; // No se encontró el archivo
    }

    // --- GETTERS ---
    public Carpeta getRaiz() { return raiz; }
    public Carpeta getCarpetaActual() { return carpetaActual; }
    public SimuladorSD getDisco() { return disco; }
}
