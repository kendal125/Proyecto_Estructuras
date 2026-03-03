package com.mycompany.proyecto_busnova;

/**
 *
 * @author lopez
 */
import javax.swing.JOptionPane;

public class Proyecto_BusNova {

    public static void main(String[] args) {

        Sistema sistema = new Sistema();

        if (!sistema.existeConfig()) {

            String nombre = JOptionPane.showInputDialog("Ingrese nombre de la terminal:");
            if (nombre == null) return;

            sistema.setNombreTerminal(nombre);

            int cantidad = Integer.parseInt(
                    JOptionPane.showInputDialog("Cantidad total de buses (mínimo 2):"));

            if (cantidad < 2) {
                JOptionPane.showMessageDialog(null, "Debe haber mínimo 2 buses.");
                return;
            }

            sistema.crearBuses(cantidad);

            int cantUsuarios = Integer.parseInt(
                    JOptionPane.showInputDialog("Cantidad de usuarios:"));

            for (int i = 0; i < cantUsuarios; i++) {
                String user = JOptionPane.showInputDialog("Username:");
                String pass = JOptionPane.showInputDialog("Password:");
                sistema.agregarUsuario(user, pass);
            }

            sistema.guardarConfiguracion();

            JOptionPane.showMessageDialog(null, "Sistema configurado correctamente.");
        }

        int opcion;

        do {

            String menu = "TERMINAL: " + sistema.getNombreTerminal() + "\n\n"
                    + "1. Agregar bus\n"
                    + "2. Cambiar nombre terminal\n"
                    + "3. Salir";

            String entrada = JOptionPane.showInputDialog(menu);

            if (entrada == null) return;

            opcion = Integer.parseInt(entrada);

            switch (opcion) {

                case 1:
                    sistema.agregarBusExtra();
                    JOptionPane.showMessageDialog(null, "Bus agregado correctamente.");
                    break;

                case 2:
                    String nuevo = JOptionPane.showInputDialog("Nuevo nombre:");
                    if (nuevo != null && !nuevo.trim().equals("")) {
                        sistema.cambiarNombreTerminal(nuevo);
                        JOptionPane.showMessageDialog(null, "Nombre actualizado.");
                    }
                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }

        } while (opcion != 3);
    }
}
