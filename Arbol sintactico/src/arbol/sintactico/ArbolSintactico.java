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
public class ArbolSintactico {
    String label = ".", er;
    ArrayList<Nodo> hijos;
    
    public ArbolSintactico(String er){
        this.er = er;
        this.hijos = new ArrayList<>();
    }
    
    public void crearArbol(){
        
    }
}
