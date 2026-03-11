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
import java.awt.Color;

public class Archivo {
    private String nombre;
    private int tamañoBloques;
    private String dueño;
    private Color color; // Requerimiento: diferenciar archivos por colores
    private ListaEnlazada<Integer> bloquesAsignados;

    public Archivo(String nombre, int tamaño, String dueño, Color color) {
        this.nombre = nombre;
        this.tamañoBloques = tamaño;
        this.dueño = dueño;
        this.color = color;
        this.bloquesAsignados = new ListaEnlazada<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getTamañoBloques() { return tamañoBloques; }
    public String getDueño() { return dueño; }
    public Color getColor() { return color; }
    public ListaEnlazada<Integer> getBloquesAsignados() { return bloquesAsignados; }
    
    @Override
    public String toString() { return nombre; } // Necesario para que el JTree muestre el nombre
}
