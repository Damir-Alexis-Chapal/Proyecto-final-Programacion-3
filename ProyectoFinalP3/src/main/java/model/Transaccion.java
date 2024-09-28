/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alexis Chapal
 */
public class Transaccion {
    
    String idTransaccion;
    String fecha;
    double monto;
    String descripcion;
    TipoTransaccion tipoTransaccion;
    
    public Transaccion (String idTransaccion, String fecha,double monto, String descripcion){
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
        
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
      @Override
    public String toString() {
        return "Transaccion{"
                + "idTransaccion= '" + idTransaccion + '\'' 
                + ", fecha=" + fecha + '\''
                + ", monto=" + monto + '\''
                + ", descripcion=" + descripcion + '}';
        
    }   
}

