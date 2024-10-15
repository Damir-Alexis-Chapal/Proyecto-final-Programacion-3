/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alexis Chapal
 */
public class Usuario implements Serializable {

    // Atributos
    private int idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String numeroTelefono;
    private String direccion;
    private double saldoTotal;

    // Lista de cuentas bancarias asociadas
    private LinkedList<Cuenta> cuentasBancarias;

    public Usuario() {
        this.cuentasBancarias = new LinkedList<>();
    }

    public Usuario(int idUsuario, String nombreCompleto, String correoElectronico,
            String numeroTelefono, String direccion, double saldoTotal) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.direccion = direccion;
        this.saldoTotal = saldoTotal;
        this.cuentasBancarias = new LinkedList<>();
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public LinkedList<Cuenta> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(LinkedList<Cuenta> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public Cuenta buscarCuenta(LinkedList<Cuenta> cuentasBancarias, Cuenta cuenta) {

        for (int i = 0; i < cuentasBancarias.size(); i++) {
            if ((cuentasBancarias.get(i).getNumeroCuenta().equals(cuenta.getNumeroCuenta()))) {
                return cuentasBancarias.get(i);
            }
        }
        return null;
    }

    // Método para mostrar información del usuario
    public String mostrarInformacionUsuario() {
        return "ID Usuario: " + idUsuario
                + "\nNombre Completo: " + nombreCompleto
                + "\nCorreo Electrónico: " + correoElectronico
                + "\nNúmero de Teléfono: " + numeroTelefono
                + "\nDirección: " + direccion
                + "\nSaldo Total: " + String.format("%.2f", saldoTotal)
                + "\nCuentas Bancarias: " + cuentasBancarias;
    }
}
