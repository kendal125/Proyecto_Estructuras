/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 * Sistema de gestión de buses y usuarios para la terminal BusNova.
 * Maneja la configuración, buses y usuarios del sistema.
 *
 * @author lopez
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Sistema {

    private String nombreTerminal;
    private ListaBuses listaBuses;
    private ListaUsuarios listaUsuarios;

    /**
     * Constructor for Sistema. Initializes the bus and user lists, adds a default admin user if not present, and loads configuration if it exists.
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
     * Verifica si existe el archivo de configuración config.json.
     *
     * @return true si el archivo existe, false en caso contrario.
     */
    public boolean existeConfig() {
        File archivo = new File("config.json");
        return archivo.exists();
    }
    //Cambia y devuelve el nombre de la terminal
    /**
     * Sets the name of the terminal.
     *
     * @param nombre the new name for the terminal
     */
    public void setNombreTerminal(String nombre) {
        this.nombreTerminal = nombre;
    }
    
    public String getNombreTerminal() {
        return nombreTerminal;
    }
    //Crea varios buses según la cantidad indicada
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
    //Crea varios usuarios según la cantidad indicada
    public void agregarUsuario(String user, String pass) {
        listaUsuarios.agregarUsuario(new Usuario(user, pass));
    }
    //Crea varios buses según la cantidad indicada
    public void cambiarNombreTerminal(String nuevoNombre) {
        this.nombreTerminal = nuevoNombre;
        guardarConfiguracion();
    }
    //Agrega un bus extra tipo 'N'
    public void agregarBuses(int cantidadBuses) {
       
        int idInicial = listaBuses.getCantidad() + 1; 

        for (int i = 0; i < cantidadBuses; i++) {
            Bus busNuevo = new Bus(idInicial + i, 'N');
            listaBuses.agregarBus(busNuevo);
        }
        
    }

    public ListaBuses getListaBuses() {
        return listaBuses;
    }

    public ListaUsuarios getListaUsuarios() {
        return listaUsuarios;
    }
    
    //Carga el archivo
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
    
    //Guarda el archivo
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
}