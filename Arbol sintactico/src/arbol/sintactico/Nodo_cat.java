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
public class Nodo_cat implements Nodo{
    String label = ".";
    ArrayList<Nodo> hijos;
    
    public Nodo_cat(String label){
        this.label = label;
        hijos = new ArrayList<>();
    }

    @Override
    public void agregarHijo(Nodo hijo) {
        hijos.add(hijo);
    }

    @Override
    public ArrayList<Nodo> getHijos() {
        return hijos;
    }
}