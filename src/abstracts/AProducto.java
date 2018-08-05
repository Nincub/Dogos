package abstracts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nincub
 */
public abstract class AProducto implements interfaces.ICRUD{
    private double precio;
    private String nombre;
    private boolean status;
    
    public AProducto () {
    }

    public AProducto(double precio, String nombre, boolean status) {
        this.precio = precio;
        this.nombre = nombre;
        this.status = status;
    }

    
    
    public abstract void calcularPrecio ();
    
    @Override
    public abstract String toString ();

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
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
    
}
