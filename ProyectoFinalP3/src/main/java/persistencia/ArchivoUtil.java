/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Usuario;

/**
 *
 * @author Alexis Chapal
 */
public class ArchivoUtil {

    static void guardarArchivo(String rutaUsuario, String info, boolean ban) throws IOException {
        FileWriter fw = new FileWriter(rutaUsuario, ban);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(info);
        bfw.close();
        fw.close();

    }

    static ArrayList<String> leerArchivo(String rutaUsuario) throws FileNotFoundException, IOException {
        ArrayList<String> contenido = new ArrayList<String>();
        //abrir conexion
        FileReader fr = new FileReader(rutaUsuario);
        BufferedReader bfr = new BufferedReader(fr);
        // leer
        String linea = "";
        while ((linea = bfr.readLine()) != null) {
            contenido.add(linea);
        }
        //cerrar
        bfr.close();
        fr.close();
        return contenido;
    }
}
