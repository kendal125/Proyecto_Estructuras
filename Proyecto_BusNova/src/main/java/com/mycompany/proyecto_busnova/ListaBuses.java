/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
public class ListaBuses {

    private Nodo primero;
    private int cantidad;

    public ListaBuses() {
        primero = null;
        cantidad = 0;
    }

    public void agregarBus(Bus bus) {
        Nodo nuevo = new Nodo(bus);

        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }

        cantidad++;
    }

    public int getCantidad() {
        return cantidad;
    }
}