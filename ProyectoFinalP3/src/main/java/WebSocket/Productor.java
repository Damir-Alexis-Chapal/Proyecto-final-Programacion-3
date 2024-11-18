/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocket;

import model.Transaccion;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.google.gson.Gson;
import com.rabbitmq.client.MessageProperties;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Alexis Chapal
 */
public class Productor implements Runnable {

    private static final String QUEUE_NAME = "operaciones_financieras";

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor de sockets iniciado en el puerto 12345...");

            // Configurar conexión a RabbitMQ
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setUsername("iFurina"); // o tu usuario específico
            factory.setPassword("arle");
            try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
                channel.queueDelete(QUEUE_NAME);
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                System.out.println("Conexión con RabbitMQ establecida.");

                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new Thread(new ClienteHandler(clientSocket, channel)).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClienteHandler implements Runnable {

        private final Socket clientSocket;
        private final Channel channel;
        private final Gson gson = new Gson(); // Para convertir objetos a JSON

        public ClienteHandler(Socket clientSocket, Channel channel) {
            this.clientSocket = clientSocket;
            this.channel = channel;
        }

        @Override
        public void run() {
            try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
                // Leer el objeto del cliente
                Transaccion trs = (Transaccion) in.readObject();
                System.out.println("Transaccion recibida en el servidor");

                // Convertir a JSON
                String mensajeJson = gson.toJson(trs);

                // Publicar en RabbitMQ
                channel.basicPublish("", QUEUE_NAME, MessageProperties.TEXT_PLAIN, mensajeJson.getBytes());
                
                
                System.out.println("Transacción enviada a RabbitMQ");
                Thread thread = new Thread(new Consumidor());
                thread.start();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
