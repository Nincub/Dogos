/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mein;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import menus.MenuPan;
import products.Pan;
import products.Salchicha;

/**
 *
 * @author Nincub
 */
public class Mein {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException,IOException {
        // TODO code application logic 
        MenuPan pan = new MenuPan();
        pan.Principal();
        
        
        
        
    }
    
}
