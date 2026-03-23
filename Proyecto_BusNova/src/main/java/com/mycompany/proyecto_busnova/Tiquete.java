/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import java.util.Date;

/**
 * Clase que representa un tiquete emitido en la terminal.
 * <p>
 * Contiene información sobre el tipo de servicio, estado, hora de creación,
 * hora de atención, precio calculado, bus asignado y terminal de origen.
 * </p>
 */

public class Tiquete {
     /** Tipos de servicio disponibles para los tiquetes. */
    public enum TipoServicio { VIP, REGULAR, CARGA, EJECUTIVO }

    /** Estados posibles de un tiquete. */
    public enum Estado { PENDIENTE, EN_ATENCION, ATENDIDO }

    private String id;
    private TipoServicio tipoServicio;
    private double pesoCarga;
    private Date horaCreacion;
    private Date horaAtencion;
    private Estado estado;
    private int busAsignadoId;
    private String terminalOrigen;
    private double precioCalculado;

    
        /**
     * Constructor de la clase Tiquete.
     *
     * @param id identificador único del tiquete
     * @param tipoServicio tipo de servicio del tiquete
     * @param pesoCarga peso de la carga (si aplica)
     * @param terminalOrigen terminal donde se compró el tiquete
     */

    public Tiquete(String id, TipoServicio tipoServicio, double pesoCarga, Date horaCreacion, Date horaAtencion, Estado estado, int busAsignadoId, String terminalOrigen, double precioCalculado) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.pesoCarga = pesoCarga;
        this.horaCreacion = new Date();
        this.horaAtencion = horaAtencion;
        this.estado = estado;
        this.busAsignadoId = -1;
        this.terminalOrigen = terminalOrigen;
        this.precioCalculado = 0.0;
    }

    
    // Getters y setters documentados
    
    /** @return identificador del tiquete */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    /** @return tipo de servicio del tiquete */

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    
    /** @return peso de la carga asociada */

    public double getPesoCarga() {
        return pesoCarga;
    }

    public void setPesoCarga(double pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    
    /** @return fecha de creación del tiquete */

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    
    /** @param horaAtencion fecha de atención a asignar */

    public Date getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(Date horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    
    /** @param estado nuevo estado del tiquete */

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
    /** @param busAsignadoId identificador del bus asignado */

    public int getBusAsignadoId() {
        return busAsignadoId;
    }

    public void setBusAsignadoId(int busAsignadoId) {
        this.busAsignadoId = busAsignadoId;
    }

    
    /** @return terminal de origen */

    public String getTerminalOrigen() {
        return terminalOrigen;
    }

    public void setTerminalOrigen(String terminalOrigen) {
        this.terminalOrigen = terminalOrigen;
    }

    
    /** @param precioCalculado precio calculado del tiquete */
    
    public double getPrecioCalculado() {
        return precioCalculado;
    }

    public void setPrecioCalculado(double precioCalculado) {
        this.precioCalculado = precioCalculado;
    }

    
    
}
