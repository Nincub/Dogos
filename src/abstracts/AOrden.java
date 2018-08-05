/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstracts;

import java.util.Date;

/**
 *
 * @author Nincub
 */
public abstract class AOrden implements interfaces.ICRUD {
    private int orden;
    private String nombre;
    private double total;
    private String direccion;
    private Date fecha;

    public AOrden() {
    }

    public AOrden(int orden, String nombre, double total, String direccion, Date fecha) {
        this.orden = orden;
        this.nombre = nombre;
        this.total = total;
        this.direccion = direccion;
        this.fecha = fecha;
    }
    
    
    
    public abstract void CalcularTotal ();
    
    @Override
    public abstract String toString ();

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
