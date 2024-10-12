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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

    public static void guardarRegistroLog(String mensajeLog, int nivel, String accion, String rutaArchivo)
    {
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler =  null;

        try {
            fileHandler = new FileHandler(rutaArchivo,true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            switch (nivel) {
                case 1:
                    LOGGER.log(Level.INFO,accion+","+mensajeLog) ;
                    break;

                case 2:
                    LOGGER.log(Level.WARNING,accion+","+mensajeLog) ;
                    break;

                case 3:
                    LOGGER.log(Level.SEVERE,accion+","+mensajeLog) ;
                    break;

                default:
                    break;
            }

        } catch (SecurityException e) {

            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            LOGGER.log(Level.SEVERE,e.getMessage());
            e.printStackTrace();
        }
        finally {

            fileHandler.close();
        }

    }



}
