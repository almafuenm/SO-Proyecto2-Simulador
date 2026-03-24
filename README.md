# Simulador de Sistema de Archivos y Planificacion de Disco
### Proyecto II - Sistemas Operativos | Universidad Metropolitana (UNIMET)

Este proyecto consiste en un simulador funcional que integra los conceptos fundamentales de la gestion de recursos de un Sistema Operativo. El sistema abarca la jerarquia logica de archivos, la planificacion de peticiones de disco con visualizacion en tiempo real, la administracion de procesos mediante estados y mecanismos de resiliencia ante fallos.

---

## Caracteristicas Principales

### 1. Estructuras de Datos Propias (Zero Standard Libraries)
En estricto cumplimiento con las restricciones de la catedra, el proyecto no hace uso del framework de colecciones de Java (java.util.*). Se implementaron de forma manual las siguientes estructuras genericas:
* **Lista Enlazada:** Estructura base para el manejo de archivos, directorios y la cadena de bloques fisicos.
* **Cola (Queue):** Utilizada para la gestion de la cola de procesos (Ready Queue) y la cola de peticiones del planificador de disco.

### 2. Planificacion de Disco y Animacion
El sistema simula el comportamiento de un cabezal de lectura/escritura sobre un disco SD de 64 bloques. Implementa los siguientes algoritmos de optimizacion:
* **FIFO** (First-In, First-Out)
* **SSTF** (Shortest Seek Time First)
* **SCAN** (Algoritmo del Elevador)
* **C-SCAN** (Circular SCAN)
* **Gestion Visual:** Los bloques en el disco se identifican mediante un sistema de colores que permite observar la fragmentacion de los archivos en tiempo real.

### 3. Gestion de Procesos y Concurrencia
* **Modelo de 5 Estados:** Implementacion completa del Ciclo de Vida del Proceso (Nuevo, Listo, Ejecutando, Bloqueado y Terminado).
* **Control de Acceso (Locks):** Manejo de exclusion mutua mediante el modelo de Lectores-Escritores, garantizando que multiples procesos puedan leer simultaneamente, pero solo uno pueda escribir de forma exclusiva.

### 4. Sistema de Archivos y Resiliencia
* **Asignacion Encadenada:** Tecnica de almacenamiento donde los archivos ocupan bloques no contiguos vinculados mediante punteros.
* **Journaling:** Registro log de operaciones que asegura la integridad del sistema ante cierres inesperados o fallos criticos.
* **Persistencia:** Almacenamiento del estado del sistema (jerarquia y disco) mediante archivos en formato JSON para su recuperacion posterior.

---

## Tecnologias Utilizadas
* **Lenguaje:** Java 17 o superior.
* **IDE:** Apache NetBeans (Configurado con Ant).
* **Interfaz Grafica:** Java Swing (Sincronizada mediante Event Dispatch Thread).
* **Control de Versiones:** Git y GitHub.

---

## Estructura del Proyecto

* `src/animacion`: Hilos encargados de la logica de movimiento y refresco visual del cabezal.
* `src/estructuras`: Implementaciones manuales de listas, colas y nodos.
* `src/gui`: Dashboard principal y paneles de telemetria del disco.
* `src/persistencia`: Modulos de gestion de archivos JSON y Journaling.
* `src/planificacion`: Clases encargadas de los algoritmos de ordenamiento de peticiones.
* `src/procesos`: Logica del PCB (Process Control Block) y manejo de hilos de ejecucion.
* `src/sistema_archivos`: Modelos de Archivo, Carpeta y el Administrador Logico de Archivos.

---

## Instalacion y Ejecucion

El proyecto esta estructurado para ser compilado mediante la herramienta de construccion Ant en NetBeans.

1. **Clonacion del Repositorio:**
   ```bash
   git clone [https://github.com/tu_usuario/SO-Proyecto2-Simulador.git](https://github.com/almafuenm/SO-Proyecto2-Simulador.git)

2. **Apertura del Proyecto**
* Inicie Apache NetBeans.
* Seleccione File -> Open Project.
* Navegue hasta la carpeta del repositorio y seleccione la subcarpeta SimuladorArchivos (identificada con el icono de proyecto de Java).

3. **Compilacion y Arranque**
* Realice un Clean and Build para inicializar las dependencias y scripts de construccion locales.
* Ejecute el proyecto (F6 o boton de Play).

4. **Autores**
* Alma Fuenmayor
* Iker Solar
