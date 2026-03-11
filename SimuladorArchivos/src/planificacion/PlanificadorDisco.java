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

public class PlanificadorDisco {
    private int posicionCabezal;
    private int limiteDisco = 200; // Valor de ejemplo para el extremo del disco

    public PlanificadorDisco(int posicionInicial) {
        this.posicionCabezal = posicionInicial;
    }

    // 1. Algoritmo FIFO: Atiende las solicitudes en el orden en que llegaron
    public ListaEnlazada<Integer> planificarFIFO(ListaEnlazada<Integer> solicitudes) {
        // En FIFO el orden esperado es exactamente el mismo de entrada 
        return solicitudes; 
    }

    // 2. Algoritmo SSTF: Shortest Seek Time First (Busca el más cercano al cabezal)
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
            cabezalActual = elegido; // El cabezal se mueve a la nueva posición [cite: 53]
            pendientes.remove(indiceMasCercano);
        }
        return resultado;
    }

    // 3. Algoritmo SCAN (Elevador): Sube hasta el final y luego baja
    public ListaEnlazada<Integer> planificarSCAN(ListaEnlazada<Integer> solicitudes, boolean ascendente) {
        ListaEnlazada<Integer> resultado = new ListaEnlazada<>();
        ListaEnlazada<Integer> ordenadas = ordenarLista(copiaLista(solicitudes));
        
        // Separar solicitudes a la derecha e izquierda del cabezal
        ListaEnlazada<Integer> derecha = new ListaEnlazada<>();
        ListaEnlazada<Integer> izquierda = new ListaEnlazada<>();
        
        for (int i = 0; i < ordenadas.size(); i++) {
            if (ordenadas.get(i) >= posicionCabezal) derecha.add(ordenadas.get(i));
            else izquierda.add(ordenadas.get(i));
        }

        if (ascendente) {
            // Sube hasta el final y luego baja
            for (int i = 0; i < derecha.size(); i++) resultado.add(derecha.get(i));
            for (int i = izquierda.size() - 1; i >= 0; i--) resultado.add(izquierda.get(i));
        } else {
            // Baja hasta el inicio y luego sube
            for (int i = izquierda.size() - 1; i >= 0; i--) resultado.add(izquierda.get(i));
            for (int i = 0; i < derecha.size(); i++) resultado.add(derecha.get(i));
        }
        return resultado;
    }

    // 4. Algoritmo C-SCAN: Solo procesa en una dirección, luego salta al inicio
    public ListaEnlazada<Integer> planificarCSCAN(ListaEnlazada<Integer> solicitudes) {
        ListaEnlazada<Integer> resultado = new ListaEnlazada<>();
        ListaEnlazada<Integer> ordenadas = ordenarLista(copiaLista(solicitudes));
        
        ListaEnlazada<Integer> derecha = new ListaEnlazada<>();
        ListaEnlazada<Integer> izquierda = new ListaEnlazada<>();
        
        for (int i = 0; i < ordenadas.size(); i++) {
            if (ordenadas.get(i) >= posicionCabezal) derecha.add(ordenadas.get(i));
            else izquierda.add(ordenadas.get(i));
        }

        // Procesa la derecha (62 -> 64... -> 180)
        for (int i = 0; i < derecha.size(); i++) resultado.add(derecha.get(i));
        // Salta al inicio y procesa la izquierda (11 -> 34)
        for (int i = 0; i < izquierda.size(); i++) resultado.add(izquierda.get(i));
        
        return resultado;
    }

    // Método de ordenamiento Burbuja (ya que no podemos usar java.util)
    private ListaEnlazada<Integer> ordenarLista(ListaEnlazada<Integer> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j) > lista.get(j + 1)) {
                    int temp = lista.get(j);
                    // Aquí necesitarías un método 'set' en tu ListaEnlazada o hacerlo manual
                    // Si no tienes 'set', podemos intercambiar los datos de los nodos
                    intercambiar(lista, j, j + 1);
                }
            }
        }
        return lista;
    }
    
    // Auxiliares
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
