/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.io.BufferedInputStream;
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
 * @author Nincub
 */
public class Pan extends abstracts.AProducto implements Serializable{
    
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

    /**
     * Recibe un objeto de pan y lo escribe en un Fichero nombrado "Pan" que es guardado en la raíz del proyecto
     * @param Pan
     * @return Devuelve true en caso de que se complete la escritura con exito en caso contrario será false
     */
    @Override
    public boolean Escribir(Object obj) {
        boolean exito = true;
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            
            if (!validarFile()) {
                out = new FileOutputStream("Pan", true);
                oos = new ObjectOutputStream(out);
            } else {
                out = new FileOutputStream("Pan", true);
                oos = new MiObjectOutputStream(out);
            }
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
        } finally {
            try {
                out.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
    }
    
    private boolean validarFile() {
        File f  = new File("Pan");
        return f.exists();
    }

    /**
     * Leer la lista de Objetos <Pan> del Fichero "Pan"
     * @return Devuelve un LinkedList casteado a Object  en caso de haber sido nada encontrado retornara null
     */
    @Override
    public Object Leer() {
        LinkedList<Pan> ll = null;
        Pan aux;
        FileInputStream in = null;
        ObjectInputStream ois = null;
        try {
            in = new FileInputStream("Pan");
            ois = new ObjectInputStream(in);
            
            ll = new LinkedList();
            while (true) {
                aux = (Pan) ois.readObject();
                ll.add(aux);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { 
            
        } finally {
            try {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
                }
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
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
                aux = (Pan) ois.readObject();
                if (aux.getNombre().equals(pan.getNombre()) && aux.getPrecio() == pan.getPrecio()){
                    oos.writeObject(pan1);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
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
        File F = new File("Pan");
        File F2 = new File("Pan1");
        Pan aux;
        Pan auxx = (Pan) obj;
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
                aux = (Pan) ois.readObject();
                if (aux.getNombre().equals(auxx.getNombre()) && aux.getPrecio() == auxx.getPrecio()) {
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                ois.close();
                out.close();
                oos.close();
                F.delete();
                F2.renameTo(F);
            } catch (IOException ex) {
                Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
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
        File F = new File("Pan");
        File F2 = new File("Pan1");
        Pan aux;
        Pan auxx = (Pan) obj;
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
                aux = (Pan) ois.readObject();
                if (aux.getNombre().equals(auxx.getNombre()) && aux.getPrecio() == auxx.getPrecio()) {
                    aux.setStatus(false);
                    oos.writeObject(aux);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Pan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ban;
    }
    
}
