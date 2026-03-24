/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Sistema de gestión de buses y usuarios para la terminal BusNova.
 * <p>
 * Esta clase centraliza la lógica principal del sistema, incluyendo:
 * <ul>
 *   <li>Gestión de buses</li>
 *   <li>Gestión de usuarios</li>
 *   <li>Configuración del sistema mediante archivo JSON</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Permite crear buses, registrar usuarios, cambiar el nombre de la terminal,
 * así como cargar y guardar la configuración utilizando la librería Jackson.
 * </p>
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.CARGA;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.EJECUTIVO;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.REGULAR;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.VIP;

public class Sistema {

    /**
     * Nombre de la terminal de buses.
     */
    private String nombreTerminal;
    
    /**
     * Lista de buses del sistema.
     */
    private ListaBuses listaBuses;
    
    /**
     * Lista de usuarios del sistema.
     */
    private ListaUsuarios listaUsuarios;

    /**
     * Constructor de la clase Sistema.
     * <p>
     * Inicializa las listas de buses y usuarios.
     * Agrega un usuario administrador por defecto si no existe.
     * Si se encuentra un archivo de configuración, lo carga automáticamente.
     * </p>
     */
    public Sistema() {
        listaBuses = new ListaBuses();
        listaUsuarios = new ListaUsuarios();
        
        if (!listaUsuarios.validarLogin("admin", "admin")) {
            listaUsuarios.agregarUsuario(new Usuario("admin", "admin"));
        }

        if (existeConfig()) {
            cargarConfig();
        }
    }
    
    /**
     * Verifica si existe el archivo de configuración {@code config.json}.
     *
     * @return {@code true} si el archivo existe, {@code false} en caso contrario
     */
    public boolean existeConfig() {
        File archivo = new File("config.json");
        return archivo.exists();
    }
    
    /**
     * Establece el nombre de la terminal.
     *
     * @param nombre nuevo nombre de la terminal
     */
    public void setNombreTerminal(String nombre) {
        this.nombreTerminal = nombre;
    }
    
    /**
     * Obtiene el nombre de la terminal.
     *
     * @return nombre de la terminal
     */
    public String getNombreTerminal() {
        return nombreTerminal;
    }
    
    /**
     * Crea una cantidad específica de buses.
     * <p>
     * Los primeros dos buses son especiales:
     * <ul>
     *   <li>ID 1 - tipo 'P' (Preferencial)</li>
     *   <li>ID 2 - tipo 'D' (Directo)</li>
     * </ul>
     * Los demás buses son de tipo 'N' (Normal).
     * </p>
     *
     * @param cantidad número total de buses a crear
     */
    public void crearBuses(int cantidad) {

        if (cantidad < 2) {
            System.out.println("Debe haber mínimo 2 buses.");
            return;
        }

        listaBuses.agregarBus(new Bus(1, 'P'));
        listaBuses.agregarBus(new Bus(2, 'D'));

        for (int i = 3; i <= cantidad; i++) {
            listaBuses.agregarBus(new Bus(i, 'N'));
        }
    }
    
    /**
     * Agrega un nuevo usuario al sistema.
     *
     * @param user nombre de usuario
     * @param pass contraseña del usuario
     */
    public void agregarUsuario(String user, String pass) {
        listaUsuarios.agregarUsuario(new Usuario(user, pass));
    }
    
    /**
     * Cambia el nombre de la terminal y guarda la configuración.
     *
     * @param nuevoNombre nuevo nombre de la terminal
     */
    public void cambiarNombreTerminal(String nuevoNombre) {
        this.nombreTerminal = nuevoNombre;
        guardarConfiguracion();
    }
    
    /**
     * Agrega múltiples buses de tipo normal ('N').
     *
     * @param cantidadBuses cantidad de buses a agregar
     */
    public void agregarBuses(int cantidadBuses) {
       
        int idInicial = listaBuses.getCantidad() + 1; 

        for (int i = 0; i < cantidadBuses; i++) {
            Bus busNuevo = new Bus(idInicial + i, 'N');
            listaBuses.agregarBus(busNuevo);
        }
        
    }

    /**
     * Obtiene la lista de buses.
     *
     * @return lista de buses
     */
    public ListaBuses getListaBuses() {
        return listaBuses;
    }
    
