package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.SwingConstants;
import model.Cuenta;
import model.Usuario;
import persistencia.Persistencia;
import view.*;

/**
 *
 * @author Alexis Chapal
 */
public class Wallet {

    // usamos singleton para asegurarnos de que solo haya una copia de wallet
    private static final Wallet instancia = new Wallet();

    // Declaración de la lista de usuarios
    public LinkedList<Usuario> listaUsuarios;
    

    // Constructor privado
    private Wallet() {
        // Inicializamos la lista en el constructor
        listaUsuarios = new LinkedList<>();
    }

    public static Wallet obtenerInstancia() {
        return instancia;
    }

    public static void main(String[] args) {
        Login login = Login.obtenerInstancia();
        login.setVisible(true);
    }

    public void agregarUsuario(Usuario usuario) throws IOException {

        listaUsuarios.add(usuario);
        Persistencia persistencia = Persistencia.obtenerInstancia();
        persistencia.guardarUsuarios(listaUsuarios);
    }

    public boolean validarUsuario(String nombreUsuario, String correo) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getNombreCompleto().equals(nombreUsuario)
                    && listaUsuarios.get(i).getCorreoElectronico().equals(correo)) {
                return true;
            }
        }
        return false;
    }

    public Usuario obtenerUsuario(String nombreUsuario, String correo) {

        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getNombreCompleto().equals(nombreUsuario)
                    && listaUsuarios.get(i).getCorreoElectronico().equals(correo)) {
                return listaUsuarios.get(i);
            }
        }
        return null;
    }

    public LinkedList<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    public void editarUsuario(int idUsuario, Usuario usuario) throws IOException {

        listaUsuarios.set(idUsuario, usuario);
        Persistencia persistencia = Persistencia.obtenerInstancia();
        persistencia.guardarUsuarios(listaUsuarios);
    }
    
}
