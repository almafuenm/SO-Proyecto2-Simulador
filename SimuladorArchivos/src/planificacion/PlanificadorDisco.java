/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planificacion;

/**
 *
 * @author alma
 */

import estructuras.ListaEnlazada;

/**
 * Clase encargada de la planificación de peticiones de entrada/salida (E/S) del disco.
 * Implementa diversas políticas de despacho para optimizar el movimiento del cabezal 
 * físico y reducir la latencia de acceso a los datos en el SD[cite: 109].
 * * Lógica de Diseño: Encapsula algoritmos clásicos de planificación, permitiendo 
 * comparar empíricamente el rendimiento de cada uno bajo diferentes cargas de trabajo.
 */
public class PlanificadorDisco {
    private int posicionCabezal;
    private int limiteDisco = 200; 

    public PlanificadorDisco(int posicionInicial) {
        this.posicionCabezal = posicionInicial;
    }

    /**
     * Algoritmo FIFO (First-In, First-Out).
     * Lógica: Atiende las solicitudes en el orden estricto en que fueron generadas 
     * por los procesos de usuario.
     * Ventaja: Es el algoritmo más justo (no hay inanición), pero puede causar 
     * desplazamientos excesivos del cabezal si las peticiones están dispersas [cite: 86-87].
     */
    public ListaEnlazada<Integer> planificarFIFO(ListaEnlazada<Integer> solicitudes) {
        return solicitudes; 
    }

    /**
     * Algoritmo SSTF (Shortest Seek Time First).
     * Lógica de Selección: Evalúa todas las solicitudes pendientes y selecciona 
     * aquella que se encuentre a la menor distancia absoluta de la posición actual del cabezal.
     * Transición de Estado: En cada iteración, el cabezal "salta" al bloque más cercano, 
     * actualizando su posición para la siguiente evaluación hasta vaciar la cola.
     * Nota: Minimiza el desplazamiento total, pero puede causar inanición en bloques lejanos.
     */
    public ListaEnlazada<Integer> planificarSSTF(ListaEnlazada<Integer> solicitudes) {
        ListaEnlazada<Integer> resultado = new ListaEnlazada<>();
        ListaEnlazada<Integer> pendientes = copiaLista(solicitudes);
        int cabezalActual = this.posicionCabezal;

        while (!pendientes.isEmpty()) {
            int indiceMasCercano = 0;
            int distanciaMinima = Math.abs(pendientes.get(0) - cabezalActual);

            for (int i = 1; i < pendientes.size(); i++) {
                int distancia = Math.abs(pendientes.get(i) - cabezalActual);
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    indiceMasCercano = i;
                }
            }

            int elegido = pendientes.get(indiceMasCercano);
            resultado.add(elegido);
            cabezalActual = elegido; 
            pendientes.remove(indiceMasCercano);
        }
        return resultado;
    }

    /**
     * Algoritmo SCAN (Algoritmo del Elevador).
     * Lógica de Barrido: Divide las solicitudes en dos grupos (derecha e izquierda 
     * del cabezal). El cabezal recorre el disco en una dirección hasta el extremo 
     * y luego invierte el sentido para procesar el resto.
     * Transición de Estado: El sistema transita de un modo ascendente a uno descendente 
     * (o viceversa), asegurando que todas las peticiones sean atendidas en un ciclo completo.
     */
    public ListaEnlazada<Integer> planificarSCAN(ListaEnlazada<Integer> solicitudes, boolean ascendente) {
        ListaEnlazada<Integer> resultado = new ListaEnlazada<>();
        ListaEnlazada<Integer> ordenadas = ordenarLista(copiaLista(solicitudes));
        
        ListaEnlazada<Integer> derecha = new ListaEnlazada<>();
        ListaEnlazada<Integer> izquierda = new ListaEnlazada<>();
        
        for (int i = 0; i < ordenadas.size(); i++) {
            if (ordenadas.get(i) >= posicionCabezal) derecha.add(ordenadas.get(i));
            else izquierda.add(ordenadas.get(i));
        }

        if (ascendente) {
            for (int i = 0; i < derecha.size(); i++) resultado.add(derecha.get(i));
            for (int i = izquierda.size() - 1; i >= 0; i--) resultado.add(izquierda.get(i));
        } else {
            for (int i = izquierda.size() - 1; i >= 0; i--) resultado.add(izquierda.get(i));
            for (int i = 0; i < derecha.size(); i++) resultado.add(derecha.get(i));
        }
        return resultado;
    }

    /**
     * Algoritmo C-SCAN (Circular SCAN).
     * Lógica de Flujo Único: Similar a SCAN, pero solo procesa solicitudes en una 
     * dirección (ej. ascendente). Al llegar al final, el cabezal realiza un retorno 
     * rápido al inicio sin procesar nada en el regreso.
     * Ventaja: Proporciona un tiempo de espera más uniforme para todas las peticiones, 
     * tratando el espacio de bloques como un bucle continuo.
     */
    public ListaEnlazada<Integer> planificarCSCAN(ListaEnlazada<Integer> solicitudes) {
        ListaEnlazada<Integer> resultado = new ListaEnlazada<>();
        ListaEnlazada<Integer> ordenadas = ordenarLista(copiaLista(solicitudes));
        
        ListaEnlazada<Integer> derecha = new ListaEnlazada<>();
        ListaEnlazada<Integer> izquierda = new ListaEnlazada<>();
        
        for (int i = 0; i < ordenadas.size(); i++) {
            if (ordenadas.get(i) >= posicionCabezal) derecha.add(ordenadas.get(i));
            else izquierda.add(ordenadas.get(i));
        }

        for (int i = 0; i < derecha.size(); i++) resultado.add(derecha.get(i));
        for (int i = 0; i < izquierda.size(); i++) resultado.add(izquierda.get(i));
        
        return resultado;
    }

    /**
     * Lógica de Ordenamiento (Burbuja): Organiza las solicitudes de forma ascendente.
     * Requisito Técnico: Implementado manualmente para evitar el uso de librerías 
     * prohibidas de Java, permitiendo la división de grupos necesaria para SCAN y C-SCAN.
     */
    private ListaEnlazada<Integer> ordenarLista(ListaEnlazada<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    intercambiar(lista, j, j + 1);
                }
            }
        }
        return lista;
    }
    
    private void intercambiar(ListaEnlazada<Integer> lista, int i, int j) {
        int temp = lista.get(i);
        lista.set(i, lista.get(j));
        lista.set(j, temp);
    }

    private ListaEnlazada<Integer> copiaLista(ListaEnlazada<Integer> original) {
        ListaEnlazada<Integer> copia = new ListaEnlazada<>();
        for (int i = 0; i < original.size(); i++) copia.add(original.get(i));
        return copia;
    }

    public void setPosicionCabezal(int posicion) { this.posicionCabezal = posicion; }
}