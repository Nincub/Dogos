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
public class Dogo extends abstracts.AProducto implements Serializable{
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
        
        return"El nombre es "+this.getNombre()+" tipo de salchicha "+salchicha.toString()+" tipo de dogo:  "
                +pan.toString()+ " El precio es :" + this.getPrecio(); 
               
        
        
    }

    /**
     * Recibe un objeto de dogo y lo escribe en un Fichero nombrado "Dogo" que es guardado en la raíz del proyecto
     * @param Dogo
     * @return Devuelve true en caso de que se complete la escritura con exito en caso contrario será false
     */
    @Override
    public boolean Escribir(Object obj) {
        boolean exito = true;
        FileOutputStream out = null;
        ObjectOutputStream oos = null;
        try {
            
            if (!validarFile()) {
                out = new FileOutputStream("Dogo", true);
                oos = new ObjectOutputStream(out);
            } else {
                out = new FileOutputStream("Dogo", true);
                oos = new MiObjectOutputStream(out);
            }
            oos.writeObject(obj);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        } catch (IOException ex) {
           Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            exito = false;
        } catch (Exception ex) {
            System.err.println(ex);
            exito = false;
        } finally {
            try {
                out.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return exito;
    }
    
    private boolean validarFile() {
        File f  = new File("Dogo");
        return f.exists();
    }

    /**
     * Leer la lista de Objetos <Dogo> del Fichero "Dogo"
     * @return Devuelve un LinkedList casteado a Object  en caso de haber sido nada encontrado retornara null
     */
    @Override
    public Object Leer() {
        LinkedList<Dogo> ll = null;
        Dogo aux;
        FileInputStream in = null;
        ObjectInputStream ois = null;
        try {
            in = new FileInputStream("Dogo");
            ois = new ObjectInputStream(in);
            
            ll = new LinkedList();
            while (true) {
                aux = (Dogo) ois.readObject();
                ll.add(aux);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { 
            
        } finally {
            try {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
                }
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ll;
    }

    /**
     * Recibe un LinkedList dentro de un tipo Object en el que el primer elemento de la misma es la referencia
     * a modificar y la segunda el la modificacion
     * @param obj
     * @param LinkedList
     * @return Devuelve true si se logro modificar algo y false en caso contrario
     */
    @Override
    public boolean Modificar(Object obj) {
        File F = new File("Dogo");
        File F2 = new File ("Dogo1");
        boolean ban = false;
        Dogo aux;
        LinkedList<Dogo> ll = (LinkedList) obj;
        Dogo dogo = (Dogo) ll.pollFirst();
        Dogo dogo1 = (Dogo) ll.pollLast();
        if(dogo == null || dogo1 == null){
            if (!dogo.equals(dogo1)) {
                System.err.println("No puede ser igual el dogo a modificar");
            } else {
                System.err.println("lista de dogoes a modificar incompleta");
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
                aux = (Dogo) ois.readObject();
                if (aux.getNombre().equals(dogo.getNombre()) && aux.getPrecio() == dogo.getPrecio()){
                    oos.writeObject(dogo1);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
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
        File F = new File("Dogo");
        File F2 = new File("Dogo1");
        Dogo aux;
        Dogo auxx = (Dogo) obj;
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
                aux = (Dogo) ois.readObject();
                if (aux.getNombre().equals(auxx.getNombre()) && aux.getPrecio() == auxx.getPrecio()) {
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
                ois.close();
                out.close();
                oos.close();
                F.delete();
                F2.renameTo(F);
            } catch (IOException ex) {
                Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
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
        File F = new File("Dogo");
        File F2 = new File("Dogo1");
        Dogo aux;
        Dogo auxx = (Dogo) obj;
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
                aux = (Dogo) ois.readObject();
                if (aux.getNombre().equals(auxx.getNombre()) && aux.getPrecio() == auxx.getPrecio()) {
                    aux.setStatus(false);
                    oos.writeObject(aux);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ban;
    }
    
    /**
     * Retorna el Pan
     * @return pan
     */
    public Pan getPan() {
        return pan;
    }

    /**
     * Asignar el Pan
     * @param pan 
     */
    public void setPan(Pan pan) {
        this.pan = pan;
    }

    /**
     * Retorna la Salchicha
     * @return salchicha
     */
    public Salchicha getSalchicha() {
        return salchicha;
    }
    
    /**
     * Asigna la Salchicha
     * @param salchicha 
     */
    public void setSalchicha(Salchicha salchicha) {
        this.salchicha = salchicha;
    }
    
    
    
    }
    
    

    
    

