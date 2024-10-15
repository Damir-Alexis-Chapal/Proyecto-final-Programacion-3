/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import app.Wallet;
import java.beans.XMLEncoder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import model.Banco;
import model.Cuenta;
import model.TipoCuenta;
import model.TipoTransaccion;
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
    String rutaCuentasBancarias = "";
    String rutaUsuarioXML = "";
    String rutaTransaccionXML = "";
    String rutaCuentasXML = "";

    public void guardarUsuario(Usuario usuario) throws IOException {

        rutaUsuario = obtenerRutaProperties("usuario");

        StringBuilder txtUsuario = new StringBuilder();
        txtUsuario.append(usuario.getIdUsuario() + "@@");
        txtUsuario.append(usuario.getNombreCompleto() + "@@");
        txtUsuario.append(usuario.getCorreoElectronico() + "@@");
        txtUsuario.append(usuario.getNumeroTelefono() + "@@");
        txtUsuario.append(usuario.getDireccion() + "@@");
        txtUsuario.append(usuario.getSaldoTotal() + "\n");

        ArchivoUtil.guardarArchivo(rutaUsuario, txtUsuario.toString(), false);
    }

    private String obtenerRutaProperties(String ruta) {
        Properties properties = new Properties();
        try {
            //aqui se debe cambiar la ruta del método (argumento) por la ruta en la que se haya almacenado el archivo properties en su pc
            //en mi caso es el del ejemplo
            properties.load(new FileInputStream(new File("C:\\td\\persistencia\\archivos\\config.properties")));

            if (ruta == "usuario") {
                return properties.get("rutaUsuario").toString();
            } else if (ruta == "transaccion") {
                return properties.get("rutaTransacccion").toString();
            } else if (ruta == "cuenta") {
                return properties.get("rutaCuentas").toString();

            } else if (ruta == "usuarioXML") {
                return properties.get("rutaUsuarioXML").toString();

            } else if (ruta == "transaccionXML") {
                return properties.get("rutaTransacccionXML").toString();

            } else if (ruta == "cuentasXML") {
                return properties.get("rutaCuentasXML").toString();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public LinkedList<Transaccion> cargarTransacciones(Wallet wallet) throws IOException {

        rutaTransaccion = obtenerRutaProperties("transaccion");
        ArrayList<String> listaTransaccion = ArchivoUtil.leerArchivo(rutaTransaccion);

        for (String tr : listaTransaccion) {
            String[] split = tr.split("@@");
            Transaccion transaccionN = new Transaccion();
            TipoTransaccion tipo = transaccionN.obtenerTipoTransaccion(split[0]);
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime fecha = LocalDateTime.parse(split[2], formato);
            transaccionN.setTipoTransaccion(tipo);
            transaccionN.setIdTransaccion(Double.parseDouble(split[1]));
            transaccionN.setFecha(fecha.toString());
            transaccionN.setMonto(Double.parseDouble(split[3]));
            transaccionN.setCuentaOrigen(split[4]);
            transaccionN.setCuentaDestino(split[5]);
            transaccionN.setDescripcion(split[6]);
            transaccionN.setIdentificador(split[7]);

            LinkedList<Transaccion> transaccion = wallet.getTransacccion();
            transaccion.add(transaccionN);
        }

        return wallet.getTransacccion();
    }

    public LinkedList<Usuario> cargarUsuarios(Wallet wallet) throws IOException {

        rutaUsuario = obtenerRutaProperties("usuario");
        rutaCuentasBancarias = obtenerRutaProperties("cuenta");
        ArrayList<String> listaCuentas = ArchivoUtil.leerArchivo(rutaCuentasBancarias);
        ArrayList<String> listaUsuarios = ArchivoUtil.leerArchivo(rutaUsuario);

        TipoCuenta tipo = TipoCuenta.AHORRO;
        Banco banco = Banco.BANCO_ITAU;

        for (String txtUsuario : listaUsuarios) {
            String[] split = txtUsuario.split("@@");
            Usuario usuario = new Usuario(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4],
                    Double.parseDouble(split[5]));

            LinkedList<Cuenta> cuentas = new LinkedList<>();

            for (String account : listaCuentas) {
                String[] splitAccount = account.split("@@");
                Cuenta cuenta = new Cuenta();
                banco = cuenta.obtenerBanco(splitAccount[1]);
                tipo = cuenta.obtenerTipoCuenta(splitAccount[3]);

                cuenta = new Cuenta(Integer.parseInt(splitAccount[0]), banco, splitAccount[2],
                        tipo, Double.parseDouble(splitAccount[4]));
                if (split[0].equals(splitAccount[5])) {
                    cuentas.add(cuenta);
                }

            }
            usuario.setCuentasBancarias(cuentas);
            LinkedList<Usuario> usuarios = wallet.getUsuarios();
            usuarios.add(usuario);
        }
        return wallet.getUsuarios();
    }

    public void guardarUsuarios(LinkedList<Usuario> usuarios) throws IOException {
        rutaUsuario = obtenerRutaProperties("usuario");
        rutaCuentasBancarias = obtenerRutaProperties("cuenta");

        StringBuilder txtUsuario = new StringBuilder();
        StringBuilder txtCuenta = new StringBuilder();
        for (Usuario usuario : usuarios) {
            txtUsuario.append(usuario.getIdUsuario() + "@@");
            txtUsuario.append(usuario.getNombreCompleto() + "@@");
            txtUsuario.append(usuario.getCorreoElectronico() + "@@");
            txtUsuario.append(usuario.getNumeroTelefono() + "@@");
            txtUsuario.append(usuario.getDireccion() + "@@");
            txtUsuario.append(usuario.getSaldoTotal() + "\n");
            for (Cuenta cuenta : usuario.getCuentasBancarias()) {
                txtCuenta.append(cuenta.getIdCuenta() + "@@");
                txtCuenta.append(String.valueOf(cuenta.getBanco()) + "@@");
                txtCuenta.append(cuenta.getNumeroCuenta() + "@@");
                txtCuenta.append(String.valueOf(cuenta.getTipoCuenta()) + "@@");
                txtCuenta.append(String.valueOf(cuenta.getSaldo()) + "@@");
                txtCuenta.append(usuario.getIdUsuario() + "\n");
            }
        }
        ArchivoUtil.guardarArchivo(rutaUsuario, txtUsuario.toString(), false);
        ArchivoUtil.guardarArchivo(rutaCuentasBancarias, txtCuenta.toString(), false);
        ArchivoUtil.guardarRegistroLog("Usuario guardado", 1, "guardarUsuario");
    }

    public void guardarTransaccion(Transaccion transaccion) throws IOException {

        rutaTransaccion = obtenerRutaProperties("transaccion");

        StringBuilder txtTransaccion = new StringBuilder();
        txtTransaccion.append(transaccion.getTipoTransaccion().toString() + "@@");
        txtTransaccion.append(transaccion.getIdTransaccion() + "@@");
        txtTransaccion.append(transaccion.getFecha() + "@@");
        txtTransaccion.append(transaccion.getMonto() + "@@");
        txtTransaccion.append(transaccion.getCuentaOrigen() + "@@");
        txtTransaccion.append(transaccion.getCuentaDestino() + "@@");
        txtTransaccion.append(transaccion.getDescripcion() + "@@");
        txtTransaccion.append(String.valueOf(transaccion.getIdentificador()) + "\n");

        ArchivoUtil.guardarArchivo(rutaTransaccion, txtTransaccion.toString(), true);

    }

    public void guardarCopias(LinkedList<Usuario> listaUsuarios, LinkedList<Transaccion> listaTransacciones) throws FileNotFoundException {
        rutaUsuarioXML = obtenerRutaProperties("usuarioXML");
        rutaTransaccionXML = obtenerRutaProperties("transaccionXML");
        rutaCuentasXML = obtenerRutaProperties("cuentasXML");

        XMLEncoder codificadorUsuarios = new XMLEncoder(new FileOutputStream(rutaUsuarioXML, false));
        codificadorUsuarios.writeObject(listaUsuarios);
        codificadorUsuarios.close();
        System.err.println("Copia usuarios creada");

        XMLEncoder codificadorTransacciones = new XMLEncoder(new FileOutputStream(rutaTransaccionXML, false));
        codificadorTransacciones.writeObject(listaTransacciones);
        codificadorTransacciones.close();
        System.err.println("Copia transacciones creada");
        
        //tuve que crear toda esta cosa solo para guardar las cuentas y solo porque me habia comido una letra al
        //llamar al método obtener ruta, esta parte funcionaría aun sin el try pero la voy a dejar asi
        try {
            System.err.println("Iniciando serialización de cuentas");
            LinkedList<Cuenta> todasLasCuentas = new LinkedList<>();
            for (Usuario usuario : listaUsuarios) {
                LinkedList<Cuenta> cuentas = usuario.getCuentasBancarias();
                System.err.println("Agregando cuentas...");
                for (Cuenta cuenta : cuentas) {
                    todasLasCuentas.add(cuenta);
                    System.err.println("Cuenta agregada!");
                }
            }

            System.err.println("Escribiendo cuentas en XML...");
            XMLEncoder codificadorCuentas = new XMLEncoder(new FileOutputStream(rutaCuentasXML, false));
            codificadorCuentas.writeObject(todasLasCuentas);
            codificadorCuentas.close();
            System.err.println("Copia cuentas creada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
