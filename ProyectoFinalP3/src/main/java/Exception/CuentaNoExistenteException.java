/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

/**
 *
 * @author Alexis Chapal
 */
public class CuentaNoExistenteException extends Exception {

    public CuentaNoExistenteException(String mensaje) {
        super(mensaje);
    }

    public CuentaNoExistenteException() {
        super("La cuenta de destino no existe.");
    }
}
