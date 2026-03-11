/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package procesos;

/**
 *
 * @author alma
 */
public class LockArchivo {
    private String nombreArchivo;
    private int lectores;
    private boolean escribiendo;

    public LockArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.lectores = 0;
        this.escribiendo = false;
    }

    public String getNombreArchivo() { return nombreArchivo; }

    // Múltiples procesos pueden leer al mismo tiempo, a menos que alguien esté escribiendo
    public synchronized boolean adquirirLockLectura() {
        if (escribiendo) {
            return false; 
        }
        lectores++;
        return true;
    }

    public synchronized void liberarLockLectura() {
        if (lectores > 0) {
            lectores--;
        }
    }

    // Solo UN proceso puede escribir, y solo si nadie más está leyendo ni escribiendo
    public synchronized boolean adquirirLockEscritura() {
        if (lectores > 0 || escribiendo) {
            return false; 
        }
        escribiendo = true;
        return true;
    }

    public synchronized void liberarLockEscritura() {
        escribiendo = false;
    }
}
