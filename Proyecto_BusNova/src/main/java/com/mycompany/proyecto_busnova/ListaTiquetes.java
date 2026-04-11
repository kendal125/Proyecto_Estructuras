/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;


public class ListaTiquetes {

    private Nodo cabeza;

    public ListaTiquetes() {
        cabeza = null;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

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

    public boolean existeTiquete(String id) {
        return buscarTiquete(id) != null;
    }

    public int getCantidad() {
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }

        return contador;
    }

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
