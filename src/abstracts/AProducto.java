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
    
    public AProducto () {
    }

    public AProducto(double precio, String nombre) {
        this.precio = precio;
        this.nombre = nombre;
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
