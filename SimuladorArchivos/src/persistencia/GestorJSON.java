/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import estructuras.ListaEnlazada; 
import logica_sistema.AdministradorArchivos; 
import sistema_archivos.Archivo; 


/**
 * Clase de utilidad encargada de la persistencia no volátil del simulador.
 * Implementa la lógica de exportación de metadatos del sistema de archivos a un 
 * formato estándar JSON, permitiendo la trazabilidad externa de la asignación[cite: 41].
 * * Requisito Técnico: Al no permitir librerías externas (como GSON), la clase 
 * realiza una serialización manual mediante flujos de escritura (FileWriter).
 */
public class GestorJSON {
    
    private static final String ARCHIVO_ESTADO = "estado_sistema.json";

    /**
     * Algoritmo de Persistencia del Estado del Sistema.
     * 1. Recorrido de Estructuras: Accede a la 'ListaEnlazada' de archivos de la 
     * carpeta actual a través del administrador.
     * 2. Serialización Textual: Transforma los objetos 'Archivo' en pares clave-valor 
     * siguiendo la sintaxis JSON (nombre y conteo de bloques).
     * 3. Escritura en Disco: Vuelca el búfer de texto en un archivo físico en el 
     * almacenamiento secundario de la máquina real.
     * * Transición de Estado: El sistema transfiere los metadatos desde estructuras 
     * dinámicas en RAM hacia un soporte físico permanente (Archivo JSON).
     */
    public static void guardarEstado(AdministradorArchivos admin) {
        try (FileWriter fw = new FileWriter(ARCHIVO_ESTADO)) {
            // Estructura de cabecera del JSON
            fw.write("{\n");
            fw.write("  \"sistema_archivos\": [\n");
            
            ListaEnlazada<Archivo> archivos = admin.getCarpetaActual().getArchivos();
            
            for (int i = 0; i < archivos.size(); i++) {
                Archivo a = archivos.get(i);
                
                // Mapeo de metadatos del objeto a texto plano
                fw.write("    {\n");
                fw.write("      \"nombre\": \"" + a.getNombre() + "\",\n");
                fw.write("      \"bloques_ocupados\": " + a.getBloquesAsignados().size() + "\n");
                fw.write("    }");
                
                // Lógica de formato: Controla la puntuación para un JSON válido
                if (i < archivos.size() - 1) {
                    fw.write(",");
                }
                fw.write("\n");
            }
            
            fw.write("  ]\n");
            fw.write("}\n");
            
            System.out.println("Estado guardado correctamente en " + ARCHIVO_ESTADO);
            
        } catch (IOException e) {
            // Manejo de errores de E/S: Garantiza la estabilidad del simulador 
            // ante fallos de escritura en el sistema operativo real.
            System.out.println("Error al guardar el estado JSON: " + e.getMessage());
        }
    }
}