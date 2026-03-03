/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = null;
        fin = null;
    }

    // Encolar un tiquete (usamos el ID como dato)
    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (fin != null) {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        if (frente == null) {
            frente = nuevo;
        }
    }

    // Desencolar
    public Object desencolar() throws Exception {
        if (frente == null) {
            throw new Exception("La cola está vacía");
        }
        Object valor = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        return valor;
    }

    // Ver frente
    public Object frente() throws Exception {
        if (frente == null) {
            throw new Exception("La cola está vacía");
        }
        return frente.getDato();
    }

    // Verificar si está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Mostrar cola
    public void mostrarCola() {
        Nodo actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
}
