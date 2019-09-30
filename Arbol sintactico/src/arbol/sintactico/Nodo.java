/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.sintactico;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LinkRs
 */
public class Nodo {
    private Nodo hijoIzq = null, hijoDer = null;
    private char label;
    private int posicion;
    private Set<Integer> ppos, upos;
    private boolean anulable = true;
    private int numSeq = 0;

    public Nodo(char label){
        this.label = label;
        this.ppos = new HashSet<>();
        this.upos = new HashSet<>();
    }

    public Nodo getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(Nodo hijoDer) {
        this.hijoDer = hijoDer;
    }

    public Nodo getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(Nodo hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public char getLabel() {
        return label;
    }   

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    public void addPpos(int numero){
        this.ppos.add(numero);
    }
    
    public void addUpos(int numero){
        this.upos.add(numero);
    }
    
    public Set<Integer> getPpos(){
        return this.ppos;
    }
     
    public void setPpos(Set<Integer> ppos){
        this.ppos = ppos;
    }
    
    public Set<Integer> getUpos(){
        return this.upos;
    }
    
    public void setUpos(Set<Integer> upos){
        this.upos = upos;
    }
    
    public boolean esAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public int getNumSeq() {
        return numSeq;
    }

    public void setNumSeq(int numSeq) {
        this.numSeq = numSeq;
    }
    
}
