package com.mycompany.proyecto_busnova;

import javax.swing.JOptionPane;

public class Auth {

    private ListaUsuarios listaUsuarios;

    public Auth(ListaUsuarios listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String[] pedirCredenciales() {
        String username = JOptionPane.showInputDialog("Ingrese usuario:");
        String password = JOptionPane.showInputDialog("Ingrese contraseña:");
        return new String[]{ username, password };
    }

    public boolean validarLogin() {
        int intentosMaximos = 3;
        int contador = 0;

        while (contador < intentosMaximos) {
            String[] cred = pedirCredenciales();
            String user = cred[0];
            String pass = cred[1];

            if (user == null || pass == null) return false; // cancelado

            if (listaUsuarios.validarLogin(user, pass)) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                return true;
            }

            contador++;
            JOptionPane.showMessageDialog(
                null,
                "Credenciales incorrectas.\nIntentos restantes: " + (intentosMaximos - contador)
            );
        }

        return false;
    }
}