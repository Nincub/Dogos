/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

/**
 *
 * @author Nincub
 */
public interface ICRUD extends Serializable{
    
    public boolean Escribir (Object obj);
    
    public Object Leer ();
    
    public boolean Modificar (Object obj);
    
    public boolean Eliminar (Object obj);
    
    public boolean EliminarLogic (Object obj);
    
}
