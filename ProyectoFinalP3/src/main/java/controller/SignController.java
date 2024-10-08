/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.Sign;
import model.Cuenta;
import app.Wallet;
import java.io.IOException;
import java.util.LinkedList;
import model.Usuario;
import persistencia.ArchivoUtil;
import persistencia.Persistencia;

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

    public void agregarUsuario(Usuario usuario) throws IOException {
        Wallet wallet = Wallet.obtenerInstancia();
        wallet.agregarUsuario(usuario);
        System.out.println(usuario.mostrarInformacionUsuario());
        System.out.println("Usuario agregado");
    }

    

}
