/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simuladorarchivos;

/**
 *
 * @author alma
 */

/**
 * Clase Lanzadora (Launcher) del proyecto.
 * Su única responsabilidad es actuar como el punto de arranque del simulador,
 * separando la ejecución inicial de la lógica de la interfaz gráfica.
 */
public class SimuladorArchivos {

    public static void main(String[] args) {
        // Lógica de arranque seguro para hilos de Swing
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instancia la ventana y la pone en pantalla
                new gui.VentanaPrincipal().setVisible(true);
            }
        });
    }
}