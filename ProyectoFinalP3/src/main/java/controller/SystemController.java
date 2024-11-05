/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import app.Wallet;
import java.io.IOException;
import model.Cuenta;
import model.Transaccion;
import model.Usuario;
import utils.ArchivoUtil;
import utils.Persistencia;
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
        sistema.nombreUsuario = (usuario.getNombreCompleto());
        sistema.correoUsuario = (usuario.getCorreoElectronico());
        sistema.usuarioPrueba = usuario;

    }

    public void editarUsuario(Usuario usuario) throws IOException {
        Wallet wallet = Wallet.obtenerInstancia();
        int idUsuario = usuario.getIdUsuario() - 1;
        wallet.editarUsuario(idUsuario, usuario);
        ArchivoUtil.guardarRegistroLog("Se edito perfil del usuario: " + usuario.getNombreCompleto(), 1, "editar nombre usuario");

    }

    public void usarBotonServicios(Usuario usuario, String numero) {
        Sistema sistema = Sistema.obtenerInstancia();
        for (int i = 0; i < usuario.getCuentasBancarias().size(); i++) {
            if (usuario.getCuentasBancarias().get(i).getNumeroCuenta().equals(numero)) {
                sistema.txtBanco.setText(usuario.getCuentasBancarias().get(i).getBanco().toString());
                sistema.txtNumeroCuenta.setText(usuario.getCuentasBancarias().get(i).getNumeroCuenta());
                sistema.txtTipoCuenta.setText(usuario.getCuentasBancarias().get(i).getTipoCuenta().toString());
                sistema.txtSaldoCuenta.setText(String.valueOf(usuario.getCuentasBancarias().get(i).getSaldo()));
            }
        }

    }

    public void guardarTransaccion(Transaccion transaccion) throws IOException {
        Wallet wallet = Wallet.obtenerInstancia();
        Persistencia persistencia = Persistencia.obtenerInstancia();
        wallet.agregarTransaccion(transaccion);

        for (Usuario usuario : wallet.getUsuarios()) {
            for (Cuenta cuenta : usuario.getCuentasBancarias()) {

                if (cuenta.getNumeroCuenta().equals(transaccion.getCuentaDestino())) {
                    double saldo = cuenta.getSaldo();
                    cuenta.setSaldo(saldo + transaccion.getMonto());
                    usuario.getCuentasBancarias().set(cuenta.getIdCuenta() - 1, cuenta);
                }
                if (cuenta.getNumeroCuenta().equals(transaccion.getCuentaOrigen())) {
                    double saldoDos = cuenta.getSaldo();
                    cuenta.setSaldo(saldoDos - transaccion.getMonto());
                    usuario.getCuentasBancarias().set(cuenta.getIdCuenta() - 1, cuenta);
                }

            }

            wallet.editarUsuario(usuario.getIdUsuario(), usuario);
        }

        persistencia.guardarUsuarios(wallet.getUsuarios());

        System.err.println("Transacción exitosa!");
        System.err.println(transaccion.toString());
    }

}
