/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema_archivos;

/**
 *
 * @author alma
 */

import estructuras.ListaEnlazada;

public class Carpeta {
    private String nombre;
    private ListaEnlazada<Carpeta> subcarpetas;
    private ListaEnlazada<Archivo> archivos;

    public Carpeta(String nombre) {
        this.nombre = nombre;
        this.subcarpetas = new ListaEnlazada<>();
        this.archivos = new ListaEnlazada<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public ListaEnlazada<Carpeta> getSubcarpetas() { return subcarpetas; }
    public ListaEnlazada<Archivo> getArchivos() { return archivos; }

    @Override
    public String toString() { return nombre; }
}
