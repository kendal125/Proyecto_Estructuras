/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
import java.io.*;

public class Sistema {

    private String nombreTerminal;
    private ListaBuses listaBuses;
    private ListaUsuarios listaUsuarios;

    public Sistema() {
        listaBuses = new ListaBuses();
        listaUsuarios = new ListaUsuarios();

        if (existeConfig()) {
            cargarConfiguracion();
        }
    }

    public boolean existeConfig() {
        File archivo = new File("config.json");
        return archivo.exists();
    }

    public void setNombreTerminal(String nombre) {
        this.nombreTerminal = nombre;
    }

    public String getNombreTerminal() {
        return nombreTerminal;
    }

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

    public void agregarUsuario(String user, String pass) {
        listaUsuarios.agregarUsuario(new Usuario(user, pass));
    }

    // MÉTODO QUE FALTABA
    public void cambiarNombreTerminal(String nuevoNombre) {
        this.nombreTerminal = nuevoNombre;
        guardarConfiguracion();
    }

    // MÉTODO QUE FALTABA
    public void agregarBusExtra() {
        int nuevoId = listaBuses.getCantidad() + 1;
        listaBuses.agregarBus(new Bus(nuevoId, 'N'));
    }

    public void guardarConfiguracion() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("config.json"));
            writer.println(nombreTerminal);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarConfiguracion() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.json"));
            nombreTerminal = br.readLine();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ListaBuses getListaBuses() {
        return listaBuses;
    }

    public ListaUsuarios getListaUsuarios() {
        return listaUsuarios;
    }
}