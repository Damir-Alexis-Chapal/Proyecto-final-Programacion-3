/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDateTime;
import model.TipoTransaccion;
import model.Usuario;
import view.Envios;
import view.Servicios;

/**
 *
 * @author angel
 */
public class ServiciosController {
    
   private static final ServiciosController instancia = new ServiciosController();

    private ServiciosController() {
    }

    public static ServiciosController obtenerInstancia() {
        return instancia;
    }
    
    public static void mostrarVentana(Usuario usuario){
        
        Servicios servicios = Servicios.obtenerInstancia();
        servicios.usuario = usuario;
        servicios.setVisible(true);
    }

    public void setearDatosEnvios(Usuario usuario2) {
        
       Envios envios = Envios.obtenerInstancia();
       LocalDateTime fecha;
       LocalDateTime hora;
       TipoTransaccion tipoTransaccion = TipoTransaccion.TRANSFERENCIA;
       
        
    }
}
