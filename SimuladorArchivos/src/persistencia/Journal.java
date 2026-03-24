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


/**
 * Clase encargada de la persistencia de transacciones para la recuperación ante fallos.
 * Implementa un registro de auditoría (Log) que asegura la integridad del sistema 
 * de archivos mediante la técnica de Write-Ahead Logging (WAL).
 * * Lógica de Diseño: Proporciona métodos estáticos para un acceso global desde 
 * cualquier módulo del núcleo (Kernel), garantizando que toda operación crítica 
 * sea documentada antes de alterar el Almacenamiento Secundario (SD).
 */
public class Journal {
    
    // Archivo físico que actúa como bitácora de persistencia del simulador
    private static final String ARCHIVO_LOG = "journal_sistema.log";

    /**
     * Algoritmo de Registro de Transacciones.
     * 1. Estampado de Tiempo: Captura la hora exacta del evento para facilitar la 
     * auditoría y el ordenamiento cronológico de las operaciones.
     * 2. Persistencia en Log: Abre un flujo de escritura en modo 'append' (anexar) 
     * para no sobrescribir registros previos, asegurando un historial histórico completo.
     * 3. Formateo de Datos: Organiza la información en una estructura de columnas 
     * (Operación, Archivo, Estado) para su posterior análisis por el hilo de recuperación.
     * * Transición de Estado: Las operaciones se registran inicialmente como 'PENDIENTE' 
     * y solo transitan a 'CONFIRMADO' tras el éxito físico en el disco simulado.
     */
    public static void registrarTransaccion(String operacion, String nombreArchivo, String estado) {
        try (FileWriter fw = new FileWriter(ARCHIVO_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            // Sincronización Temporal: Formatea la fecha para estándares de sistemas operativos
            String tiempo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            // Lógica de Escritura: Vuelca la transacción al soporte físico no volátil
            pw.println("[" + tiempo + "] | " + operacion + " | " + nombreArchivo + " | " + estado);
            
        } catch (IOException e) {
            // Manejo de Error Crítico: Notifica si la capa de persistencia real falla, 
            // lo cual comprometería la seguridad de los datos en el simulador.
            System.out.println("Error crítico del SO: No se pudo escribir en el Journal.");
        }
    }
}