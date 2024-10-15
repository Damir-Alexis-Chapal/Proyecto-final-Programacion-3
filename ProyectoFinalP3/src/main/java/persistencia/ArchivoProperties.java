/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Alexis Chapal
 */
public class ArchivoProperties {

    public static void main(String[] args) {
        // Crear una instancia de Properties
        Properties propiedades = new Properties();
        
        //rutas para guardar los datos en txt
        propiedades.setProperty("rutaUsuario", "C:\\td\\persistencia\\archivos\\usuarios.txt");
        propiedades.setProperty("rutaTransacccion", "C:\\td\\persistencia\\archivos\\transaciones.txt");
        propiedades.setProperty("rutaCuentas", "C:\\td\\persistencia\\archivos\\cuentasBancarias.txt");
        //rutas para hacer copias de los datos en XML
        propiedades.setProperty("rutaUsuarioXML", "C:\\td\\persistencia\\respaldo\\copiaUsuarios.XMl");
        propiedades.setProperty("rutaTransacccionXML", "C:\\td\\persistencia\\respaldo\\copiaTransaciones.XML");
        propiedades.setProperty("rutaCuentasXML", "C:\\td\\persistencia\\respaldo\\copiaCuentasBancarias.XML");
        //rutas para hacer copias de los datos en binario
        propiedades.setProperty("rutaUsuarioBIN", "C:\\td\\persistencia\\respaldo\\copiaUsuariosBIN");
        propiedades.setProperty("rutaTransacccionBIN", "C:\\td\\persistencia\\respaldo\\copiaTransacionesBIN");
        propiedades.setProperty("rutaCuentasBIN", "C:\\td\\persistencia\\respaldo\\copiaCuentasBancariasBIN");

        // Intentar escribir el archivo properties
        try (FileOutputStream salida = new FileOutputStream("C:\\td\\persistencia\\archivos\\config.properties")) {
            // Guardar las propiedades en el archivo con un comentario opcional
            propiedades.store(salida, "Configuración de la aplicación");
            System.out.println("Archivo properties creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
