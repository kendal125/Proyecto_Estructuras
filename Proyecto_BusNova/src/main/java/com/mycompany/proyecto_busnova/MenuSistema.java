/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import javax.swing.JOptionPane;

/**
 * Clase encargada de mostrar los menús del sistema.
 * <p>
 * Proporciona diferentes menús interactivos mediante cuadros de diálogo
 * para gestionar la configuración, buses y usuarios del sistema.
 * </p>
 * * <p>
 * Utiliza la clase {@link Sistema} para ejecutar las operaciones correspondientes
 * según la opción seleccionada por el usuario.
 * </p>
 *
 */
public class MenuSistema {
    
    /**
     * Muestra el menú de configuración del sistema.
     * <p>
     * Permite visualizar la configuración actual, cambiar el nombre de la terminal
     * y guardar los cambios.
     * </p>
     *
     * @param sistema instancia del sistema a configurar
     */
    public static void mostrarMenuConfig(Sistema sistema) {

        int opcion = 0;

        do {
            String menu = "CONFIGURACIÓN DEL SISTEMA\n\n"
                    + "1. Ver configuración actual\n"
                    + "2. Cambiar nombre de terminal\n"
                    + "3. Guardar configuración\n"
                    + "4. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);

            if (seleccion == null) return;

            try {
                opcion = Integer.parseInt(seleccion);

                switch (opcion) {

                    case 1:
                        mostrarConfiguracion(sistema);
                        break;

                    case 2:
                        cambiarNombre(sistema);
                        break;

                    case 3:
                        sistema.guardarConfiguracion();
                        JOptionPane.showMessageDialog(null, "Configuración guardada.");
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null, "Volviendo al menu principal");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }

        } while (opcion != 4);
    }

    /**
     * Muestra la configuración actual del sistema.
     *
     * @param sistema instancia del sistema
     */
    private static void mostrarConfiguracion(Sistema sistema) {
        String info = "CONFIGURACIÓN ACTUAL\n\n"
                + "Terminal: " + sistema.getNombreTerminal() + "\n"
                + "Buses: " + sistema.getListaBuses().getCantidad() + "\n"
                + "Usuarios: " + sistema.getListaUsuarios().getUsuarios().length;

        JOptionPane.showMessageDialog(null, info);
    }

    /**
     * Permite cambiar el nombre de la terminal.
     *
     * @param sistema instancia del sistema
     */
    private static void cambiarNombre(Sistema sistema) {
        String nombre = JOptionPane.showInputDialog("Nuevo nombre de la terminal:");

        if (nombre != null && !nombre.trim().isEmpty()) {
            sistema.cambiarNombreTerminal(nombre);
            JOptionPane.showMessageDialog(null, "Nombre actualizado.");
        } else {
            JOptionPane.showMessageDialog(null, "Nombre inválido.");
        }
    }
    
    /**
     * Muestra el menú de administración de buses.
     * <p>
     * Permite visualizar buses, agregar nuevos y consultar la cantidad total.
     * </p>
     *
     * @param sistema instancia del sistema
     */
    public static void mostrarMenuAdminBuses(Sistema sistema){
        int opcion = 0;
        
        do{
            String menu = "ADMINISTRAR BUSES\n\n"
                    + "1. Ver buses registrados\n"
                    + "2. Agregar buses normales\n"
                    + "3. Ver cantidad total de buses\n"
                    + "4. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);
            if (seleccion == null) return;
            
            opcion = Integer.parseInt(seleccion);
            try {
                switch(opcion){
                    case 1:
                        JOptionPane.showMessageDialog(null, sistema.mostrarBuses());
                        break;
                        
                    case 2:
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cuantos buses desea Agregar: "));
                        sistema.agregarBuses(cantidad);
                        JOptionPane.showMessageDialog(null, "Bus agregado correctamente.");
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Cantidad de buses actual: " + sistema.getListaBuses().getCantidad() + "\n");
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null, "Volviendo al menu principal");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero.");
            }
        } while (opcion != 4);
    }

