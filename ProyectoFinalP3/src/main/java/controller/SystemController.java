/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import app.Wallet;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
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
        int idUsuario = usuario.getIdUsuario();
        wallet.editarUsuario(idUsuario, usuario);
        ArchivoUtil.guardarRegistroLog("Se editó perfil del usuario: " + usuario.getNombreCompleto(), 1, "editar nombre usuario");

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
        if (transaccion.getTipoTransaccion().name().equals("TRANSFERENCIA") || transaccion.getTipoTransaccion().name().equals("DEPOSITO")) {
            Wallet wallet = Wallet.obtenerInstancia();
            LinkedList<Usuario> users = Wallet.getUsuarios();

            wallet.agregarTransaccion(transaccion);

            boolean actualizacionRealizadaUno = false;
            boolean actualizacionRealizadaDos = false;

            for (Usuario usuario : users) {
                for (Cuenta cuenta : usuario.getCuentasBancarias()) {
                    if (cuenta.getNumeroCuenta().equals(transaccion.getCuentaDestino())) {
                        usuario.setSaldoTotal(usuario.getSaldoTotal() + transaccion.getMonto());
                        cuenta.setSaldo(cuenta.getSaldo() + transaccion.getMonto());
                        actualizacionRealizadaUno = true;
                    }
                    if (cuenta.getNumeroCuenta().equals(transaccion.getCuentaOrigen())) {
                        usuario.setSaldoTotal(usuario.getSaldoTotal() - transaccion.getMonto());
                        cuenta.setSaldo(cuenta.getSaldo() - transaccion.getMonto());
                        actualizacionRealizadaDos = true;
                    }
                }
            }
            // Guarda los cambios en persistencia si hubo alguna actualización
            if (actualizacionRealizadaUno && actualizacionRealizadaDos) {
                wallet.setUsuarios(users);

                System.err.println("transacción exitosa!\n" + transaccion.toString());
                JOptionPane.showMessageDialog(null, "Transacción exitosa!\nInicie sesión nuevamente!\n" + transaccion.toString());
            } else {
                System.err.println("No se encontró ninguna cuenta para la transacción.");
            }
        }
        if (transaccion.getTipoTransaccion().name().equals("RETIRO")) {
            Wallet wallet = Wallet.obtenerInstancia();
            LinkedList<Usuario> users = Wallet.getUsuarios();

            wallet.agregarTransaccion(transaccion);
            boolean actualizacionRealizadaDos = false;

            for (Usuario usuario : users) {
                for (Cuenta cuenta : usuario.getCuentasBancarias()) {
                    if (cuenta.getNumeroCuenta().equals(transaccion.getCuentaOrigen())) {
                        usuario.setSaldoTotal(usuario.getSaldoTotal() - transaccion.getMonto());
                        cuenta.setSaldo(cuenta.getSaldo() - transaccion.getMonto());
                        actualizacionRealizadaDos = true;
                    }
                }
            }
            // Guarda los cambios en persistencia si hubo alguna actualización
            if (actualizacionRealizadaDos) {
                wallet.setUsuarios(users);

                System.err.println("transacción exitosa!\n" + transaccion.toString());
                JOptionPane.showMessageDialog(null, "Transacción exitosa!\nInicie sesión nuevamente!\n" + transaccion.toString());
            } else {
                System.err.println("No se encontró ninguna cuenta para la transacción.");
            }
        }
    }

}
