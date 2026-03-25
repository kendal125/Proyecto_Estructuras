/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa una lista enlazada de buses.
 * <p>
 * Permite almacenar objetos de tipo {@link Bus} en una estructura
 * dinámica utilizando nodos enlazados.
 * </p>
 * 
 * <p>
 * Funcionalidades principales:
 * <ul>
 *   <li>Agregar buses al final de la lista</li>
 *   <li>Consultar la cantidad total de buses registrados</li>
 * </ul>
 * </p>
 * 
 */

public class ListaBuses {
    
   

    /**
     * Nodo que representa el primer elemento de la lista.
     */
    private Nodo primero;
    
    /**
     * Cantidad total de buses almacenados en la lista.
     */
    private int cantidad;

    /**
     * Constructor de la clase ListaBuses.
     * <p>
     * Inicializa una lista vacía sin buses registrados.
     * </p>
     */
    public ListaBuses() {
        primero = null;
        cantidad = 0;
    }

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }
    
    

    /**
     * Agrega un nuevo bus al final de la lista.
     * <p>
     * Si la lista está vacía, el bus se convierte en el primer nodo.
     * En caso contrario, se recorre la lista hasta el último nodo
     * para insertar el nuevo elemento al final.
     * </p>
     *
     * @param bus objeto {@link Bus} que se desea agregar a la lista
     */
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

    /**
     * Obtiene la cantidad total de buses registrados en la lista.
     *
     * @return número de buses almacenados
     */
    public int getCantidad() {
        return cantidad;
    }
    
    /**
    * Genera una representación en texto de todos los buses registrados en la lista.
    * Recorre la lista enlazada de buses y construye un String con la información
    * de cada bus (ID y tipo).
    *
    * @return Un String con la lista de buses registrados. Si no hay buses,
    * retorna un mensaje indicando que la lista está vacía.
    */
    public String mostrarBuses() {
        if (primero == null) {
            return "No hay buses registrados.";
        }

        String busesRegistrados = "BUSES REGISTRADOS\n\n";
        Nodo actual = primero;

        while (actual != null) {
            Bus bus = (Bus) actual.getDato();
            
            String tipo = "";
            
            switch (bus.getTipo()) {
                case 'P':
                    tipo = "Preferencial";
                    break;
                case 'D':
                    tipo = "Directo";
                    break;
                case 'N':
                    tipo = "Normal";
                    break;
                default:
                    tipo = "Desconocido";
                    break;
            }
            busesRegistrados += "ID: " + bus.getId() + " | Tipo: " + tipo + "\n";
            actual = actual.getSiguiente();
            
        }    

        return busesRegistrados;
    }

}