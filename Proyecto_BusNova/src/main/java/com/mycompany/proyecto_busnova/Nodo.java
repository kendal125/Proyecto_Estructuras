/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa un nodo dentro de una estructura enlazada.
 * <p>
 * Este nodo es utilizado como base para estructuras como listas y colas.
 * Permite almacenar cualquier tipo de objeto y una referencia al siguiente nodo.
 * </p>
 * 
 * <p>
 * Es una implementación genérica (no tipada) que trabaja con {@link Object},
 * lo que permite almacenar diferentes tipos de datos como {@link Bus},
 * {@link Usuario}, entre otros.
 * </p>
 * 
 */
public class Nodo {

    /**
     * Dato almacenado en el nodo.
     * <p>
     * Puede contener cualquier tipo de objeto.
     * </p>
     */
    Object dato;   // ahora puede guardar cualquier objeto
    
    /**
     * Referencia al siguiente nodo en la estructura.
     */
    Nodo siguiente;

    /**
     * Referencia al siguiente nodo en la estructura.
     */
    public Nodo(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return el objeto contenido en el nodo
     */
    public Object getDato() {
        return dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     *
     * @return nodo siguiente en la estructura, o {@code null} si no existe
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * Establece el siguiente nodo en la estructura.
     *
     * @param siguiente nodo que se desea enlazar como siguiente
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
