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

/**
 * Clase que representa la entidad lógica de un Archivo en el sistema.
 * Funciona como el descriptor de recursos, almacenando tanto los metadatos 
 * de usuario como la estructura física de sus datos en el disco simulado.
 * * Lógica de Asignación: Implementa la 'Asignación Encadenada' mediante una 
 * ListaEnlazada que almacena los índices de los bloques ocupados en el SD.
 */
public class Archivo {
    private String nombre;
    private int tamañoBloques;
    private String dueño;
    private Color color; // Requisito Visual: Permite identificar fragmentación en el disco.
    private ListaEnlazada<Integer> bloquesAsignados;

    /**
     * Constructor: Crea una nueva entrada en el sistema de archivos.
     * Estado Inicial: El archivo nace con metadatos definidos, pero su lista 
     * de 'bloquesAsignados' está vacía hasta que el Administrador ejecute 
     * la lógica de escritura en el SimuladorSD.
     */
    public Archivo(String nombre, int tamaño, String dueño, Color color) {
        this.nombre = nombre;
        this.tamañoBloques = tamaño;
        this.dueño = dueño;
        this.color = color;
        this.bloquesAsignados = new ListaEnlazada<>();
    }

    // --- Getters y Setters ---

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public int getTamañoBloques() { return tamañoBloques; }
    public String getDueño() { return dueño; }
    public Color getColor() { return color; }

    /**
     * Acceso a la Estructura Física: Devuelve la cadena de bloques que 
     * componen el archivo. Esta lista es fundamental para que el 
     * 'AnimadorCabezal' conozca la ruta exacta que debe recorrer el disco.
     */
    public ListaEnlazada<Integer> getBloquesAsignados() { return bloquesAsignados; }
    
    /**
     * Lógica de Representación: Retorna el nombre del archivo.
     * Es esencial para la integración con el JTree, permitiendo que el 
     * componente gráfico renderice el nodo correctamente en la jerarquía.
     */
    @Override
    public String toString() { return nombre; } 
}