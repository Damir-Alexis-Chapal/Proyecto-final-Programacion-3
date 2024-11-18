/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import WebSocket.Cliente;
import WebSocket.Productor;
import model.Transaccion;

/**
 *
 * @author Alexis Chapal
 */
public class WebSocketController {

    public void conectarServiciosWebSocket(Transaccion ts) {
        Cliente cliente = new Cliente(ts);
        Thread thread = new Thread(cliente);
        thread.start();
    }

    public void iniciarServidor() {
        Productor productor = new Productor();
        Thread thread = new Thread(productor);
        thread.start();
    }

}
