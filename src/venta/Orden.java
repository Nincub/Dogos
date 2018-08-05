/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venta;

import com.sun.corba.se.impl.io.OutputStreamHook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import products.Dogo;

/**
 *
 * @author Nincub
 */
public class Orden extends abstracts.AOrden {
    
    LinkedList <Dogo> dogos = new LinkedList();

    public Orden() {
    }
    
    public Orden (int orden, String nombre, double total, String direccion, Date fecha, LinkedList <Dogo> dogos) {
        super(orden, nombre, total, direccion, fecha);
        this.dogos = dogos;
    }
    
    
    
    @Override
    public double CalcularTotal() {
        double total = 0;
        total = dogos.stream().map((t) -> t.getPrecio()).reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }

    @Override
    public String toString() {
        String aux;
        aux = "Orden :" + this.getOrden() + ", Nombre :" + this.getNombre() + ", Total :" + this.getTotal() + ", Direccion :" + this.getDireccion() + ", Fecha :" + this.getFecha() + ", Dogos :";
        for(Dogo d : dogos){
            aux += d.toString();
        }
        return aux;
    }

    @Override
    public boolean Escribir(Object obj) {
        boolean ban = true;
        try {
            FileOutputStream out = new FileOutputStream("Orden", true);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
            ban = false;
        } catch (IOException ex) {
            Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
            ban = false;
        }
        return ban;
    }

    @Override
    public Object Leer() {
        LinkedList<Orden> ll = new LinkedList();
        try {
            FileInputStream in = new FileInputStream("Orden");
            ObjectInputStream ois = new ObjectInputStream(in);
            while(ois.read() != -1) {
                ll.add((Orden) ois.readObject());
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ll;
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
    
}
