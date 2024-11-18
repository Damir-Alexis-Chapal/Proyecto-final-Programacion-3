/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocket;

/**
 *
 * @author Alexis Chapal
 */
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import model.Transaccion;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente implements Runnable {

    static Transaccion trs;

    public Cliente(Transaccion ts) {
        this.trs = ts;
    }

    public void run() {

        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            out.writeObject(trs);
            System.out.println("Transacción enviada al servidor");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
