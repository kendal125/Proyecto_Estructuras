/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa la configuración inicial del sistema.
 * <p>
 * Esta clase se utiliza principalmente para mapear datos desde un archivo
 * JSON (por ejemplo, usando la librería Jackson) y cargar valores iniciales
 * como el nombre de la terminal, la cantidad de buses y los usuarios del sistema.
 * </p>
 * 
 * <p>
 * Es una clase de tipo contenedor (DTO - Data Transfer Object), por lo que
 * no contiene lógica, únicamente atributos públicos para facilitar la
 * serialización y deserialización de datos.
 * </p>
 * 
 */
public class Config {
    
    /**
     * Nombre de la terminal de buses.
     */
    public String terminal;
    
    /**
     * Cantidad total de buses que se deben crear en el sistema.
     */
    public int cantidadBuses;
    
    /**
     * Arreglo de usuarios que serán cargados en el sistema.
     */
    public Usuario[] usuarios;
}
