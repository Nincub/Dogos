/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mein;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import menus.*;


/**
 *
 * @author Nincub
 */
public class Mein {

    
    /**
     * 
     * @param args
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException,IOException {
        // TODO code application logic 
        int i;
        do {
            System.out.println("Seleccione los que deseas editar");
            System.out.println("1) Dogos\n2) Salchichas \n3) Panes \n4) Salir");
            i = Mein.validaInt();
            selectOpcion(i);
        } while ( i != 4);
        
        
        
    }
    
    private static int validaInt () {
        int re;
        try {
            re = new Scanner(System.in).nextInt();
        } catch (InputMismatchException ex){
            System.out.println("Valor no valido intente de nuevo");
            re = validaInt();
        }
        return re;
    }

    private static void selectOpcion(int i) {
        MenuDogo menuDogo = new MenuDogo();
        MenuPan menuPan = new MenuPan();
        MenuSalchicha menuSalchicha = new MenuSalchicha();
        switch (i) {
            case 1: 
                menuDogo.Principal();
                break;
            case 2:
                menuSalchicha.Principal();
                break;
            case 3:
                menuPan.Principal();
                break;
            default :
                System.out.println("Opcion no valida");
                
        }
    }
    
    
}
