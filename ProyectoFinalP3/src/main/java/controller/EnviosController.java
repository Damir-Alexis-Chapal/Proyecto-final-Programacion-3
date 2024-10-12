/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import app.Wallet;
import java.time.LocalDateTime;
import model.TipoTransaccion;
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
    
    public void setearDatosTransaccion (Usuario usuario){ //CON ESTO SE SETEAN LOS PRIMEROS 4 DATOS DE LA PARTE DE ENVIO
        
        Wallet wallet = Wallet.obtenerInstancia();
        int idTransferencia = 0;
        Envios envios = new Envios();
        
        if(wallet.listaTransacciones == null){
            
            idTransferencia +=1;
        }
        else{
            idTransferencia = wallet.listaTransacciones.size()+1;
        }
        
        
        TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
        LocalDateTime fecha = LocalDateTime.now();
        
        envios.txtTipoTransaccion.setText(String.valueOf(tipoTransaccion));
        envios.txtIdTransaccion.setText(String.valueOf(idTransferencia));
        envios.txtFechaTransaccion.setText(String.valueOf(fecha));
        
        envios.setVisible(true);
                
    }
    
}
