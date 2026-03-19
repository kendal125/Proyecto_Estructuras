/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa una lista enlazada de usuarios.
 * <p>
 * Permite almacenar, recorrer y validar usuarios dentro del sistema.
 * Utiliza una estructura basada en nodos enlazados.
 * </p>
 * 
 * <p>
 * Funcionalidades principales:
 * <ul>
 *   <li>Agregar usuarios a la lista</li>
 *   <li>Validar credenciales de inicio de sesión</li>
 *   <li>Obtener la lista de usuarios en formato de arreglo</li>
 * </ul>
 * </p>
 * 
 */
public class ListaUsuarios {

    /**
     * Nodo que representa el inicio de la lista de usuarios.
     */
    private Nodo cabeza;

    /**
     * Constructor de la clase ListaUsuarios.
     * <p>
     * Inicializa una lista vacía sin usuarios.
     * </p>
     */
    public ListaUsuarios() {
        cabeza = null;
    }

    /**
     * Agrega un nuevo usuario al final de la lista.
     *
     * @param usuario objeto {@link Usuario} que se desea agregar
     */
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

    /**
     * Valida las credenciales de un usuario.
     * <p>
     * Recorre la lista de usuarios y compara el nombre de usuario
     * y contraseña con los valores ingresados.
     * </p>
     *
     * @param user nombre de usuario ingresado
     * @param pass contraseña ingresada
     * @return {@code true} si las credenciales son correctas,
     * {@code false} en caso contrario
     */
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

    /**
     * Obtiene el nodo cabeza de la lista.
     *
     * @return nodo inicial de la lista de usuarios
     */
    public Nodo getCabeza() {
        return cabeza;
    }
    
    /**
     * Convierte la lista enlazada de usuarios en un arreglo.
     * <p>
     * Primero cuenta la cantidad de usuarios y luego recorre nuevamente
     * la lista para llenar el arreglo.
     * </p>
     *
     * @return arreglo de objetos {@link Usuario} con todos los usuarios registrados
     */
    public Usuario[] getUsuarios() {

        // contar usuarios
        int contador = 0;
        Nodo actual = cabeza;

        while (actual != null) {
            contador++;
            actual = actual.getSiguiente(); // recorrer Lista
        }

        // Crear array
        Usuario[] usuarios = new Usuario[contador];

        // llenar lista
        actual = cabeza;
        int i = 0;

        while (actual != null) {
            usuarios[i] = (Usuario) actual.getDato();
            actual = actual.getSiguiente();
            i++;
        }

        return usuarios;
    }
    
}
