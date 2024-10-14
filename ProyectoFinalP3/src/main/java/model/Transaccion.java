/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author Alexis Chapal
 */
public class Transaccion {
    
    TipoTransaccion tipoTransaccion;
    double idTransaccion;
    LocalDateTime fecha;
    double monto;
    Cuenta cuentaSalida;
    Cuenta cuentaEntrada;
    String descripcion;
    
    //METODO PARA LISTAR LAS IDS DE LAS TRANSACCIONES
    public static ArrayList<Double> listaIds = new ArrayList<>();
    private static double proximoId= 1;
    protected double identificadorTransaccion;
    
    public Transaccion(){
        
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(double idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cuenta getCuentaSalida() {
        return cuentaSalida;
    }

    public void setCuentaSalida(Cuenta cuentaSalida) {
        this.cuentaSalida = cuentaSalida;
    }

    public Cuenta getCuentaEntrada() {
        return cuentaEntrada;
    }

    public void setCuentaEntrada(Cuenta cuentaEntrada) {
        this.cuentaEntrada = cuentaEntrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static ArrayList<Double> getListaIds() {
        return listaIds;
    }

    public static void setListaIds(ArrayList<Double> listaIds) {
        Transaccion.listaIds = listaIds;
    }

    public static double getProximoId() {
        return proximoId;
    }

    public static void setProximoId(double proximoId) {
        Transaccion.proximoId = proximoId;
    }

    public double getIdentificadorTransaccion() {
        return identificadorTransaccion;
    }

    public void setIdentificadorTransaccion(double identificadorTransaccion) {
        this.identificadorTransaccion = identificadorTransaccion;
    }

    @Override
    public String toString() {
        return "Transaccion{" 
                + "tipoTransaccion=" + tipoTransaccion + '\''
                + ", idTransaccion=" + idTransaccion + '\''
                + ", fecha=" + fecha + '\''
                + ", monto=" + monto + '\''
                + ", cuentaSalida=" + cuentaSalida  + '\''
                + ", cuentaEntrada=" + cuentaEntrada + '\''
                + ", descripcion=" + descripcion + '\''
                + ", identificadorTransaccion=" + identificadorTransaccion + '}';
    }
    
    
    
}

