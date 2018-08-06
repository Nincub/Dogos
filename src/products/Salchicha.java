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
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            
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
        } finally {
            try {
                out.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
    }
    
    private boolean validarFile() {
        File f  = new File("Salchicha");
        return f.exists();
    }

    /**
     * Leer la lista de Objetos <Salchicha> del Fichero "Salchicha"
     * @return Devuelve un LinkedList casteado a Object  en caso de haber sido nada encontrado retornara null
     */
    @Override
    public Object Leer() {
        LinkedList<Salchicha> ll = null;
        Salchicha aux;
        FileInputStream in = null;
        ObjectInputStream ois = null;
        try {
            in = new FileInputStream("Salchicha");
            ois = new ObjectInputStream(in);
            
            ll = new LinkedList();
            while (true) {
                aux = (Salchicha) ois.readObject();
                ll.add(aux);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { 
            
        } finally {
            try {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
                }
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ll;
    }

    /**
     * Recibe un LinkedList dentro de un tipo Object en el que el primer elemento de la misma es la referencia
     * a modificar y la segunda el la modificacion
     * @param LinkedList
     * @return Devuelve true si se logro modificar algo y false en caso contrario
     */
    @Override
    public boolean Modificar(Object obj) {
        File F = new File("Salchicha");
        File F2 = new File ("Salchicha1");
        boolean ban = false;
        Salchicha aux;
        LinkedList<Salchicha> ll = (LinkedList) obj;
        Salchicha salchicha = (Salchicha) ll.pollFirst();
        Salchicha salchicha1 = (Salchicha) ll.pollLast();
        if(salchicha == null || salchicha1 == null){
            if (!salchicha.equals(salchicha1)) {
                System.err.println("No puede ser igual el salchicha a modificar");
            } else {
                System.err.println("lista de salchichaes a modificar incompleta");
            }
            return ban;
        }
        FileInputStream in = null;
        ObjectInputStream ois = null;
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            in = new FileInputStream(F);
            ois = new ObjectInputStream(in);
            out = new FileOutputStream(F2);
            oos = new ObjectOutputStream(out);
            while (true) {
                aux = (Salchicha) ois.readObject();
                if (aux.getNombre().equals(salchicha.getNombre()) && aux.getPrecio() == salchicha.getPrecio()){
                    oos.writeObject(salchicha1);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            
            
        } finally {
            try {
                in.close();
                ois.close();
                out.close();
                oos.close();
                F.delete();
                F2.renameTo(F);
            } catch (IOException ex) {
                Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ban;
    }
    
    /**
     * Recibe como parametro el elemento a eliminar
     * @param obj 
     * @return devuelve true en caso de que se lograra eliminar y false en caso contrario
     */
    @Override
    public boolean Eliminar(Object obj) {
        boolean ban = false;
        File F = new File("Salchicha");
        File F2 = new File("Salchicha1");
        Salchicha aux;
        Salchicha auxx = (Salchicha) obj;
        FileInputStream in = null;
        ObjectInputStream ois = null;
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            in = new FileInputStream(F);
            ois = new ObjectInputStream(in);
            out = new FileOutputStream(F2, true);
            oos = new ObjectOutputStream(out);
            while (true){
                aux = (Salchicha) ois.readObject();
                if (aux.getNombre().equals(auxx.getNombre()) && aux.getPrecio() == auxx.getPrecio()) {
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                ois.close();
                out.close();
                oos.close();
                F.delete();
                F2.renameTo(F);
            } catch (IOException ex) {
                Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ban;
    }

    /**
     * Recibe como parametro el elemento a eliminar de manera logica
     * @param obj 
     * @return devuelve true en caso de que se lograra eliminar y false en caso contrario
     */
    @Override
    public boolean EliminarLogic(Object obj) {
        boolean ban = false;
        File F = new File("Salchicha");
        File F2 = new File("Salchicha1");
        Salchicha aux;
        Salchicha auxx = (Salchicha) obj;
        FileInputStream in = null;
        ObjectInputStream ois = null;
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            in = new FileInputStream(F);
            ois = new ObjectInputStream(in);
            out = new FileOutputStream(F2, true);
            oos = new ObjectOutputStream(out);
            while (true){
                aux = (Salchicha) ois.readObject();
                if (aux.getNombre().equals(auxx.getNombre()) && aux.getPrecio() == auxx.getPrecio()) {
                    aux.setStatus(false);
                    oos.writeObject(aux);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            
        } finally {
            try {
                in.close();
                ois.close();
                out.close();
                oos.close();
                F.delete();
                F2.renameTo(F);
            } catch (IOException ex) {
                Logger.getLogger(Salchicha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ban;
    }
    
}
