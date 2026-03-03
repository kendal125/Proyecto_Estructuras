
package BusNovaTech;

import javax.swing.JOptionPane;


public class Auth {
    
    private Usuario[] usuarios;

    public Auth(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }
    
    public boolean login(String username, String Password){

            if(username == null || Password == null){
                return false;
            }

            for(int i = 0; i < usuarios.length; i++){
                Usuario user = usuarios[i];
                if (user == null){
                    continue;
                }
                else if (user.getUsername().equals(username) && user.getPassword().equals(Password)){
                    return true;
                }
            }
        return false;
    }
    
    public boolean validarLogin(){
        int intentosMaximos = 3;
        int contador = 0;
        
        do{
            String user = JOptionPane.showInputDialog("Ingrese Usuario: ");
            String password = JOptionPane.showInputDialog("Contrasenia: ");
            
            if (login(user, password)) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + user);
                return true;
            } 
            else {
                contador++;
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas.\nIntentos restantes: " + (intentosMaximos - contador));
            }

        } while (contador < intentosMaximos);
        
        
        return false;
    }
    
}