/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Hilxer
 */
public class Dogo extends abstracts.AProducto {
    private Pan pan;
    private Salchicha salchicha;
    
    
    public Dogo(){}
    
    public Dogo(Pan pan, Salchicha salchicha,double precio,String nombre,boolean status){
        super(precio,nombre,status);
        this.salchicha=salchicha;
        this.pan=pan;
    }
    
    

    @Override
    public void calcularPrecio() {
        double aux = pan.getPrecio() + salchicha.getPrecio();
        this.setPrecio(aux);
    }
    

    @Override
    public String toString() {
        
        return"El nombre es "+this.getNombre()+" tipo de salchicha "+salchicha.toString()+" tipo de pan:  "
                +pan.toString()+ " El precio es :" + this.getPrecio(); 
               
        
        
    }

    @Override
    public boolean Escribir(Object obj) {
         boolean exito = true;
        try {
            FileOutputStream out = new FileOutputStream("Dogo", true);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        } catch (IOException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        } catch (Exception ex) {
            System.err.println(ex);
            exito = false;
        }
        return exito;
       
    }

    @Override
    public Object Leer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Modificar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean EliminarLogic(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the pan
     */
    public Pan getPan() {
        return pan;
    }

    /**
     * @param pan the pan to set
     */
    public void setPan(Pan pan) {
        this.pan = pan;
    }

    /**
     * @return the salchicha
     */
    public Salchicha getSalchicha() {
        return salchicha;
    }

    /**
     * @param salchicha the salchicha to set
     */
    public void setSalchicha(Salchicha salchicha) {
        this.salchicha = salchicha;
    }
    
    }
    
    

    
    

