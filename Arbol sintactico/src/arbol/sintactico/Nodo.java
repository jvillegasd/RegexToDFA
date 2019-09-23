/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.sintactico;

import java.util.ArrayList;

/**
 *
 * @author LinkRs
 */
public interface Nodo {
    
    public void agregarHijo(Nodo hijo);
    public ArrayList<Nodo> getHijos();
}
