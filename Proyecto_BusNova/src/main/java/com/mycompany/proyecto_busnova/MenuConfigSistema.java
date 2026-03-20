/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_busnova;

import javax.swing.JOptionPane;
        
public class MenuConfigSistema {
    public static void mostrarMenu(Sistema sistema) {

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

    private static void mostrarConfiguracion(Sistema sistema) {
        String info = "CONFIGURACIÓN ACTUAL\n\n"
                + "Terminal: " + sistema.getNombreTerminal() + "\n"
                + "Buses: " + sistema.getListaBuses().getCantidad() + "\n"
                + "Usuarios: " + sistema.getListaUsuarios().getUsuarios().length;

        JOptionPane.showMessageDialog(null, info);
    }

    private static void cambiarNombre(Sistema sistema) {
        String nombre = JOptionPane.showInputDialog("Nuevo nombre de la terminal:");

        if (nombre != null && !nombre.trim().isEmpty()) {
            sistema.cambiarNombreTerminal(nombre);
            JOptionPane.showMessageDialog(null, "Nombre actualizado.");
        } else {
            JOptionPane.showMessageDialog(null, "Nombre inválido.");
        }
    }
}
