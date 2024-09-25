/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alexis Chapal
 */
public class Cuenta {

    private int idCuenta;
    private Banco banco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;

    public Cuenta(int idCuenta, Banco banco, String numeroCuenta, TipoCuenta tipoCuenta) {
        this.idCuenta = idCuenta;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;

    }

    // Getters y Setters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{"
                + "numeroCuenta='" + numeroCuenta + '\''
                + ", tipoCuenta=" + tipoCuenta + ", Banco=" + banco + '}';
    }
}
