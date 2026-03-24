/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Implementación de una estructura de datos tipo cola (FIFO - First In, First Out).
 * <p>
 * Esta clase permite almacenar elementos en una secuencia donde el primer elemento
 * en entrar es el primero en salir. Se utiliza una estructura enlazada mediante nodos.
 * </p>
 * 
 * <p>
 * Funcionalidades principales:
 * <ul>
 *   <li>Encadenar elementos (encolar)</li>
 *   <li>Remover elementos (desencolar)</li>
 *   <li>Consultar el frente de la cola</li>
 *   <li>Verificar si está vacía</li>
 *   <li>Mostrar todos los elementos</li>
 * </ul>
 * </p>
 * 
 */
public class Cola {

    /**
     * Nodo que representa el frente de la cola (primer elemento).
     */
    Nodo frente;
    
    /**
     * Nodo que representa el final de la cola (último elemento).
     */
    private Nodo fin;

    /**
     * Constructor de la clase Cola.
     * <p>
     * Inicializa una cola vacía sin elementos.
     * </p>
     */
    public Cola() {
        frente = null;
        fin = null;
    }

    /**
     * Inserta un nuevo elemento al final de la cola.
     * <p>
     * El elemento se agrega en la última posición (fin).
     * </p>
     *
     * @param dato objeto que se desea agregar a la cola
     */
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

    /**
     * Remueve y retorna el elemento al frente de la cola.
     *
     * @return el elemento que estaba al frente de la cola
     * @throws Exception si la cola está vacía
     */
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

    /**
     * Obtiene el elemento al frente de la cola sin removerlo.
     *
     * @return el elemento al frente de la cola
     * @throws Exception si la cola está vacía
     */
    public Object frente() throws Exception {
        if (frente == null) {
            throw new Exception("La cola está vacía");
        }
        return frente.getDato();
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return {@code true} si la cola no contiene elementos,
     * {@code false} en caso contrario
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Muestra todos los elementos de la cola en consola.
     * <p>
     * Recorre la cola desde el frente hasta el final imprimiendo cada dato.
     * </p>
     */
    public void mostrarCola() {
        Nodo actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.getDato() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
    
    
    /**
     * Devuelve el tamaño actual de la cola.
     *
     * @return número de elementos en la cola
     */
    public int getTamaño() {
        int contador = 0;
        Nodo actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    /**
     * Devuelve el tiquete al frente sin removerlo.
     *
     * @return tiquete en el frente o null si la cola está vacía
     */
    public Tiquete peekTiquete() {
        if (frente == null) {
            return null;
        }
        return (Tiquete) frente.getDato();
    }

    /**
     * Desencola y retorna el tiquete del frente.
     *
     * @return tiquete desencolado
     * @throws Exception si la cola está vacía
     */
    public Tiquete desencolarTiquete() throws Exception {
        Object obj = desencolar();
        return (Tiquete) obj;
    }

    /**
     * Encola un tiquete en la cola.
     *
     * @param t tiquete a encolar
     */
    public void encolarTiquete(Tiquete t) {
        encolar(t);
    }
    
    
    
}
