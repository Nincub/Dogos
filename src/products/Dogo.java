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
        
        return"El nombre es "+this.getNombre()+" tipo de salchicha "+salchicha.toString()+" tipo de pan:  "
                +pan.toString()+ " El precio es :" + this.getPrecio(); 
               
        
        
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
    
    private boolean validarFile() {
        File f  = new File("Dogo");
        return f.exists();
    }

    @Override
    public Object Leer() {
        LinkedList<Dogo> ll = null;
        Dogo aux;
        try {
            FileInputStream in = new FileInputStream("Dogo");
            ObjectInputStream ois = new ObjectInputStream(in);
            ll = new LinkedList();
            while(true) {
                aux = (Dogo) ois.readObject();
                ll.add(aux);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return ll;
    }

    @Override
    public boolean Modificar(Object obj) {
        File F = new File("Dogo");
        File F2 = new File ("Dogo1");
        boolean ban = false;
        Dogo aux;
        LinkedList<Dogo> ll = (LinkedList) obj;
        Dogo dogo = (Dogo) ll.pollFirst();
        Dogo dogo1 = (Dogo) ll.pollLast();
        if(pan == null || dogo1 == null){
            if (!pan.equals(dogo1)) {
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
            while (true) {
                aux = (Dogo) ois.readObject();
                if (aux.equals(dogo)){
                    oos.writeObject(dogo1);
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return ban;
    }

    @Override
    public boolean Eliminar(Object obj) {
        boolean ban = false;
        File F = new File("Dogo");
        File F2 = new File("Dogo1");
        Dogo aux;
        try {
            FileInputStream in = new FileInputStream(F);
            ObjectInputStream ois = new ObjectInputStream(in);
            FileOutputStream out = new FileOutputStream(F2, true);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            while (true){
                aux = (Dogo) ois.readObject();
                if (aux.equals((Dogo)obj)) {
                    ban = true;
                } else {
                    oos.writeObject(aux);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Dogo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            
        }
        return ban;
    }

    @Override
    public boolean EliminarLogic(Object obj) {
     boolean ban = false;
        File F = new File("Dogo");
        File F2 = new File("Dogo1");
        Dogo aux;
        try {
            FileInputStream in = new FileInputStream(F);
            ObjectInputStream ois = new ObjectInputStream(in);
            FileOutputStream out = new FileOutputStream(F2, true);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            while (true){
                aux = (Dogo) ois.readObject();
                if (aux.equals((Dogo)obj)) {
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
            
        }
        return ban;
        
    }
    }
    
    

    
    

