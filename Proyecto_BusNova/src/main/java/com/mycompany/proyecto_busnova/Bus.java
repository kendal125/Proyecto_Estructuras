/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import java.util.Date;
import javax.swing.JOptionPane;

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
    
    
     /**
     * Procesa el abordaje del siguiente tiquete en la cola del bus.
     * <p>
     * Si el pasajero paga, se marca como ATENDIDO y se guarda en
     * atendidos.json. Si se niega, se elimina de la cola y no se marca como
     * atendido.
     * </p>
     *
     * @param sistema referencia al sistema para cálculo de precio y
     * persistencia
     */
    public void abordar(Sistema sistema) {
        try {
            if (cola.estaVacia()) {
                JOptionPane.showMessageDialog(null, "No hay tiquetes en la cola del bus " + id);
                return;
            }
            if (inspectorOcupado) {
                JOptionPane.showMessageDialog(null, "Inspector del bus " + id + " está ocupado.");
                return;
            }

            Tiquete t = cola.peekTiquete();
            if (t == null) {
                return;
            }

            t.setEstado(Tiquete.Estado.EN_ATENCION);
            t.setHoraAtencion(new Date());
            t.setBusAsignadoId(this.id);
            this.inspectorOcupado = true;

            double precio = sistema.calcularPrecio(t);
            //Determinar símbolo de moneda
            String simbolo;
            if (t.getMonedaCuenta().equalsIgnoreCase("COLONES")) {
                simbolo = "₡";
            } else {
                simbolo = "$";
            }

            t.setPrecioCalculado(precio);

            String mensaje = "Tiquete ID: " + t.getId()
                + "\nServicio: " + t.getTipoServicio()
                + "\nMonto a pagar: " + simbolo + precio
                + "\n¿Desea pagar y abordar?";
            
            int resp = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar pago", JOptionPane.YES_NO_OPTION);

            if (resp == JOptionPane.YES_OPTION) {
                cola.desencolarTiquete();
                t.setEstado(Tiquete.Estado.ATENDIDO);
                t.setHoraAtencion(new Date());
                sistema.guardarAtendido(t);
                JOptionPane.showMessageDialog(null, "Tiquete atendido y subió al bus " + id);
            } else {
                cola.desencolarTiquete();
                JOptionPane.showMessageDialog(null, "El pasajero fue retirado de la fila por negarse a pagar.");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al procesar abordaje: " + e.getMessage());
        } finally {
            this.inspectorOcupado = false;
        }
        
    }
    
    
    
    
    
    
}
