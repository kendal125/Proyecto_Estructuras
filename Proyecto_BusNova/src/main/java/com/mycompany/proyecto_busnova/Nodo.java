/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
public class Nodo {

    Object dato;   // ahora puede guardar cualquier objeto
    Nodo siguiente;

    public Nodo(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Object getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
