/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Journal {
    
    // Archivo físico donde se guardará el historial del SO
    private static final String ARCHIVO_LOG = "journal_sistema.log";

    // Método estático para poder llamarlo desde cualquier parte del proyecto sin instanciar
    public static void registrarTransaccion(String operacion, String nombreArchivo, String estado) {
        try (FileWriter fw = new FileWriter(ARCHIVO_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            // Obtenemos la hora exacta del sistema
            String tiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            // Escribimos la línea en el log: [TIEMPO] | OPERACION | ARCHIVO | ESTADO
            pw.println("[" + tiempo + "] | " + operacion + " | " + nombreArchivo + " | " + estado);
            
        } catch (IOException e) {
            System.out.println("Error crítico del SO: No se pudo escribir en el Journal.");
        }
    }
}
