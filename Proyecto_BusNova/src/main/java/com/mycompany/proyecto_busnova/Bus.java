/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa un Bus dentro del sistema.
 * <p>
 * Cada bus tiene un identificador único, un tipo de servicio,
 * una cola de pasajeros asociada y un estado que indica si el
 * inspector está ocupado o no.
 * </p>
 * 
 * <p>
 * Los tipos de bus se representan mediante caracteres:
 * <ul>
 *   <li>'P' = Preferencial</li>
 *   <li>'D' = Directo</li>
 *   <li>'N' = Normal</li>
 * </ul>
 * </p>
 * 
 */
public class Bus {

    /**
     * Identificador único del bus.
     */
    private int id;
    
    /**
     * Tipo de bus:
     * <ul>
     *   <li>'P' = Preferencial</li>
     *   <li>'D' = Directo</li>
     *   <li>'N' = Normal</li>
     * </ul>
     */
    private char tipo; // P = Preferencial, D = Directo, N = Normal
    
    /**
     * Cola de pasajeros asociada al bus.
     */
    private Cola cola;
    
    /**
     * Indica si el bus está ocupado.
     */
    private boolean inspectorOcupado;

    
    /**
     * Constructor de la clase Bus.
     * <p>
     * Inicializa el bus con un identificador, tipo y una cola vacía.
     * El inspector inicia como no ocupado.
     * </p>
     *
     * @param id identificador único del bus
     * @param tipo tipo de bus ('P', 'D', 'N')
     */
    public Bus(int id, char tipo) {
        this.id = id;
        this.tipo = tipo;
        this.cola = new Cola();
        this.inspectorOcupado = false;
    }

    /**
     * Obtiene el identificador del bus.
     *
     * @return el id del bus
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el tipo de bus.
     *
     * @return el tipo de bus ('P', 'D', 'N')
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Obtiene la cola de pasajeros del bus.
     *
     * @return la cola asociada al bus
     */
    public Cola getCola() {
        return cola;
    }

    /**
     * Indica si el inspector está ocupado.
     *
     * @return {@code true} si está ocupado, {@code false} en caso contrario
     */
    public boolean isInspectorOcupado() {
        return inspectorOcupado;
    }

    /**
     * Establece el estado del inspector.
     *
     * @param inspectorOcupado {@code true} si el inspector está ocupado,
     * {@code false} si está disponible
     */
    public void setInspectorOcupado(boolean inspectorOcupado) {
        this.inspectorOcupado = inspectorOcupado;
    }
}
