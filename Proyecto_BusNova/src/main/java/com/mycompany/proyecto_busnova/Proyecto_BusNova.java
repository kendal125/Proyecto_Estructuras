package com.mycompany.proyecto_busnova;

import javax.swing.JOptionPane;

/**
 * Clase principal del sistema BusNova.
 * <p>
 * Contiene el método {@code main} que inicia la ejecución del programa.
 * Se encarga de:
 * <ul>
 *   <li>Inicializar el sistema</li>
 *   <li>Gestionar la autenticación de usuarios</li>
 *   <li>Configurar el sistema si no existe una configuración previa</li>
 *   <li>Mostrar el menú principal de opciones</li>
 * </ul>
 * </p>
 * 
 * <p>
 * El sistema permite administrar una terminal de buses, incluyendo la creación
 * de buses, gestión de usuarios y configuración de la terminal.
 * </p>
 * 
 * @author barra
 * Kendal Lopez corrales
 * Matias Barrantes benavides
 * Steven Mora Ortiz
 * Santiago Barrantes salazar
 */
public class Proyecto_BusNova {

    public static void main(String[] args) {
        
        
       Sistema sistema = new Sistema();
        
        
       Auth auth = new Auth(sistema.getListaUsuarios());

        if (!auth.validarLogin()) {
            JOptionPane.showMessageDialog(null, "Acceso denegado.");
            return;
        }


        if (!sistema.existeConfig()) {

            String nombre = JOptionPane.showInputDialog("Ingrese nombre de la terminal asignada posteriormente:");
            if (nombre == null) return;

            sistema.setNombreTerminal(nombre);

            int cantidad = Integer.parseInt(
                    JOptionPane.showInputDialog("Cantidad total de buses (mínimo 2):"));

            if (cantidad < 2) {
                JOptionPane.showMessageDialog(null, " Asigne una nueva cantidad debe haber mínimo 2 buses.");
                return;
            }

            sistema.crearBuses(cantidad);

            int cantUsuarios = Integer.parseInt(
                    JOptionPane.showInputDialog("Cantidad de usuarios:"));

            for (int i = 0; i < cantUsuarios; i++) {
                String user = JOptionPane.showInputDialog("Username:");
                String pass = JOptionPane.showInputDialog("contrasenia:");
                sistema.agregarUsuario(user, pass);
            }

            sistema.guardarConfiguracion();

            JOptionPane.showMessageDialog(null, "Sistema configurado correctamente.");
        }

        int opcion;

        do {

            String menu = "TERMINAL DE BUSES: " + sistema.getNombreTerminal() + "\n\n"
                    + "1. Agregar bus\n"
                    + "2. Cambiar nombre terminal\n"
                    + "3. Salir";

            String entrada = JOptionPane.showInputDialog(menu);

            if (entrada == null) return;

            opcion = Integer.parseInt(entrada);

            switch (opcion) {

                case 1:
                    //sistema.agregarBusExtra();
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cuantos buses desea Agregar: "));
                    sistema.agregarBuses(cantidad);
                    JOptionPane.showMessageDialog(null, "Bus agregado correctamente.");
                    break;

                case 2:
                    String nuevo = JOptionPane.showInputDialog("Nuevo nombre:");
                    if (nuevo != null && !nuevo.trim().equals("")) {
                        sistema.cambiarNombreTerminal(nuevo);
                        JOptionPane.showMessageDialog(null, "Su nombre asignado ha sido actualizado de manera exitosa.");
                    }
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no permitida.");
            }

        } while (opcion != 3);
    }
}

