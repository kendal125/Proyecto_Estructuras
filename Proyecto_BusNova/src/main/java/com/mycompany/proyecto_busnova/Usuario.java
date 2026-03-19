/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Clase que representa un usuario del sistema.
 * <p>
 * Contiene las credenciales necesarias para la autenticación,
 * incluyendo nombre de usuario y contraseña.
 * </p>
 * 
 * <p>
 * Esta clase es utilizada en procesos de login y también puede
 * ser cargada desde archivos de configuración (JSON).
 * </p>
 * 
 */
public class Usuario {

    /**
     * Nombre de usuario.
     */
    private String username;
    
    /**
     * Contraseña del usuario.
     */
    private String password;
    
    /**
     * Constructor vacío.
     * <p>
     * Necesario para procesos de deserialización (usando Jackson).
     * </p>
     */
    public Usuario() { } 

    /**
     * Constructor con parámetros.
     *
     * @param username nombre de usuario
     * @param password contraseña del usuario
     */
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username nuevo nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

     /**
     * Establece la contraseña del usuario.
     *
     * @param password nueva contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
