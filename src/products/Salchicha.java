/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sobre.MiObjectOutputStream;

/**
 *
 * @author Hilxer
 */
public class Salchicha  extends abstracts.AProducto implements Serializable{
    
    public Salchicha(){}
    
    public Salchicha(double precio, String nombre, boolean status){
       super(precio,nombre, status);
       
    }
    @Deprecated 
    @Override
    public void calcularPrecio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "{Salchicha{Nombre:" + this.getNombre() + ",Precio:" + this.getPrecio() +"}}";
    }

    @Override
    public boolean Escribir(Object obj) {
      boolean exito = true;
        try {
            FileOutputStream out;
            ObjectOutputStream oos;
            if (!validarFile()) {
                out = new FileOutputStream("Salchicha", true);
                oos = new ObjectOutputStream(out);
            } else {
                out = new FileOutputStream("Salchicha", true);
                oos = new MiObjectOutputStream(out);
            }
            oos.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        } catch (IOException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        } catch (Exception ex) {
            System.err.println(ex);
            exito = false;
        }
        return exito;
    }
    
    private boolean validarFile() {
        File f  = new File("Salchicha");
        return f.exists();
    }

    @Override
    public Object Leer() {
         LinkedList<Salchicha> aaa = null;
         Salchicha aux;
        try {
            FileInputStream eee = new FileInputStream("Salchicha");
            ObjectInputStream ole = new ObjectInputStream(eee);
            aaa = new LinkedList();
            while(true) {
                aux = (Salchicha) ole.readObject();
                aaa.add(aux);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return aaa;
    }

    @Override
    public boolean Modificar(Object obj) {
        File F = new File("Salchicha");
        File F2 = new File ("Salchicha1");
        boolean ban = false;
        Salchicha aux;
        LinkedList<Salchicha> aaa = (LinkedList) obj;
        Salchicha salchicha = (Salchicha) aaa.pollFirst();
        Salchicha salchicha1 = (Salchicha) aaa.pollLast();
        if(salchicha == null || salchicha1 == null){
            if (!salchicha.equals(salchicha1)) {
                System.err.println("No puede ser igual la salchicha a modificar");
            } else {
                System.err.println("lista de salchichas a modificar incompleta");
            }
            return ban;
        }
        try {
            FileInputStream eee = new FileInputStream(F);
            ObjectInputStream ole = new ObjectInputStream(eee);
            FileOutputStream hola = new FileOutputStream(F2, true);
            ObjectOutputStream hi = new ObjectOutputStream(hola);
            while (ole.read() != -1) {
                aux = (Salchicha) ole.readObject();
                if (aux.equals(salchicha)){
                    hi.writeObject(salchicha1);
                    ban = true;
                } else {
                    hi.writeObject(aux);
                }
            }
            eee.close();
            hi.close();
            if (F.delete()) {
                if (F2.renameTo(F)) {
                    System.out.println("Exito");
                }
            }  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }

    @Override
    public boolean Eliminar(Object obj) {
       boolean ban = false;
        File F = new File("Salchicha");
        File F2 = new File("Salchicha1");
        Salchicha aux;
        try {
            FileInputStream eee = new FileInputStream(F);
            ObjectInputStream ole = new ObjectInputStream(eee);
            FileOutputStream hola = new FileOutputStream(F2, true);
            ObjectOutputStream hi = new ObjectOutputStream(hola);
            while (ole.read() != -1){
                aux = (Salchicha) ole.readObject();
                if (aux.equals((Salchicha)obj)) {
                    ban = true;
                } else {
                    hi.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }

    @Override
    public boolean EliminarLogic(Object obj) {
        boolean ban = false;
        File F = new File("Salchicha");
        File F2 = new File("Salchicha1");
        Salchicha aux;
        try {
            FileInputStream eee = new FileInputStream(F);
            ObjectInputStream ole = new ObjectInputStream(eee);
            FileOutputStream hola = new FileOutputStream(F2, true);
            ObjectOutputStream hi = new ObjectOutputStream(hola);
            while (ole.read() != -1){
                aux = (Salchicha) ole.readObject();
                if (aux.equals((Salchicha)obj)) {
                    aux.setStatus(false);
                    hi.writeObject(aux);
                    ban = true;
                } else {
                    hi.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }
    
    
    
}
