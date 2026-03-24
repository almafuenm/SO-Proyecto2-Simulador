/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import estructuras.ListaEnlazada; 
// Importa tus clases reales de sistema de archivos
import logica_sistema.AdministradorArchivos; 
import sistema_archivos.Archivo; 

public class GestorJSON {
    
    private static final String ARCHIVO_ESTADO = "estado_sistema.json";

    // Método para GUARDAR el estado actual del disco
    public static void guardarEstado(AdministradorArchivos admin) {
        try (FileWriter fw = new FileWriter(ARCHIVO_ESTADO)) {
            fw.write("{\n");
            fw.write("  \"sistema_archivos\": [\n");
            
            // Suponiendo que admin.getCarpetaActual().getArchivos() nos da la lista
            ListaEnlazada<Archivo> archivos = admin.getCarpetaActual().getArchivos();
            
            for (int i = 0; i < archivos.size(); i++) {
                Archivo a = archivos.get(i);
                fw.write("    {\n");
                fw.write("      \"nombre\": \"" + a.getNombre() + "\",\n");
                fw.write("      \"bloques_ocupados\": " + a.getBloquesAsignados().size() + "\n");
                fw.write("    }");
                
                // Ponemos coma si no es el último archivo
                if (i < archivos.size() - 1) {
                    fw.write(",");
                }
                fw.write("\n");
            }
            
            fw.write("  ]\n");
            fw.write("}\n");
            
            System.out.println("Estado guardado correctamente en " + ARCHIVO_ESTADO);
            
        } catch (IOException e) {
            System.out.println("Error al guardar el estado JSON: " + e.getMessage());
        }
    }
}
