/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import app.Wallet;
import java.io.IOException;
import model.Usuario;
import persistencia.ArchivoUtil;
import view.Sistema;

/**
 *
 * @author Alexis Chapal
 */
public class SystemController {

    private static final SystemController instancia = new SystemController();

    private SystemController() {

    }

    public static SystemController obtenerInstancia() {
        return instancia;
    }

    public void setearDatosUsuario(Usuario usuario) {
        Sistema sistema = view.Sistema.obtenerInstancia();
        sistema.txtNombre.setText(usuario.getNombreCompleto());
        sistema.txtCorreo.setText(usuario.getCorreoElectronico());
        sistema.txtTelefono.setText(usuario.getNumeroTelefono());
        sistema.txtDireccion.setText(usuario.getDireccion());
        sistema.jbNombreUsuario.setText(usuario.getCorreoElectronico());
        sistema.jbSaldoDisponible.setText("$" + String.format("%.2f", usuario.getSaldoTotal()));

    }

    public void editarUsuario(Usuario usuario) throws IOException {
        Wallet wallet = Wallet.obtenerInstancia();
        int idUsuario = usuario.getIdUsuario()-1;
        wallet.editarUsuario(idUsuario, usuario);
        ArchivoUtil.guardarRegistroLog("Se edito perfil del usuario", 1, "editar nombre usuario", "C:\\td\\persistencia\\log\\registroApp.log");

    }

}
