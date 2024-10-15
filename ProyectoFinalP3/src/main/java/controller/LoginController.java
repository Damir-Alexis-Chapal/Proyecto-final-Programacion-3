/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import persistencia.ArchivoUtil;
import view.Login;
import view.Sign;
import app.Wallet;
import model.Usuario;
import  persistencia.ArchivoUtil;

import java.io.IOException;

/**
 *
 * @author Alexis Chapal
 */
public class LoginController {

    //aplico el patrón singleton al constructor para obtener siempre la misma instancia
    private static final LoginController instancia = new LoginController();

    private LoginController() {
    }

    public static LoginController obtenerInstancia() {
        return instancia;
    }

    public static void mostrarSign() {
        Sign sign = Sign.obtenerInstancia();
        sign.setVisible(true);
    }

    public static boolean validarUsuario(String nombreUsuario, String correo) {
        Wallet wallet = Wallet.obtenerInstancia();
        ArchivoUtil.guardarRegistroLog("Usuario"+nombreUsuario+" entro al sistema", 1, "Login");
        return(wallet.validarUsuario(nombreUsuario, correo));
    }

    public Usuario obtenerUsuario(String nombreUsuario, String correo) {
        Wallet wallet = Wallet.obtenerInstancia();
        return(wallet.obtenerUsuario(nombreUsuario, correo));
        
    }
}
