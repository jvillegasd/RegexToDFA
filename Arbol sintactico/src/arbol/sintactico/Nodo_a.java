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
public class Nodo_a implements Nodo{
    String label = "";
    
    public Nodo_a(String label){
        this.label = label;
    }

    @Override
    public void agregarHijo(Nodo hijo) {
    }

    @Override
    public ArrayList<Nodo> getHijos() {
        return null;
    }
}