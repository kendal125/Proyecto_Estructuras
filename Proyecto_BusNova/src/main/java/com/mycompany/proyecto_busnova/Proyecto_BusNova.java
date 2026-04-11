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

            opcion = Integer.parseInt(entrada);

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
                    JOptionPane.showMessageDialog(null, "Opcion en desarrollo");
                    break;
                    
                case 8:
                    JOptionPane.showMessageDialog(null, "Opcion en desarrollo");
                    break;
                    
                case 9:
                    JOptionPane.showMessageDialog(null, sistema.mostrarTiquetes());
                    break;

                case 10:
                    sistema.guardarConfiguracion();
                    sistema.guardarTiquetes();
                    sistema.guardarColas();
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                    
            
                default:
                    JOptionPane.showMessageDialog(null, "Opción no permitida.");
            }

        } while (opcion != 10);
    }
}

