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
import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
/*
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.CARGA;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.EJECUTIVO;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.REGULAR;
import static com.mycompany.proyecto_busnova.Tiquete.TipoServicio.VIP;
*/

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
     * Lista de ticketes del sistema
     */
    private ListaTiquetes listaTiquetes;

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
        listaTiquetes = new ListaTiquetes();

        if (existeConfig()) {
            cargarConfig();
            cargarTiquetes();
            cargarColas();
            
        } 
        
        if (!listaUsuarios.existeUsuario("admin")) {
        listaUsuarios.agregarUsuario(new Usuario("admin", "admin"));
        guardarConfiguracion();
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
     * <li>ID 1 - tipo 'P' (Preferencial)</li>
     * <li>ID 2 - tipo 'D' (Directo)</li>
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
    //metodo
    public void crearTiquete(Tiquete tiquete, int busId) {
        Bus bus = buscarBus(busId);
        if (bus == null) {
            System.out.println("Bus no encontrado");
            return;
        }
        
        listaTiquetes.agregarTiquete(tiquete);
        tiquete.setBusAsignadoId(busId);
        tiquete.setEstado(Tiquete.Estado.PENDIENTE);

        // si inspector libre y sin fila - atención inmediata
        if (!bus.isInspectorOcupado() && bus.getCola().estaVacia()) {
            atenderTiquete(bus, tiquete);
        } else {
            bus.getCola().encolar(tiquete);
            System.out.println("Tiquete en cola");
        }
        
        guardarTiquetes();
        guardarColas();
    }

    //metodo
    public void atenderTiquete(Bus bus, Tiquete t) {
        bus.setInspectorOcupado(true);
        t.setEstado(Tiquete.Estado.EN_ATENCION);
        double precio = calcularPrecio(t);
        
        t.setPrecioCalculado(precio);
        // aquí podra validar pago extra
        t.setEstado(Tiquete.Estado.ATENDIDO);
        t.setHoraAtencion(new java.util.Date());
        guardarAtendido(t);
        bus.setInspectorOcupado(false);

        System.out.println("Tiquete atendido: " + t.getId());
    }

    //metodo
    public void abordar(int busId) {
        Bus bus = buscarBus(busId);

        if (bus == null) {
            System.out.println("Bus no encontrado");
            return;
        }

        if (bus.isInspectorOcupado()) {
            System.out.println("Inspector ocupado");
            return;
        }

        if (bus.getCola().estaVacia()) {
            System.out.println("No hay personas en fila");
            return;
        }

        try {
            Tiquete tiquete = (Tiquete) bus.getCola().desencolar();
            atenderTiquete(bus, tiquete);
            guardarTiquetes();
            guardarColas();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para asignar un tiquete al bus correcto según tipo de servicio
    public void asignarTicketABus(Tiquete tiquete) {
        Bus busAsignado = null;

        switch (tiquete.getTipoServicio()) {
            case VIP:
                busAsignado = buscarBusPorTipo('P');
                break;
            case EJECUTIVO:
            case REGULAR:
                busAsignado = buscarBusPorTipo('N');
                break;
            case CARGA:
                busAsignado = buscarBusPorTipo('D');
                break;
        }

        if (busAsignado != null) {
            
            listaTiquetes.agregarTiquete(tiquete);
            busAsignado.getCola().encolar(tiquete);
            tiquete.setEstado(Tiquete.Estado.PENDIENTE);
            tiquete.setBusAsignadoId(busAsignado.getId());
            
            guardarTiquetes();
            guardarColas();
            JOptionPane.showMessageDialog(null, "Tiquete asignado al bus ID: " + busAsignado.getId());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un bus disponible para este tiquete.");
        }
    }

// Método auxiliar: buscar primer bus de un tipo
    private Bus buscarBusPorTipo(char tipo) {
        Nodo actual = listaBuses.getPrimero();
        while (actual != null) {
            Bus bus = (Bus) actual.getDato();
            if (bus.getTipo() == tipo) {
                return bus;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /*
// Atender tiquete del bus (desencolar)
    public void atenderTiquete(int busId) {
        Bus bus = buscarBus(busId);
        if (bus == null) {
            JOptionPane.showMessageDialog(null, "Bus no encontrado.");
            return;
        }

        try {
            Tiquete t = (Tiquete) bus.getCola().desencolar();
            t.setEstado(Tiquete.Estado.EN_ATENCION);
            t.setHoraAtencion(new java.util.Date());
            t.setPrecioCalculado(calcularPrecio(t));

            // Guardar como atendido
            guardarAtendido(t);

            JOptionPane.showMessageDialog(null, "Tiquete atendido ID: " + t.getId()
                    + "\nPrecio: $" + t.getPrecioCalculado());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay tiquetes pendientes en el bus.");
        }
    }
*/
// Mostrar todas las colas de todos los buses
    public String mostrarColas() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = listaBuses.getPrimero();

        while (actual != null) {
            Bus bus = (Bus) actual.getDato();
            sb.append("Bus ID: ").append(bus.getId())
                    .append(" | Tipo: ").append(bus.getTipo())
                    .append(" | Tiquetes en cola: ");

            Cola cola = bus.getCola();
            Nodo nodoCola = cola.frente; // Accede al frente directamente

            if (nodoCola == null) {
                sb.append("0\n");
            } else {
                while (nodoCola != null) {
                    Tiquete t = (Tiquete) nodoCola.getDato();
                    sb.append(t.getId()).append(" ");
                    nodoCola = nodoCola.getSiguiente();
                }
                sb.append("\n");
            }
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }

    /**
     * Agrega un nuevo usuario al sistema.
     *
     * @param user nombre de usuario
     * @param pass contraseña del usuario
     */
    public void agregarUsuario(String user, String pass) {
        listaUsuarios.agregarUsuario(new Usuario(user, pass));
        guardarConfiguracion();
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
    //metodo
    public Bus buscarBus(int id) {

        Nodo actual = listaBuses.getPrimero();

        while (actual != null) {
            Bus bus = (Bus) actual.getDato();

            if (bus.getId() == id) {
                return bus;
            }

            actual = actual.getSiguiente();
        }

        return null;
    }

    public void agregarBuses(int cantidadBuses) {

        int idInicial = listaBuses.getCantidad() + 1;

        for (int i = 0; i < cantidadBuses; i++) {
            Bus busNuevo = new Bus(idInicial + i, 'N');
            listaBuses.agregarBus(busNuevo);
        }
        guardarConfiguracion();
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
     * Este método actúa como intermediario entre el menú y la estructura de
     * datos.
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
    public void guardarAtendido(Tiquete tiquete) {
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
            nuevo[existentes.length] = tiquete;

            // sobrescribir archivo con arreglo actualizado
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, nuevo);

            System.out.println("Tiquete guardado en atendidos.json: " + tiquete.getId());
        } catch (Exception e) {
            System.err.println("Error guardando atendido: " + e.getMessage());
        }
    }
    
    public void guardarTiquetes() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Tiquete> lista = new ArrayList<>();
            Nodo actual = listaTiquetes.getCabeza();

            while (actual != null) {
                lista.add((Tiquete) actual.getDato());
                actual = actual.getSiguiente();
            }

            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File("tiquetes.json"), lista);

        } catch (Exception e) {
            System.out.println("Error al guardar tiquetes");
        }
    }
    
    public void cargarTiquetes() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            File file = new File("tiquetes.json");
            if (!file.exists()) return;

            Tiquete[] tiquetes = mapper.readValue(file, Tiquete[].class);

            for (Tiquete t : tiquetes) {
                listaTiquetes.agregarTiquete(t);
            }

        } catch (Exception e) {
            System.out.println("Error al cargar tiquetes");
        }
    }
    
    
    public Tiquete[] cargarAtendidos() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            File file = new File("atendidos.json");
            if (!file.exists()) {
                return new Tiquete[0];
            }

            Tiquete[] atendidos = mapper.readValue(file, Tiquete[].class);
            return atendidos;

        } catch (Exception e) {
            System.out.println("Error al cargar atendidos");
            return new Tiquete[0];
        }
    }
    
    public void guardarColas() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<List<Tiquete>> colas = new ArrayList<>();

            Nodo actualBus = listaBuses.getPrimero();

            while (actualBus != null) {
                Bus bus = (Bus) actualBus.getDato();

                List<Tiquete> colaBus = new ArrayList<>();
                Nodo actualCola = bus.getCola().frente;

                while (actualCola != null) {
                    colaBus.add((Tiquete) actualCola.getDato());
                    actualCola = actualCola.getSiguiente();
                }

                colas.add(colaBus);
                actualBus = actualBus.getSiguiente();
            }

            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File("colas.json"), colas);

        } catch (Exception e) {
            System.out.println("Error al guardar colas");
        }
    }
    
    public void cargarColas() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            File file = new File("colas.json");
            if (!file.exists()) return;

            List<List<Tiquete>> colas = mapper.readValue(
                    file,
                    new com.fasterxml.jackson.core.type.TypeReference<List<List<Tiquete>>>() {}
            );

            Nodo actualBus = listaBuses.getPrimero();
            int i = 0;

            while (actualBus != null && i < colas.size()) {
                Bus bus = (Bus) actualBus.getDato();

                for (Tiquete t : colas.get(i)) {
                    bus.getCola().encolarTiquete(t);
                }

                actualBus = actualBus.getSiguiente();
                i++;
            }

        } catch (Exception e) {
            System.out.println("Error al cargar colas");
        }
    }
    
}
