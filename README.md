# Simulador de Sistema de Archivos y Planificación de Disco
### Proyecto II - Sistemas Operativos | Universidad Metropolitana (UNIMET)

Este proyecto es un simulador funcional de conceptos básicos de Sistemas Operativos, incluyendo la gestión de archivos, planificación de peticiones de disco, administración de procesos y recuperación ante fallos.

---

## Características Principales

### 1. Estructuras de Datos Propias (Zero Standard Libraries) 
Cumpliendo con las restricciones del proyecto, **no se utiliza el framework de colecciones de Java (`java.util.*`)**. Se implementaron desde cero:
* **Lista Enlazada Genérica:** Para el manejo de archivos, carpetas y bloques.
* **Cola (Queue):** Para la gestión de procesos y peticiones de disco.

### 2. Planificación de Disco 
El sistema implementa 4 algoritmos de planificación para optimizar el movimiento del cabezal:
* **FIFO** (First-In, First-Out)
* **SSTF** (Shortest Seek Time First)
* **SCAN** (Algoritmo del elevador)
* **C-SCAN** (Circular SCAN)

### 3. Gestión de Procesos y Concurrencia
* **PCB (Process Control Block):** Manejo de estados (Nuevo, Listo, Ejecutando, Bloqueado, Terminado).
* **Sistema de Locks:** Control de acceso a archivos mediante Locks compartidos (lectura) y exclusivos (escritura).

### 4. Sistema de Archivos (En desarrollo)
* Jerarquía de directorios mediante `JTree`.
* Asignación encadenada de bloques en el disco (SD).
* Soporte para Modo Administrador (CRUD) y Modo Usuario (Solo lectura).

---

## Tecnologías Utilizadas
* **Lenguaje:** Java 17+
* **IDE:** Apache NetBeans IDE 28 (o superior)
* **Control de Versiones:** Git & GitHub

---

## Estructura del Proyecto

* `src/estructuras`: Clases base de estructuras de datos personalizadas.
* `src/procesos`: Lógica del gestor de procesos y control de concurrencia.
* `src/planificacion`: Algoritmos de movimiento del cabezal del disco.
* `src/sistema_archivos`: Modelos de archivos, carpetas y lógica del CRUD.

---

## Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/tu_usuario/tu_repositorio.git](https://github.com/tu_usuario/tu_repositorio.git)
