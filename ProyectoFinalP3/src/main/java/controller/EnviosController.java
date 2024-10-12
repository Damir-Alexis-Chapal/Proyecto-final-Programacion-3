/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Transaccion;
import model.Usuario;
import view.Envios;
import view.Sistema;
//AQUI SE MANEJA EL CONTROLLER DE ENVIOS
/**
 *
 * @author angel
 */
public class EnviosController {
    
    public static final EnviosController instancia = new EnviosController();
    
    private EnviosController() {
    }

    public static EnviosController obtenerInstancia() {
        return instancia;
    }
    
    public void setearDatosTransaccion (Transaccion transaccion){ //CON ESTO SE SETEAN LOS PRIMEROS 4 DATOS DE LA PARTE DE ENVIO
        
        Envios envios = view.Envios.obtenerInstancia();
        envios.txtTipoTransaccion.setText(String.valueOf(2));
        envios.txtIdTransaccion.setText(String.valueOf(transaccion.getIdTransaccion()));
        envios.txtFechaTransaccion.setText(String.valueOf(transaccion.getFecha().getDayOfYear()+"-"+transaccion.getFecha().getMonth() + "-"+transaccion.getFecha().getYear()));
        envios.txtHoraTransaccion.setText(String.valueOf(transaccion.getFecha().getHour()));
        
    }
    
    public void setearDatosUsuario(Usuario usuario) {
        Envios envios = view.Envios.obtenerInstancia();
        envios.txtIdCliente.setText(String.valueOf(usuario.getIdUsuario()));
        
    }
    
}
