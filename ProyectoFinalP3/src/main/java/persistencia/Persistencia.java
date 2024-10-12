/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import app.Wallet;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import model.Cuenta;
import model.Transaccion;
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
    String rutaTransaccion = "";

    public void guardarUsuario(Usuario usuario) throws IOException {

        rutaUsuario = obtenerRutaProperties();

        StringBuilder txtUsuario = new StringBuilder();
        txtUsuario.append(usuario.getIdUsuario() + "@@");
        txtUsuario.append(usuario.getNombreCompleto() + "@@");
        txtUsuario.append(usuario.getCorreoElectronico() + "@@");
        txtUsuario.append(usuario.getNumeroTelefono() + "@@");
        txtUsuario.append(usuario.getDireccion() + "@@");
        txtUsuario.append(usuario.getSaldoTotal() + "\n");

        ArchivoUtil.guardarArchivo(rutaUsuario, txtUsuario.toString(), false);
    }

    private String obtenerRutaProperties() {
        Properties properties = new Properties();
        try {
            //aqui se debe cambiar la ruta del método (argumento) por la ruta en la que se haya almacenado el archivo properties en su pc
            //en mi caso es el del ejemplo
            properties.load(new FileInputStream(new File("C:\\Users\\angel\\proyectoFinal\\Proyecto-final-Programacion-3\\config.properties")));
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
//        LinkedList<Cuenta> cuenta;
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(rutaUsuario);

        for (String txtUsuario : contenido) {
            String[] split = txtUsuario.split("@@");
            Usuario usuario = new Usuario(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4],
                    Double.parseDouble(split[5]));

            LinkedList<Usuario> usuarios = wallet.getUsuarios();
            usuarios.add(usuario);
        }
        return wallet.getUsuarios();
    }

    public void guardarUsuarios(LinkedList<Usuario> usuarios) throws IOException {
        rutaUsuario = obtenerRutaProperties();
        StringBuilder txtUsuario = new StringBuilder();
        for (Usuario usuario : usuarios) {
            txtUsuario.append(usuario.getIdUsuario() + "@@");
            txtUsuario.append(usuario.getNombreCompleto() + "@@");
            txtUsuario.append(usuario.getCorreoElectronico() + "@@");
            txtUsuario.append(usuario.getNumeroTelefono() + "@@");
            txtUsuario.append(usuario.getDireccion() + "@@");
            txtUsuario.append(usuario.getSaldoTotal() + "\n");
        }
        ArchivoUtil.guardarArchivo(rutaUsuario, txtUsuario.toString(), false);

//        ArchivoUtil.guardarRegistroLog("Empleados guardados", 1, "btnAgregarEmpleado", "C://td/log.txt");
    }
    
    public void guardarTransaccion(Transaccion transaccion, Usuario usuario) throws IOException{
        
        rutaUsuario= obtenerRutaProperties();
        
        StringBuilder txtTransaccion = new StringBuilder();
        txtTransaccion.append(transaccion.getIdTransaccion() + "@@");
        txtTransaccion.append(transaccion.getFecha().getHour() + "@@");
        txtTransaccion.append(transaccion.getFecha().getDayOfYear()+"/"+transaccion.getFecha().getMonth() + "/"+transaccion.getFecha().getYear()+ "@@");
        txtTransaccion.append(transaccion.getMonto()+ "@@");
        txtTransaccion.append(usuario.buscarCuenta(usuario.getCuentasBancarias(), transaccion.getCuentaEntrada())+ "@@");
        txtTransaccion.append(usuario.getIdUsuario()+ "@@");
        txtTransaccion.append(transaccion.getTipoTransaccion()+ "\n");
        
        //FALTA CODIGO EMPLEADO
        
        ArchivoUtil.guardarArchivo(rutaUsuario, txtTransaccion.toString(), false);
        
    }
    
    private String obtenerRutaTransaccionProperties() { //PARA OBTENER LA RUTA DE TRANSACCIONES
        Properties properties = new Properties();
        try {
            //aqui se debe cambiar la ruta del método (argumento) por la ruta en la que se haya almacenado el archivo properties en su pc
            //en mi caso es el del ejemplo
            properties.load(new FileInputStream(new File("C:\\Users\\angel\\proyectoFinal\\Proyecto-final-Programacion-3\\config.properties")));
            return properties.get("rutaTransferencia").toString();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


//    public void reescribirArchivo(LinkedList<Usuario> listaUsuario) {
//        rutaUsuario = obtenerRutaProperties();
//        StringBuilder txtUsuario = new StringBuilder();
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaUsuario))) {
//            for (Usuario usuario : listaUsuario) {
//                txtUsuario.append(usuario.getIdUsuario() + ",");
//                txtUsuario.append(usuario.getNombreCompleto() + ",");
//                txtUsuario.append(usuario.getCorreoElectronico() + ",");
//                txtUsuario.append(usuario.getNumeroTelefono() + ",");
//                txtUsuario.append(usuario.getDireccion() + ",");
//                txtUsuario.append(usuario.getSaldoTotal() + "\n");
//                
//                bw.write(txtUsuario.toString());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void guardarUsuariosEditados(LinkedList<Usuario> listaUsuarios) throws IOException {
//        rutaUsuario = obtenerRutaProperties();
//        StringBuilder txtUsuario = new StringBuilder();
//        for (Usuario usuario : listaUsuarios) {
//            txtUsuario.append(usuario.getIdUsuario() + ",");
//            txtUsuario.append(usuario.getNombreCompleto() + ",");
//            txtUsuario.append(usuario.getCorreoElectronico() + ",");
//            txtUsuario.append(usuario.getNumeroTelefono() + ",");
//            txtUsuario.append(usuario.getDireccion() + ",");
//            txtUsuario.append(usuario.getSaldoTotal() + "\n");
//        }
//        ArchivoUtil.guardarArchivo(rutaUsuario, txtUsuario.toString(), true);
//    }
}
