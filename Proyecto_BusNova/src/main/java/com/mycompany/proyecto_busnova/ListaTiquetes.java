/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa una lista enlazada simple para gestionar tiquetes.
 * <p>
 * Permite almacenar, buscar, mostrar y eliminar objetos de tipo {@code Tiquete}
 * utilizando una estructura de nodos dinámicos.
 * </p>
 *
 */
public class ListaTiquetes {

    /**
     * Referencia al primer nodo de la lista enlazada.
     */
    private Nodo cabeza;

    /**
     * Constructor de la clase ListaTiquetes.
     * Inicializa la lista vacía, estableciendo la cabeza en {@code null}.
     */
    public ListaTiquetes() {
        cabeza = null;
    }

    /**
     * Obtiene el primer nodo (cabeza) de la lista.
     *
     * @return el nodo inicial de la lista, o {@code null} si está vacía
     */
    public Nodo getCabeza() {
        return cabeza;
    }

    /**
     * Verifica si la lista de tiquetes está vacía.
     *
     * @return {@code true} si la lista no contiene elementos, {@code false} en caso contrario
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Agrega un nuevo tiquete al final de la lista enlazada.
     *
     * @param t el objeto {@code Tiquete} que se desea agregar
     */
    public void agregarTiquete(Tiquete t) {
        Nodo nuevo = new Nodo(t);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);
        }
    }
    
    /**
     * Busca un tiquete en la lista mediante su identificador único.
     *
     * @param id el identificador del tiquete a buscar
     * @return el objeto {@code Tiquete} si se encuentra, o {@code null} si no existe en la lista
     */
    public Tiquete buscarTiquete(String id) {
        Nodo actual = cabeza;

        while (actual != null) {
            Tiquete t = (Tiquete) actual.getDato();

            if (t.getId().equals(id)) {
                return t;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    /**
     * Verifica la existencia de un tiquete en la lista mediante su identificador.
     *
     * @param id el identificador del tiquete a verificar
     * @return {@code true} si el tiquete existe en la lista, {@code false} en caso contrario
     */
    public boolean existeTiquete(String id) {
        return buscarTiquete(id) != null;
    }
    
    /**
     * Calcula y retorna la cantidad total de tiquetes almacenados en la lista.
     *
     * @return el número de nodos (tiquetes) en la lista
     */
    public int getCantidad() {
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }

    /**
     * Genera una representación en texto de todos los tiquetes registrados en la lista.
     *
     * @return una cadena de texto ({@code String}) con la información detallada de cada tiquete,
     * o un mensaje indicando que no hay tiquetes si la lista está vacía
     */
    public String mostrarTiquetes() {
        if (cabeza == null) {
            return "No hay tiquetes registrados.";
        }

        String texto = "";
        Nodo actual = cabeza;

        while (actual != null) {
            Tiquete t = (Tiquete) actual.getDato();

            texto += "ID: " + t.getId()
                    + " | Tipo: " + t.getTipoServicio()
                    + " | Estado: " + t.getEstado()
                    + " | Bus: " + t.getBusAsignadoId()
                    + " | Terminal: " + t.getTerminalOrigen()
                    + "\n";

            actual = actual.getSiguiente();
        }

        return texto;
    }

    /**
     * Elimina un tiquete de la lista utilizando su identificador único.
     * Si el tiquete no existe, la lista permanece sin cambios.
     *
     * @param id el identificador del tiquete que se desea eliminar
     */
    public void eliminarTiquete(String id) {
        if (cabeza == null) {
            return;
        }

        Tiquete primero = (Tiquete) cabeza.getDato();
        if (primero.getId().equals(id)) {
            cabeza = cabeza.getSiguiente();
            return;
        }

        Nodo actual = cabeza;

        while (actual.getSiguiente() != null) {
            Tiquete siguiente = (Tiquete) actual.getSiguiente().getDato();

            if (siguiente.getId().equals(id)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }

            actual = actual.getSiguiente();
        }
    }
}
