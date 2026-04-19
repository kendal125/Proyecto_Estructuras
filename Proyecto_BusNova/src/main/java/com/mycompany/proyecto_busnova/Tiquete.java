/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import java.util.Date;

/**
 * Clase que representa un tiquete emitido en la terminal de buses.
 *
 * <p>
 * Cada tiquete almacena la información del cliente, el servicio solicitado,
 * los datos de atención y el estado del proceso dentro del sistema.
 * </p>
 *
 * <p>
 * La información del tiquete incluye:
 * <ul>
 *   <li>Datos personales del cliente (nombre, edad, moneda de cuenta)</li>
 *   <li>Tipo de servicio solicitado</li>
 *   <li>Tipo de bus asignado</li>
 *   <li>Fechas de creación y atención</li>
 *   <li>Estado actual del tiquete</li>
 *   <li>Bus asignado y terminal de origen</li>
 *   <li>Precio calculado al momento de atención</li>
 * </ul>
 * </p>
 *
 * <p>
 * Esta clase es serializada/deserializada hacia archivos JSON como parte de la
 * persistencia del sistema.
 * </p>
 */

public class Tiquete {
     /** Tipos de servicio disponibles para los tiquetes. */
    public enum TipoServicio { VIP, REGULAR, CARGA, EJECUTIVO }

    /** Estados posibles de un tiquete. */
    public enum Estado { PENDIENTE, EN_ATENCION, ATENDIDO }

    private String id;
    private String nombre;
    private int edad;
    private TipoServicio tipoServicio;
    private double pesoCarga;
    private Date horaCreacion;
    private Date horaAtencion;
    private Estado estado;
    private int busAsignadoId;
    private String terminalOrigen;
    private double precioCalculado;
    private String monedaCuenta;
    private char tipoBus;

    
        /**
     * Constructor de la clase Tiquete.
     *
     * @param id identificador único del tiquete
     * @param tipoServicio tipo de servicio del tiquete
     * @param pesoCarga peso de la carga (si aplica)
     * @param terminalOrigen terminal donde se compró el tiquete
     */
    public Tiquete(String id, String nombre, int edad, String monedaCuenta, TipoServicio tipoServicio, double pesoCarga, String terminalOrigen) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.monedaCuenta = monedaCuenta;

        this.tipoServicio = tipoServicio;
        this.pesoCarga = pesoCarga;

        this.horaCreacion = new Date();
        this.horaAtencion = null;

        this.estado = Estado.PENDIENTE;

        this.busAsignadoId = -1;
        this.tipoBus = '?'; // temporalhasta asignación real

        this.terminalOrigen = terminalOrigen;
        this.precioCalculado = 0.0;

    }
    
    public Tiquete() {
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
    
    //
    /** @param nombre nombre usuario */
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /** @param Edad usuario */
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /** @param MonedaCuenta moneda en dolares o colones */
    public String getMonedaCuenta() {
        return monedaCuenta;
    }

    public void setMonedaCuenta(String monedaCuenta) {
        this.monedaCuenta = monedaCuenta;
    }

    /** @param TipoBus el tipo de bus que proveera el servicio */
    public char getTipoBus() {
        return tipoBus;
    }

    public void setTipoBus(char tipoBus) {
        this.tipoBus = tipoBus;
    }

    @Override
    public String toString() {
        return "Tiquete{" + "id=" + id + ", nombre=" + nombre + ", "
                + "edad=" + edad + ", tipoServicio=" + tipoServicio + ", "
                + "pesoCarga=" + pesoCarga + ", horaCreacion=" + horaCreacion + ", "
                + "horaAtencion=" + horaAtencion + ", estado=" + estado + ", "
                + "busAsignadoId=" + busAsignadoId + ", terminalOrigen=" + terminalOrigen + ", "
                + "precioCalculado=" + precioCalculado + ", monedaCuenta=" + monedaCuenta + ", "
                + "tipoBus=" + tipoBus + '}';
    }
    
    

}