    /**
     * Muestra el menú de administración de usuarios.
     *
     * @param sistema instancia del sistema
     */
    public static void mostrarMenuAdminUsuarios(Sistema sistema) {
        int opcion = 0;

        do {
            String menu = "ADMINISTRAR Usuarios\n\n"
                    + "1. Ver usuarios registrados \n"
                    + "2. Agregar usuarios\n"
                    + "3. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);
            if (seleccion == null) {
                return;
            }

            opcion = Integer.parseInt(seleccion);
            try {
                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, sistema.mostrarUsuarios());
                        break;

                    case 2:
                        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario: ");
                        String password = JOptionPane.showInputDialog("Ingrese la contrasenia: ");

                        if (sistema.existeUsuario(username)) {
                            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                        } else {
                            sistema.agregarUsuario(username, password);
                        }

                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Volviendo al menu principal");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero.");
            }
        } while (opcion != 3);
    }

    /**
     * Muestra el menú de gestión de tiquetes.
     * <p>
     * Permite acceder a las opciones de creación de nuevos tiquetes.
     * </p>
     *
     * @param sistema instancia del sistema
     */
    public static void mostrarMenuTiquetes(Sistema sistema) {
        int opcion = 0;

        do {
            String menu = "GESTION DE TIQUETES\n\n"
                    + "1. Crear tiquete\n"
                    + "2. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);
            if (seleccion == null) {
                return;
            }

            try {
                opcion = Integer.parseInt(seleccion);

                switch (opcion) {

                    case 1:
                        crearTiqueteUI(sistema);
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "Volviendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }

        } while (opcion != 2);
    }
    
    /**
     * Interfaz de usuario para recopilar los datos y crear un nuevo tiquete.
     * <p>
     * Solicita datos del cliente y asigna el tiquete a un bus mediante la clase Sistema.
     * </p>
     *
     * @param sistema instancia del sistema
     */
    private static void crearTiqueteUI(Sistema sistema) {
        try{
             String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre es obligatorio.");
                return;
            }

            String id = JOptionPane.showInputDialog("Ingrese el ID del tiquete:");
            if (id == null || id.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El ID es obligatorio.");
                return;
            }

            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));
            if (edad <= 0) {
                JOptionPane.showMessageDialog(null, "La edad debe ser mayor a 0.");
                return;
            }

            String[] monedas = {"COLONES", "DOLARES"};
            String monedaCuenta = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione la moneda de la cuenta:",
                    "Moneda",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    monedas,
                    monedas[0]
            );

            if (monedaCuenta == null) {
                return;
            }

            String[] servicios = {"VIP", "REGULAR", "CARGA", "EJECUTIVO"};
            String tipoStr = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione el tipo de servicio:",
                    "Servicio",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    servicios,
                    servicios[0]
            );

            if (tipoStr == null) {
                return;
            }

            Tiquete.TipoServicio tipoServicio = Tiquete.TipoServicio.valueOf(tipoStr);

            double pesoCarga = 0.0;
            if (tipoServicio == Tiquete.TipoServicio.CARGA) {
                pesoCarga = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el peso de la carga:"));
                if (pesoCarga < 0) {
                    JOptionPane.showMessageDialog(null, "El peso no puede ser negativo.");
                    return;
                }
            }

            Tiquete tiquete = new Tiquete(
                    id,
                    nombre,
                    edad,
                    monedaCuenta,
                    tipoServicio,
                    pesoCarga,
                    sistema.getNombreTerminal()
            );

            sistema.asignarTicketABus(tiquete);
        }catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear tiquete: " + e.getMessage());
        }
    }
    
    /**
     * Interfaz de usuario para solicitar el ID del bus y procesar el abordaje.
     *
     * @param sistema instancia del sistema
     */
    private static void abordarUI(Sistema sistema) {
        int busId = Integer.parseInt(JOptionPane.showInputDialog("Bus ID:"));
        sistema.abordar(busId);
    }

    /**
     * Muestra el submenú para la creación de un tiquete y su asignación automática.
     *
     * @param sistema instancia del sistema
     */
    public static void mostrarMenuCrearTiquete(Sistema sistema) {
        crearTiqueteUI(sistema);
    }

    /**
     * Muestra el submenú para gestionar el abordaje o la atención de un tiquete.
     *
     * @param sistema instancia del sistema
     */
    public static void mostrarMenuAbordarTiquete(Sistema sistema) {
        try {
            int busId = Integer.parseInt(
                    JOptionPane.showInputDialog("Ingrese ID del bus para abordar el siguiente tiquete:")
            );

            sistema.abordar(busId);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }
    
    /**
     * Muestra en pantalla el tipo de cambio del dólar consultado desde el BCCR.
     *
     * @param sistema instancia del sistema principal
     */
    public static void mostrarMenuBCCR(Sistema sistema) {
        int opcion = 0;

        do {
            String menu = "CONSULTA BCCR\n\n"
                    + "1. Consultar tipo de cambio de venta\n"
                    + "2. Consultar tipo de cambio de compra\n"
                    + "3. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);

            if (seleccion == null) {
                return;
            }

            try {
                opcion = Integer.parseInt(seleccion);

                switch (opcion) {
                    case 1:
                        double venta = sistema.consultarTipoCambioVenta();
                        JOptionPane.showMessageDialog(
                                null,
                                "TIPO DE CAMBIO BCCR\n\n"
                                + "Tipo de cambio de venta: ₡" + venta
                        );
                        break;

                    case 2:
                        double compra = sistema.consultarTipoCambioCompra();
                        JOptionPane.showMessageDialog(
                                null,
                                "TIPO DE CAMBIO BCCR\n\n"
                                + "Tipo de cambio de compra: ₡" + compra
                        );
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Volviendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                        break;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "No se pudo consultar el tipo de cambio en este momento.\n"
                        + "Detalle: " + e.getMessage()
                );
            }

        } while (opcion != 3);
    }

    /**
     * Muestra en pantalla el estado actual de las colas de todos los buses.
     *
     * @param sistema instancia del sistema principal
     */
    public static void mostrarMenuConsultarColas(Sistema sistema) {
        JOptionPane.showMessageDialog(null, sistema.mostrarColas());
    }
    
    /**
     * Muestra el historial de tiquetes que ya fueron comprados (atendidos).
     * <p>
     * Consulta el archivo de persistencia mediante el sistema para obtener
     * el registro de todas las ventas completadas, incluyendo su fecha y hora.
     * </p>
     *
     * @param sistema instancia del sistema principal
     */
    public static void mostrarHistorial(Sistema sistema) {
        Tiquete[] atendidos = sistema.cargarAtendidos();
        
        if (atendidos == null || atendidos.length == 0) {
            JOptionPane.showMessageDialog(null, "Aún no hay historial de tiquetes comprados.");
            return;
        }

        String texto = "HISTORIAL DE TIQUETES (ATENDIDOS)\n\n";
        
        // Formato para mostrar: Día/Mes/Año Hora:Minuto AM/PM
        java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm a");
        
        for (Tiquete t : atendidos) {
            // Se valida por seguridad si la fecha existe
            String fechaFormateada = (t.getHoraAtencion() != null) 
                    ? formatoFecha.format(t.getHoraAtencion()) 
                    : "Fecha no registrada";

            texto += "ID Tiquete: " + t.getId()
                  + " | Servicio: " + t.getTipoServicio()
                  + " | Bus ID: " + t.getBusAsignadoId()
                  + " | Cobrado: " + t.getPrecioCalculado()
                  + "\nFecha y hora: " + fechaFormateada
                  + "\n-------------------------------------------------\n";
        }

        JOptionPane.showMessageDialog(null, texto);
    }
    
    /**
     * Muestra el menú de gestión de rutas y grafo.
     * <p>
     * Permite agregar localidades, crear rutas, visualizar el grafo,
     * verificar conectividad y calcular rutas más cortas.
     * </p>
     *
     * @param sistema instancia del sistema principal
    */
    public static void mostrarMenuGrafo(Sistema sistema) {

        int opcion = 0;

        do {
            String menu = "RUTAS Y GRAFO\n\n"
                    + "1. Agregar localidad\n"
                    + "2. Agregar ruta\n"
                    + "3. Mostrar grafo\n"
                    + "4. Verificar conexión\n"
                    + "5. Ruta más corta\n"
                    + "6. Volver\n";

            String input = JOptionPane.showInputDialog(menu);

            if (input == null) return;

            try {
                opcion = Integer.parseInt(input);

                switch (opcion) {

                    case 1:
                      int loc = Integer.parseInt(
                                JOptionPane.showInputDialog("ID de la localidad :")
                        );
                        String nombreLoc = JOptionPane.showInputDialog("Nombre de la localidad :");
                        
                        sistema.agregarLocalidad(loc, nombreLoc);
                        JOptionPane.showMessageDialog(null, "Localidad agregada.");
                        break;

                    case 2:
                        int origen = Integer.parseInt(
                                JOptionPane.showInputDialog("Origen:")
                        );
                        int destino = Integer.parseInt(
                                JOptionPane.showInputDialog("Destino:")
                        );
                        double peso = Double.parseDouble(
                                JOptionPane.showInputDialog("Peso:")
                        );

                        sistema.agregarRuta(origen, destino, peso);
                        JOptionPane.showMessageDialog(null, "Ruta agregada.");
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, sistema.mostrarGrafo());
                        break;

                    case 4:
                        int o = Integer.parseInt(JOptionPane.showInputDialog("Origen:"));
                        int d = Integer.parseInt(JOptionPane.showInputDialog("Destino:"));

                        boolean conectado = sistema.estanConectados(o, d);

                        JOptionPane.showMessageDialog(null,
                                conectado ? "Sí están conectados" : "No están conectados");
                        break;

                    case 5:
                        int o2 = Integer.parseInt(JOptionPane.showInputDialog("Origen:"));
                        int d2 = Integer.parseInt(JOptionPane.showInputDialog("Destino:"));

                        JOptionPane.showMessageDialog(null,
                                sistema.rutaMasCorta(o2, d2));
                        break;

                    case 6:
                        JOptionPane.showMessageDialog(null, "Volviendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en entrada de datos.");
            }

        } while (opcion != 6);
    }
}