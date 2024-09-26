/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.Sign;
import model.Cuenta;
import app.Wallet;
import model.Usuario;

/**
 *
 * @author Alexis Chapal
 */
public class SignController {

    private static final SignController instancia = new SignController();

    private SignController() {

    }

    public static SignController obtenerInstancia() {
        return instancia;
    }

    public void agregarUsuario(Usuario usuario) {
        Wallet wallet = Wallet.obtenerInstancia();
        wallet.agregarUsuario(usuario);
        System.out.println(usuario.mostrarInformacionUsuario());
        System.out.println("Usuario agregado");

    }
}
