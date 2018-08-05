/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import com.sun.corba.se.impl.io.IIOPOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nincub
 */
public class Pan extends abstracts.AProducto {
    /**
     * Constructor empty
     */
    public Pan() {
    }
    
    /**
     * Contructor con parametros
     * @param precio
     * @param nombre 
     */
    public Pan (double precio, String nombre, boolean status) {
        super(precio, nombre, status);
    }

    /**
     * Metodo no implementado
     * @deprecated
     */
    @Deprecated
    @Override
    public void calcularPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "{Pan{Nombre:" + this.getNombre() + ",Precio:" + this.getPrecio() +"}}";
    }

    @Override
    public boolean Escribir(Object obj) {
        boolean exito = true;
        try {
            FileOutputStream out = new FileOutputStream("Pan", true);
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
        LinkedList<Pan> ll;
        try {
            FileInputStream in = new FileInputStream("Pan");
            ObjectInputStream ois = new ObjectInputStream(in);
            ll = new LinkedList();
            while(ois.read() != -1) {
                ll.add((Pan)ois.readObject());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ll;
    }

    @Override
    public boolean Modificar(Object obj) {
        File F = new File("Pan");
        File F2 = new File ("Pan1");
        boolean ban = false;
        Pan aux;
        LinkedList<Pan> ll = (LinkedList) obj;
        Pan pan = (Pan) ll.pollFirst();
        Pan pan1 = (Pan) ll.pollLast();
        if(pan == null || pan1 == null){
            if (!pan.equals(pan1)) {
                System.err.println("No puede ser igual el pan a modificar");
            } else {
                System.err.println("lista de panes a modificar incompleta");
            }
            return ban;
        }
        try {
            FileInputStream in = new FileInputStream(F);
            ObjectInputStream ois = new ObjectInputStream(in);
            FileOutputStream out = new FileOutputStream(F2, true);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            while (ois.read() != -1) {
                aux = (Pan) ois.readObject();
                if (aux.equals(pan)){
                    oos.writeObject(pan1);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
            in.close();
            oos.close();
            if (F.delete()) {
                if (F2.renameTo(F)) {
                    System.out.println("Exito");
                }
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
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
