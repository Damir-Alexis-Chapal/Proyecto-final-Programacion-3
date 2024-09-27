/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import app.Wallet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;
import model.Cuenta;
import model.Usuario;

/**
 *
 * @author Alexis Chapal
 */
public class Persistencia {

    // usamos singleton para asegurarnos de que solo haya una copia de wallet
    private static final Persistencia instancia = new Persistencia();

    // Constructor privado
    private Persistencia() {

    }

    public static Persistencia obtenerInstancia() {
        return instancia;
    }

    String rutaUsuario = "";

    public void guardarUsuario(Usuario usuario) throws IOException {

        rutaUsuario = obtenerRutaProperties();

        StringBuilder txtUsuario = new StringBuilder();
        txtUsuario.append(usuario.getIdUsuario() + ",");
        txtUsuario.append(usuario.getNombreCompleto() + ",");
        txtUsuario.append(usuario.getCorreoElectronico() + ",");
        txtUsuario.append(usuario.getNumeroTelefono() + ",");
        txtUsuario.append(usuario.getDireccion() + ",");
        txtUsuario.append(usuario.getSaldoTotal() + "\n");

        ArchivoUtil.guardarArchivo(rutaUsuario, txtUsuario.toString(), true);

    }

    private String obtenerRutaProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("D:/Universidad/codigos 2024-2/Proyecto final-P3/Proyecto-final-Programacion-3/ProyectoFinalP3/config.properties")));
            return properties.get("rutaUsuario").toString();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public LinkedList<Usuario> cargarUsuarios(Wallet wallet) throws IOException {

        rutaUsuario = obtenerRutaProperties();
        LinkedList<Cuenta> cuenta;
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(rutaUsuario);

        for (String txtUsuario : contenido) {
            String[] split = txtUsuario.split(",");
            Usuario usuario = new Usuario(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4],
                    Double.parseDouble(split[5]));

            LinkedList<Usuario> usuarios = wallet.getUsuarios();
            usuarios.add(usuario);
        }
        return wallet.getUsuarios();
    }

}
