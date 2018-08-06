/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import products.Dogo;
import products.Pan;
import products.Salchicha;

/**
 *
 * @author Nincub
 */
public class MenuDogo implements interfaces.IMenu {
    @Override
    public void Principal(){
        Scanner s = new Scanner(System.in);
        int ch = 0;
        do {
            Impresion();
            ch = s.nextInt();
            Seleccion(ch);
        } while(ch != 6);
    }
    
    @Override
    public void Impresion () {
        System.out.println("1) Agregar"
                    + "\n2) Mostrar"
                    + "\n3) Modificar"
                    + "\n4) Eliminar"
                    + "\n5) Elimanar Logico"
                    + "\n6) Salir");
    }
    
    @Override
    public void Seleccion (int sel) {
        switch (sel) {
            case 1: 
                Agregar();
                break;
            case 2:
                Mostrar();
                break;
            case 3:
                Modificar();
                break;
            case 4:
                Eliminar();
                break;
            case 5:
                EliminarLogic();
                break;
            case 6:
                break;
            default :
                System.out.println("");
                break;
        }
    }

    @Override
    public void Agregar() {
        boolean ban = false;
        int it = 0;
        System.out.println("Agrega el precio del Dogo: ");
        double precio = validaDouble();
        System.out.println("Agrega el nombre del Dogo");
        String nombre = new Scanner(System.in).nextLine();
        Salchicha s = new Salchicha();
        LinkedList<Salchicha> ll = (LinkedList) s.Leer();
        Dogo dogo = null;
        Pan p = new Pan();
        if (ll.isEmpty()){
            System.out.println("No hay salchichas guardadas");
        } else {
            System.out.println("Selecciona el tipo de salchicha");
            for(Salchicha a : ll) {
                it++;
                System.out.println(it + ") " + a.toString());
            }
            s = ll.get(validaInt() - 1);
            LinkedList<Pan> list = (LinkedList) p.Leer();
            if (list.isEmpty()) {
                System.out.println("No hay panes guardados");
            } else {
                it = 0;
                ban = true;
                System.out.println("Selecciona el tipo de pan");
                for (Pan as : list) {
                    it++;
                    System.out.println(it + ") " + as.toString());
                }
                p = list.get(validaInt() - 1);
                dogo = new Dogo(p,s, precio, nombre, true);
            }
            
        }
        
        System.out.println(dogo.Escribir(dogo) && ban? "Agregado con exito": "Fallo la agregacion del dogo");
    }

    @Override
    public void Mostrar() {
      Dogo dogo = new Dogo();
      LinkedList ll = (LinkedList) dogo.Leer();
      if (ll.isEmpty()){
          System.out.println("Lista Vacia");
          return;
      }
      for (Object obj : ll){
          dogo = (Dogo) obj;
          System.out.println(dogo.toString());
      }
    }

    @Override
    public void Modificar() {
        Dogo dogo = new Dogo();
        int i = 0;
        LinkedList ll = (LinkedList) dogo.Leer();
        if (ll.isEmpty()){
            System.out.println("No hay nada para modificar");
            return;
        }
        System.out.println("Seleccione la opcion a modificar");
        for (Object obj : ll){
          i++;
          dogo = (Dogo) obj;
          System.out.println(i + ") " + dogo.toString());
        }
        LinkedList<Dogo> l = new LinkedList();
        int auxx = validaInt();
        if (auxx <= i && auxx > 0) {
            System.out.println("Ingresa el nuevo nombre");
            String nombre = new Scanner(System.in).nextLine();
            System.out.println("Ingresa el nuevo precio");
            double precio = validaDouble();
            l.add((Dogo) ll.get(auxx - 1));
            l.add(new Dogo(precio, nombre, true));
            System.out.println(dogo.Modificar(l)? "Moficado" : "Error al modificar");
        }
    }
    
    private int validaInt () {
        int re;
        try {
            re = new Scanner(System.in).nextInt();
        } catch (InputMismatchException ex){
            System.out.println("Valor no valido intente de nuevo");
            re = validaInt();
        }
        return re;
    }
    
    private double validaDouble () {
        double re;
        try {
            re = new Scanner(System.in).nextDouble();
        } catch (InputMismatchException ex){
            System.out.println("Valor no valido intente de nuevo");
            re = validaDouble();
        }
        return re;
    }

    @Override
    public void Eliminar() {
        Dogo dogo = new Dogo();
        int i = 0;
        LinkedList ll = (LinkedList) dogo.Leer();
        if (ll.isEmpty()){
            System.out.println("No hay nada para eliminar");
            return;
        }
        System.out.println("Seleccione la opcion a eliminar");
        for (Object obj : ll){
          i++;
          dogo = (Dogo) obj;
          System.out.println(i + ") " + dogo.toString());
        }
        dogo.Eliminar(ll.get(validaInt() - 1));
    }

    @Override
    public void EliminarLogic() {
        Dogo dogo = new Dogo();
        int i = 0;
        LinkedList ll = (LinkedList) dogo.Leer();
        if (ll.isEmpty()){
            System.out.println("No hay nada para eliminar logicamente");
            return;
        }
        System.out.println("Seleccione la opcion a eliminar logicamente");
        for (Object obj : ll){
          i++;
          dogo = (Dogo) obj;
          System.out.println(i + ") " + dogo.toString());
        }
        dogo.EliminarLogic(ll.get(validaInt() - 1));
    }
    
    
}
