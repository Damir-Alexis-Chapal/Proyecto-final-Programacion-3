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
    
    double idTransaccion;
    LocalDateTime fecha;
    double monto;
    String descripcion;
    TipoTransaccion tipoTransaccion;
    Cuenta cuentaEntrada;
    Cuenta cuentaSalida;
    
    
    //METODO PARA LISTAR LAS IDS DE LAS TRANSACCIONES
    public static ArrayList<Double> listaIds = new ArrayList<>();
    private static double proximoId= 1;
    protected double identificadorTransaccion;
    
    public static double generarIdTransaccion(){
        if(listaIds.isEmpty()){
            for (int i= 0; i< 100000; i++){ //CON ESTO ESTABLECEMOS EL LIMITE DE IDS A CREAR (1.000.000)
                listaIds.add(proximoId++);
            }
        }return listaIds.remove(0); //CON ESTO SE AGREGA EL PRIMER ID DEL ARRAY
    }
    
    public Transaccion(){
        
    }
    
    public Transaccion (double idTransaccion, LocalDateTime fecha,double monto, String descripcion, Cuenta cuentaEntrada, Cuenta cuentaSalida, TipoTransaccion tipoTransaccion){
        this.idTransaccion = generarIdTransaccion();//AQUI SE ASIGNO AL ID EL GENERADOR DE IDS
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion; 
        this.cuentaEntrada = cuentaEntrada;
        this.cuentaSalida = cuentaSalida;
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuenta getCuentaEntrada() {
        return cuentaEntrada;
    }

    public void setCuentaEntrada(Cuenta cuentaEntrada) {
        this.cuentaEntrada = cuentaEntrada;
    }

    public Cuenta getCuentaSalida() {
        return cuentaSalida;
    }

    public void setCuentaSalida(Cuenta cuentaSalida) {
        this.cuentaSalida = cuentaSalida;
    }
    
    

    @Override
    public String toString() {
        return "Transaccion{" 
                + "idTransaccion=" + idTransaccion + '\''
                + ", fecha=" + fecha + '\''
                + ", monto=" + monto + '\''
                + ", descripcion=" + descripcion + '\''
                + ", tipoTransaccion=" + tipoTransaccion + '\''
                + ", cuentaEntrada=" + cuentaEntrada + '\''
                + ", cuentaSalida=" + cuentaSalida + '}';
    }
}

