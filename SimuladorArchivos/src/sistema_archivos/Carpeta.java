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

/**
 * Clase que representa un Directorio o Carpeta dentro del sistema de archivos.
 * Actúa como un contenedor de nivel superior que permite organizar los recursos 
 * de forma jerárquica, funcionando como los nodos internos de la estructura de árbol.
 * * Lógica de Diseño: Implementa una estructura recursiva donde cada instancia 
 * mantiene colecciones de objetos tanto de tipo 'Archivo' como de su propia clase 'Carpeta'.
 */
public class Carpeta {
    private String nombre;
    private ListaEnlazada<Carpeta> subcarpetas;
    private ListaEnlazada<Archivo> archivos;

    /**
     * Constructor: Inicializa un nuevo directorio vacío.
     * Estado Inicial: La carpeta nace sin contenido. Se instancian las listas 
     * enlazadas personalizadas para asegurar que la gestión de memoria sea dinámica 
     * y cumpla con las restricciones de no usar colecciones estándar de Java.
     */
    public Carpeta(String nombre) {
        this.nombre = nombre;
        this.subcarpetas = new ListaEnlazada<>();
        this.archivos = new ListaEnlazada<>();
    }

    // --- Getters y Setters ---

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Acceso a la Jerarquía: Devuelve la lista de subdirectorios contenidos.
     * Es la base para la navegación entre niveles del sistema de archivos (CD command).
     */
    public ListaEnlazada<Carpeta> getSubcarpetas() { return subcarpetas; }

    /**
     * Acceso a los Recursos: Devuelve la lista de archivos almacenados en este nivel.
     * Utilizado por el 'AdministradorArchivos' para realizar búsquedas, 
     * creaciones o eliminaciones (CRUD).
     */
    public ListaEnlazada<Archivo> getArchivos() { return archivos; }

    /**
     * Lógica de Representación: Retorna el nombre del directorio.
     * Fundamental para que el componente JTree de la interfaz gráfica pueda 
     * renderizar los nodos de las carpetas correctamente en el explorador de archivos.
     */
    @Override
    public String toString() { return nombre; }
}