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
 * 
 * <p>
 * Utiliza la clase {@link Sistema} para ejecutar las operaciones correspondientes
 * según la opción seleccionada por el usuario.
 * </p>
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
                        //sistema.agregarBusExtra();
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
                
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ingrese un numero.");
            }
        }while (opcion != 4);
    }
    
    /**
     * Muestra el menú de administración de usuarios.
     *
     * @param sistema instancia del sistema
     */
    public static void mostrarMenuAdminUsuarios(Sistema sistema){
        int opcion = 0;
        
        do{
            String menu = "ADMINISTRAR Usuarios\n\n"
                    + "1. Ver usuarios registrados \n"
                    + "2. Agregar usuarios\n"
                    + "3. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);
            if (seleccion == null) return;
            
            opcion = Integer.parseInt(seleccion);
            try {
                switch(opcion){
                    case 1:
                        JOptionPane.showMessageDialog(null, sistema.mostrarUsuarios());
                        break;
                        
                    case 2:
                        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario: ");
                        String password = JOptionPane.showInputDialog("Ingrese la contrasenia: ");
                        
                        if (sistema.existeUsuario(username)) {
                            JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                        } 
                        else {
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
                
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ingrese un numero.");
            }
        }while (opcion != 3);
    }
    
    /*
    public static void mostrarMenuTemplate(Sistema sistema){
        int opcion = 0;
        
        do{
            String menu = "ADMINISTRAR Usuarios\n\n"
                    + "1. \n"
                    + "2. \n"
                    + "3. \n"
                    + "4. Volver\n";

            String seleccion = JOptionPane.showInputDialog(menu);
            if (seleccion == null) return;
            
            opcion = Integer.parseInt(seleccion);
            try {
                switch(opcion){
                    case 1:
                        
                        break;
                        
                    case 2:
                        
                        break;
                        
                    case 3: 
                        
                        break;
                        
                    case 4:
                        JOptionPane.showMessageDialog(null, "Volviendo al menu principal");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                        break;
                }
                
            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Ingrese un numero.");
            }
        }while (opcion != 4);
    }
    */
}
