/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Alexis Chapal
 */
public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
    public UsuarioNoEncontradoException() {
        super("El usuario no fue encontrado.");
    }
}
