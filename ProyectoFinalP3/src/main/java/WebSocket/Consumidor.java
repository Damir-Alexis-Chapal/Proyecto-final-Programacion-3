/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocket;

import app.Wallet;
import com.rabbitmq.client.*;
import com.google.gson.Gson;
import controller.SystemController;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cuenta;
import model.Transaccion;
import model.Usuario;
import view.Sistema;

/**
 *
 * @author Alexis Chapal
 */
public class Consumidor implements Runnable {

    public static Wallet wallet = Wallet.obtenerInstancia();

    private static final String QUEUE_NAME = "operaciones_financieras";
    private static final int NUM_THREADS = 4;

    public void run() {
        ExecutorService threadPool = Executors.newFixedThreadPool(NUM_THREADS);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("iFurina");
        factory.setPassword("arle");

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Esperando transacción en RabbitMQ...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String mensajeJson = new String(delivery.getBody(), "UTF-8");
                System.out.println("Transacción recibida en RabbitMQ");

                // Convertir JSON a objeto
                Transaccion trs = new Gson().fromJson(mensajeJson, Transaccion.class);

                // Enviar al pool de hilos para procesar el mensaje
                threadPool.submit(() -> {
                    try {
                        procesarMensaje(trs);
                        channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Rechazar mensaje
                        try {
                            channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            };

            channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void procesarMensaje(Transaccion trs) {
        if (trs.getTipoTransaccion().name().equals("TRANSFERENCIA") || trs.getTipoTransaccion().name().equals("DEPOSITO")) {
            boolean ban = false;
            for (Usuario usuario : wallet.getUsuarios()) {
                for (Cuenta cuenta : usuario.getCuentasBancarias()) {
                    if (cuenta.getNumeroCuenta().equals(trs.getCuentaDestino())) {
                        ban = true;
                        break;
                    }
                }
            }
            if (ban) {
                SystemController control = SystemController.obtenerInstancia();
                try {
                    control.guardarTransaccion(trs);

                    System.exit(0);
                } catch (IOException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La cuenta de destino no existe");
            }
        }
        if (trs.getTipoTransaccion().name().equals("RETIRO")) {

            SystemController control = SystemController.obtenerInstancia();
            try {
                control.guardarTransaccion(trs);
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (trs.getTipoTransaccion().name().equals("DEPOSITO")) {
            boolean ban = false;
            for (Usuario usuario : wallet.getUsuarios()) {
                for (Cuenta cuenta : usuario.getCuentasBancarias()) {
                    if (cuenta.getNumeroCuenta().equals(trs.getCuentaDestino())) {
                        ban = true;
                        break;
                    }
                }
            }
            if (ban) {
                SystemController control = SystemController.obtenerInstancia();
                try {
                    control.guardarTransaccion(trs);

                    System.exit(0);
                } catch (IOException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La cuenta de destino no existe");
            }
        }

    }
}
