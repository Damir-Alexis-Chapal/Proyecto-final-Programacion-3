package app;

import controller.WebSocketController;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import model.Cuenta;
import model.Transaccion;
import model.Usuario;
import utils.Persistencia;
import view.*;

/**
 *
 * @author Alexis Chapal
 */
public class Wallet implements Serializable {

    // usamos singleton para asegurarnos de que solo haya una copia de wallet
    private static final Wallet instancia = new Wallet();

    // Declaración de la lista de usuarios
    public static LinkedList<Usuario> listaUsuarios;
    public static LinkedList<Transaccion> listaTransacciones;
    
    // Constructor privado
    private Wallet() {
        // Inicializamos la lista en el constructor
        listaUsuarios = new LinkedList<>();
        listaTransacciones = new LinkedList<>();
    }

    public static Wallet obtenerInstancia() {
        return instancia;
    }

    public static void main(String[] args) {
        
        Persistencia persistencia = Persistencia.obtenerInstancia();

        try {
            persistencia.cargarTransacciones(instancia);
        } catch (Exception e) {
            System.err.println("No se pudieron cargar las transacciones");
        }

        try {
            persistencia.cargarUsuarios(instancia);
          
        } catch (Exception e) {
            System.err.println("No se pudieron cargar los usuarios");
        }

        try {
            persistencia.guardarCopias(listaUsuarios, listaTransacciones);
           
        } catch (Exception e) {
            System.err.println("No se pudieron guardar las copias");
        }

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
        Usuario usuario = new Usuario();
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getNombreCompleto().equals(nombreUsuario)
                    && listaUsuarios.get(i).getCorreoElectronico().equals(correo)) {
                usuario = listaUsuarios.get(i);
            }
        }
        System.err.println("Usuario encontrado, retornando...");
        return usuario;
    }

    public static LinkedList<Usuario> getUsuarios() {
        return listaUsuarios;
    }

    public LinkedList<Transaccion> getTransacccion() {
        return listaTransacciones;
    }

    public void editarUsuario(int idUsuario, Usuario usuario) throws IOException {

        int index = -1;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getIdUsuario() == idUsuario) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            listaUsuarios.set(index, usuario);
            Persistencia persistencia = Persistencia.obtenerInstancia();
            persistencia.guardarUsuarios(listaUsuarios);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void agregarTransaccion(Transaccion transaccion) throws IOException {
        listaTransacciones.add(transaccion);
        Persistencia persistencia = Persistencia.obtenerInstancia();
        persistencia.guardarTransaccion(transaccion);

    }

    public int verificarCuenta(int idCuenta) {
        int index = -1;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            for (int j = 0; j < listaUsuarios.get(i).getCuentasBancarias().size(); j++) {
                if (listaUsuarios.get(i).getCuentasBancarias().get(j).getIdCuenta() == idCuenta) {
                    index = j;
                    break;
                }
            }

        }
        return index;
    }

    public void setUsuarios(LinkedList<Usuario> users) {
        listaUsuarios = users;
        Persistencia persistencia = Persistencia.obtenerInstancia();
        try {
            persistencia.guardarUsuarios(listaUsuarios);
        } catch (IOException ex) {
            Logger.getLogger(Wallet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
