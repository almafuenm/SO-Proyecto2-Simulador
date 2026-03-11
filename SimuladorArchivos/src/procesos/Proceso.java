/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesos;

/**
 *
 * @author alma
 */
public class Proceso {
    public enum Estado {
        NUEVO, LISTO, EJECUTANDO, BLOQUEADO, TERMINADO
    }

    public enum Operacion {
        CREAR, LEER, ACTUALIZAR, ELIMINAR
    }

    private static int contadorId = 1; 
    
    private int id;
    private Estado estado;
    private Operacion operacion;
    private String nombreArchivo;
    private int tamañoBloques; 

    public Proceso(Operacion operacion, String nombreArchivo, int tamañoBloques) {
        this.id = contadorId++;
        this.estado = Estado.NUEVO;
        this.operacion = operacion;
        this.nombreArchivo = nombreArchivo;
        this.tamañoBloques = tamañoBloques;
    }

    public int getId() { return id; }
    
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    
    public Operacion getOperacion() { return operacion; }
    
    public String getNombreArchivo() { return nombreArchivo; }
    
    public int getTamañoBloques() { return tamañoBloques; }

    @Override
    public String toString() {
        return "P" + id + " [" + operacion + " " + nombreArchivo + "] - " + estado;
    }
}
