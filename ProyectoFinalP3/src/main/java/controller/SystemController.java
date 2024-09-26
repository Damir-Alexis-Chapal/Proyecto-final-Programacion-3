/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Usuario;
import view.System;


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
        System sistema = view.System.obtenerInstancia();
        sistema.txtNombre.setText(usuario.getNombreCompleto());
        sistema.txtCorreo.setText(usuario.getCorreoElectronico());
        sistema.txtTelefono.setText(usuario.getNumeroTelefono());
        sistema.txtDireccion.setText(usuario.getDireccion());
        sistema.jbNombreUsuario.setText(usuario.getCorreoElectronico());
        sistema.jbSaldoDisponible.setText("$"+String.format("%.2f", usuario.getSaldoTotal()));
        
        
    }
    
}
