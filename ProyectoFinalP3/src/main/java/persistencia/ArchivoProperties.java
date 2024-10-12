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

        // Establecer algunas propiedades (clave-valor)
        //en esta parte deben ingresar la ruta en la que se encuentra el archivo UserData en su pc, aquí funciona con la ruta de mi pc
        propiedades.setProperty("rutaUsuario", "C:\\td\\persistencia\\archivos\\UserData.txt");

        // Intentar escribir el archivo properties
        try (FileOutputStream salida = new FileOutputStream("config.properties")) {
            // Guardar las propiedades en el archivo con un comentario opcional
            propiedades.store(salida, "Configuración de la aplicación");
            System.out.println("Archivo properties creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
