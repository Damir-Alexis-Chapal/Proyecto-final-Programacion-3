/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Alexis Chapal
 */
public class Presupuesto {
    
    String idPresupuesto;
    String nombrePresupuesto;
    double montoTotal;
    double montoGastado;

    public String getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(String idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public String getNombrePresupuesto() {
        return nombrePresupuesto;
    }

    public void setNombrePresupuesto(String nombrePresupuesto) {
        this.nombrePresupuesto = nombrePresupuesto;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoGastado() {
        return montoGastado;
    }

    public void setMontoGastado(double montoGastado) {
        this.montoGastado = montoGastado;
    }

    @Override
    public String toString() {
        return "Presupuesto{"
                + "idPresupuesto='" + idPresupuesto + '\''
                + ", nombrePresupuesto='" + nombrePresupuesto + '\''
                + ", montoTotal=" + montoTotal + '\''
                + ", montoGastado=" + montoGastado + '}';
    }
    
}
