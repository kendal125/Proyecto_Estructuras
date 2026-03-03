/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
public class ListaUsuarios {

    private Nodo cabeza;

    public ListaUsuarios() {
        cabeza = null;
    }

    public void agregarUsuario(Usuario usuario) {
        Nodo nuevo = new Nodo(usuario);
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

    public boolean validarLogin(String user, String pass) {
        Nodo actual = cabeza;
        while (actual != null) {
            Usuario u = (Usuario) actual.getDato();
            if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}
