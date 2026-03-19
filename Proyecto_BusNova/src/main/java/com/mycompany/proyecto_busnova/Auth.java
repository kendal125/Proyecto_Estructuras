package com.mycompany.proyecto_busnova;

import javax.swing.JOptionPane;

/**
 * Clase encargada del proceso de autenticación de usuarios dentro del sistema.
 * <p>
 * Permite solicitar credenciales mediante cuadros de diálogo y validar el inicio
 * de sesión contra la lista de usuarios registrada.
 * </p>
 */
public class Auth {
    
    /**
     * Lista de usuarios utilizada para validar las credenciales ingresadas.
     */
    private ListaUsuarios listaUsuarios;

     /**
     * Constructor de la clase Auth.
     *
     * @param listaUsuarios lista de usuarios contra la cual se validará el inicio de sesión
     */
    public Auth(ListaUsuarios listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    /**
     * Solicita al usuario sus credenciales mediante ventanas emergentes.
     *
     * @return un arreglo de tipo {@code String} donde la posición 0 contiene el
     * usuario y la posición 1 contiene la contraseña
     */
    public String[] pedirCredenciales() {
        String username = JOptionPane.showInputDialog("Ingrese usuario:");
        String password = JOptionPane.showInputDialog("Ingrese contraseña:");
        return new String[]{ username, password };
    }

    /**
     * Valida el inicio de sesión permitiendo un máximo de tres intentos.
     * <p>
     * Si las credenciales son correctas, muestra un mensaje de bienvenida y
     * retorna {@code true}. Si el usuario cancela el ingreso o supera el número
     * máximo de intentos, retorna {@code false}.
     * </p>
     *
     * @return {@code true} si el usuario inicia sesión correctamente;
     * {@code false} en caso contrario
     */
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