/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Alexis Chapal
 */
public class Transaccion implements Serializable{

    TipoTransaccion tipoTransaccion;
    double idTransaccion;
    String fecha;
    double monto;
    String cuentaOrigen;
    String cuentaDestino;
    String descripcion;
    String identificador;

//    //METODO PARA LISTAR LAS IDS DE LAS TRANSACCIONES
//    public static ArrayList<Double> listaIds = new ArrayList<>();
//    private static double proximoId = 1;

    public Transaccion() {

    }

    public Transaccion(TipoTransaccion tipoTransaccion, double idTransaccion, String fecha,
            double monto, String cuentaOrigen, String cuentaDestino, String descripcion, String id) {
        this.tipoTransaccion = tipoTransaccion;
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.descripcion = descripcion;
        this.identificador = id;

    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
//
//    public static ArrayList<Double> getListaIds() {
//        return listaIds;
//    }
//
//    public static void setListaIds(ArrayList<Double> listaIds) {
//        Transaccion.listaIds = listaIds;
//    }
//
//    public static double getProximoId() {
//        return proximoId;
//    }
//
//    public static void setProximoId(double proximoId) {
//        Transaccion.proximoId = proximoId;
//    }

    @Override
    public String toString() {
        return "Transaccion{"
                + "tipoTransaccion=" + tipoTransaccion + '\''
                + ", idTransaccion=" + idTransaccion + '\''
                + ", fecha=" + fecha + '\''
                + ", monto=" + monto + '\''
                + ", cuentaSalida=" + cuentaOrigen + '\''
                + ", cuentaEntrada=" + cuentaDestino + '\''
                + ", descripcion=" + descripcion + '}';
    }

    public TipoTransaccion obtenerTipoTransaccion(String tipo) {
        TipoTransaccion tipoDos = TipoTransaccion.DEPOSITO;

        if (tipo.equals("DEPOSITO")) {
            tipoDos = TipoTransaccion.DEPOSITO;
        } else if (tipo.equals("RETIRO")) {
            tipoDos = TipoTransaccion.RETIRO;
        } else if (tipo.equals("TRANSFERENCIA")) {
            tipoDos = TipoTransaccion.TRANSFERENCIA;
        }
        return tipoDos;

    }

}
