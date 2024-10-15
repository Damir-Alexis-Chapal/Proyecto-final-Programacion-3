/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Alexis Chapal
 */
public class Cuenta implements Serializable{

    private int idCuenta;
    private Banco banco;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private double saldo;
    
    public Cuenta(){
        
    }
    public Cuenta(int idCuenta, Banco banco, String numeroCuenta, TipoCuenta tipoCuenta, double saldo) {
        this.idCuenta = idCuenta;
        this.banco = banco;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;

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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public Banco obtenerBanco(String banco){
        Banco bancoN = Banco.BANCO_ITAU;
        
        if (banco.equals("BANCO_NACIONAL")) {
            bancoN = Banco.BANCO_NACIONAL;
        } else if (banco.equals("BANCO_POPULAR")) {
            bancoN = Banco.BANCO_POPULAR;
        } else if (banco.equals("BANCO_SANTANDER")) {
            bancoN = Banco.BANCO_SANTANDER;
        } else if (banco.equals("BBVA")) {
            bancoN = Banco.BBVA;
        } else if (banco.equals("BANCO_ITAU")) {
            bancoN = Banco.BANCO_ITAU;
        }
        return bancoN;
    }
    public TipoCuenta obtenerTipoCuenta(String tipoCuenta){
        
        TipoCuenta tipo = TipoCuenta.AHORRO;
        
        if (tipoCuenta.equals("AHORRO")) {
            tipo = TipoCuenta.AHORRO;
        } else if (tipoCuenta.equals("CORRIENTE")) {
            tipo = TipoCuenta.CORRIENTE;
        } else if (tipoCuenta.equals("INVERSION")) {
            tipo = TipoCuenta.INVERSION;
        }
        return tipo;
        
    }
    @Override
    public String toString() {
        return "CuentaBancaria{"
                + "numeroCuenta='" + numeroCuenta + '\''
                + ", tipoCuenta=" + tipoCuenta + ", Banco=" + banco + '}';
    }
}
