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

public class GestorLocks {
    private ListaEnlazada<LockArchivo> locksActivos;

    public GestorLocks() {
        this.locksActivos = new ListaEnlazada<>();
    }

    // Busca si el archivo ya tiene un lock creado, si no, lo crea
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

    public boolean solicitarLectura(String nombreArchivo) {
        return obtenerOCrearLock(nombreArchivo).adquirirLockLectura();
    }

    public void liberarLectura(String nombreArchivo) {
        obtenerOCrearLock(nombreArchivo).liberarLockLectura();
    }

    public boolean solicitarEscritura(String nombreArchivo) {
        return obtenerOCrearLock(nombreArchivo).adquirirLockEscritura();
    }

    public void liberarEscritura(String nombreArchivo) {
        obtenerOCrearLock(nombreArchivo).liberarLockEscritura();
    }
}