    /**
    * Obtiene la lista de buses registrados en el sistema en formato de texto.
    * Este método actúa como intermediario entre el menú y la estructura de datos.
    *
    * @return Un String con la información de los buses registrados.
    */
    public String mostrarBuses() {
        return listaBuses.mostrarBuses();
    }

    /**
     * Obtiene la lista de usuarios.
     *
     * @return lista de usuarios
     */
    public ListaUsuarios getListaUsuarios() {
        return listaUsuarios;
    }
    
    /**
    * Obtiene la lista de usuarios registrados en el sistema en formato de texto.
    * Este método actúa como intermediario entre el menú y la estructura de datos.
    *
    * @return Un String con la información de los usuarios registrados.
    */
    public String mostrarUsuarios() {
        return listaUsuarios.mostrarUsuarios();
    }
    
    /**
     * Carga la configuración desde el archivo config.json.
     * <p>
     * Utiliza la librería Jackson para deserializar el archivo JSON
     * </p>
     */
    public void cargarConfig(){
        try {

            ObjectMapper mapper = new ObjectMapper();

            Config config = mapper.readValue(new File("config.json"),Config.class);

            this.nombreTerminal = config.terminal;

            crearBuses(config.cantidadBuses);

            for (Usuario user : config.usuarios) {
                listaUsuarios.agregarUsuario(user);
            }
            
            System.out.println("Usuarios cargados: " + config.usuarios.length);

        } catch (Exception e) {
            System.err.println("Error al leer Json");
        }
    }
    
    /**
    * Verifica si un usuario existe en el sistema.
    * <p>
    * Este método delega la validación a la lista de usuarios.
    * </p>
    *
    * @param username nombre de usuario a buscar
    * @return {@code true} si el usuario existe, {@code false} en caso contrario
    */
    public boolean existeUsuario(String username) {
        return listaUsuarios.existeUsuario(username);
    }
    
    /**
     * Guarda la configuración actual en el archivo {@code config.json}.
     * <p>
     * Serializa los datos del sistema utilizando la librería Jackson
     * en formato JSON con estructura legible (pretty print).
     * </p>
     */
    public void guardarConfiguracion() {
        try {

           ObjectMapper mapper = new ObjectMapper();

           Config config = new Config();
           config.terminal = this.nombreTerminal;
           config.cantidadBuses = this.listaBuses.getCantidad();
           config.usuarios = listaUsuarios.getUsuarios();

           mapper.writerWithDefaultPrettyPrinter()
                 .writeValue(new File("config.json"), config);

           System.out.println("Archivo config.json generado correctamente");

        } catch (Exception e) {
           System.err.println("Error al escribir Json: " + e.getMessage());
        }
    }
    
     /**
 * Calcula el precio del tiquete según las reglas de servicio.
     *
     * @param t tiquete a calcular
     * @return precio calculado
     */
    public double calcularPrecio(Tiquete t) {
        double precio = 0.0;
        switch (t.getTipoServicio()) {
            case VIP:
                precio = 20 + 100;
                break;
            case REGULAR:
                precio = 20;
                break;
            case CARGA:
                precio = 20 + (10 * t.getPesoCarga());
                break;
            case EJECUTIVO:
                precio = 20 + 1000;
                break;
        }
        return precio;
    }

    /**
     * Guarda un tiquete atendido en el archivo atendidos.json.
     * <p>
     * Si el archivo no existe, se crea con un arreglo que contiene el tiquete.
     * Si ya existe, se lee el arreglo, se crea uno nuevo con espacio adicional
     * y se agrega el nuevo tiquete.
     * </p>
     *
     * @param t tiquete marcado como ATENDIDO
     */
    public void guardarAtendido(Tiquete t) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("atendidos.json");

            Tiquete[] existentes;

            if (file.exists()) {
                // leer arreglo existente
                existentes = mapper.readValue(file, Tiquete[].class);
            } else {
                existentes = new Tiquete[0];
            }
            // crear nuevo arreglo con espacio adicional
            Tiquete[] nuevo = new Tiquete[existentes.length + 1];
            for (int i = 0; i < existentes.length; i++) {
                nuevo[i] = existentes[i];
            }
            nuevo[existentes.length] = t;

            // sobrescribir archivo con arreglo actualizado
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, nuevo);

            System.out.println("Tiquete guardado en atendidos.json: " + t.getId());
        } catch (Exception e) {
            System.err.println("Error guardando atendido: " + e.getMessage());
        }
    }
    
    
    
    
    
    
}