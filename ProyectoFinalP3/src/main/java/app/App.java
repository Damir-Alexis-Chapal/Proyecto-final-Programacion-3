/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package app;

import view.InicioSesion;



/**
 *
 * @author Alexis Chapal
 */
public class App {

    public static void main(String[] args) {
        InicioSesion login = new InicioSesion();
        
        login.setVisible(true);
        System.out.println("hola mundo");
    }
}
