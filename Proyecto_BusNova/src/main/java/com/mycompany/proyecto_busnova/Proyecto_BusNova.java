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

        try { // NUEVO: protección general del programa

            Sistema sistema = new Sistema();

            if (!sistema.existeConfig()) {

                String nombre = JOptionPane.showInputDialog("Ingrese nombre de la terminal asignada posteriormente:");
                if (nombre == null) return;

                sistema.setNombreTerminal(nombre);

                int cantidad = 0;

                try { // NUEVO: validación de entrada numérica
                    cantidad = Integer.parseInt(
                            JOptionPane.showInputDialog("Cantidad total de buses (mínimo 2):"));

                    if (cantidad < 2) {
                        JOptionPane.showMessageDialog(null, " Asigne una nueva cantidad debe haber mínimo 2 buses.");
                        return;
                    }

                } catch (NumberFormatException e) { // NUEVO: captura error si no es número
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
                    return;
                }

                sistema.crearBuses(cantidad);

                int cantUsuarios = 0;

                try { // NUEVO: validación de número de usuarios
                    cantUsuarios = Integer.parseInt(
                            JOptionPane.showInputDialog("Cantidad de usuarios:"));
                } catch (NumberFormatException e) { // NUEVO
                    JOptionPane.showMessageDialog(null, "Cantidad inválida.");
                    return;
                }

                for (int i = 0; i < cantUsuarios; i++) {
                    String user = JOptionPane.showInputDialog("Username:");
                    String pass = JOptionPane.showInputDialog("contrasenia:");
                    sistema.agregarUsuario(user, pass);
                }

                sistema.guardarConfiguracion();

                JOptionPane.showMessageDialog(null, "Sistema configurado correctamente.");
            }

            Auth auth = new Auth(sistema.getListaUsuarios());

            if (!auth.validarLogin()) {
                JOptionPane.showMessageDialog(null, "Acceso denegado.");
                return;
            }

            int opcion;

            do {

                String menu = "TERMINAL DE BUSES: " + sistema.getNombreTerminal() + "\n\n"
                        + "1. Configuración del sistema \n"
                        + "2. Administrar buses\n"
                        + "3. Administrar usuarios\n"
                        + "4. Gestión de tiquetes\n"
                        + "5. Atención de tiquetes\n"
                        + "6. Consulta de colas\n"
                        + "7. Rutas y grafo\n"
                        + "8. Consulta BCCR\n"
                        + "9. Reportes\n"
                        + "10. Salir\n";

                String entrada = JOptionPane.showInputDialog(menu);

                if (entrada == null) return;

                try { // NUEVO: validación del menú
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) { // NUEVO
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                    opcion = 0; // NUEVO: evita que el programa falle
                    continue; // NUEVO: vuelve al menú
                }

                switch (opcion) {

                    case 1:
                        MenuSistema.mostrarMenuConfig(sistema);
                        break;

                    case 2:
                        MenuSistema.mostrarMenuAdminBuses(sistema);
                        /*
                        String nuevo = JOptionPane.showInputDialog("Nuevo nombre:");
                        if (nuevo != null && !nuevo.trim().equals("")) {
                            sistema.cambiarNombreTerminal(nuevo);
                            JOptionPane.showMessageDialog(null, "Su nombre asignado ha sido actualizado de manera exitosa.");
                        }
                        */
                        break;

                    case 3:
                        MenuSistema.mostrarMenuAdminUsuarios(sistema);
                        break;

                    case 4:
                        MenuSistema.mostrarMenuTiquetes(sistema);
                        break;

                    case 5:
                        MenuSistema.mostrarMenuAbordarTiquete(sistema);
                        break;

                    case 6:
                        MenuSistema.mostrarMenuConsultarColas(sistema);
                        break;

                    case 7:
                        break;

                    case 8:
                        break;

                    case 9:
                        break;

                    case 10:
                        JOptionPane.showMessageDialog(null, "Saliendo...");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no permitida.");
                }

            } while (opcion != 10);

        } catch (Exception e) { // NUEVO: captura cualquier error inesperado
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
        }
    }
}

