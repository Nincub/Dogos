/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import products.Salchicha;

/**
 *
 * @author Nincub
 */
public class MenuSalchicha implements interfaces.IMenu{
    
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
        System.out.println("Agrega el precio del Salchicha: ");
        double precio = validaDouble();
        System.out.println("Agrega el nombre del Salchicha");
        String nombre = new Scanner(System.in).nextLine();
        Salchicha salchicha = new Salchicha(precio, nombre, true);
        System.out.println(salchicha.Escribir(salchicha)? "Agregado con exito": "Fallo la agregacion del salchicha");
    }

    @Override
    public void Mostrar() {
      Salchicha salchicha = new Salchicha();
      LinkedList ll = (LinkedList) salchicha.Leer();
      if (ll.isEmpty()){
          System.out.println("Lista Vacia");
          return;
      }
      for (Object obj : ll){
          salchicha = (Salchicha) obj;
          System.out.println(salchicha.toString());
      }
    }

    @Override
    public void Modificar() {
        Salchicha salchicha = new Salchicha();
        int i = 0;
        LinkedList ll = (LinkedList) salchicha.Leer();
        if (ll.isEmpty()){
            System.out.println("No hay nada para modificar");
            return;
        }
        System.out.println("Seleccione la opcion a modificar");
        for (Object obj : ll){
          i++;
          salchicha = (Salchicha) obj;
          System.out.println(i + ") " + salchicha.toString());
        }
        LinkedList<Salchicha> l = new LinkedList();
        int auxx = validaInt();
        if (auxx <= i && auxx > 0) {
            System.out.println("Ingresa el nuevo nombre");
            String nombre = new Scanner(System.in).nextLine();
            System.out.println("Ingresa el nuevo precio");
            double precio = validaDouble();
            l.add((Salchicha) ll.get(auxx - 1));
            l.add(new Salchicha(precio, nombre, true));
            System.out.println(salchicha.Modificar(l)? "Moficado" : "Error al modificar");
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
        Salchicha salchicha = new Salchicha();
        int i = 0;
        LinkedList ll = (LinkedList) salchicha.Leer();
        if (ll.isEmpty()){
            System.out.println("No hay nada para eliminar");
            return;
        }
        System.out.println("Seleccione la opcion a eliminar");
        for (Object obj : ll){
          i++;
          salchicha = (Salchicha) obj;
          System.out.println(i + ") " + salchicha.toString());
        }
        salchicha.Eliminar(ll.get(validaInt() - 1));
    }

    @Override
    public void EliminarLogic() {
        Salchicha salchicha = new Salchicha();
        int i = 0;
        LinkedList ll = (LinkedList) salchicha.Leer();
        if (ll.isEmpty()){
            System.out.println("No hay nada para eliminar logicamente");
            return;
        }
        System.out.println("Seleccione la opcion a eliminar logicamente");
        for (Object obj : ll){
          i++;
          salchicha = (Salchicha) obj;
          System.out.println(i + ") " + salchicha.toString());
        }
        salchicha.EliminarLogic(ll.get(validaInt() - 1));
    }
    
    
}
