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
public class Estado {
    private int label;
    private Set<Integer> conjPos = null;
    private boolean marcado = false;
    private boolean estadoFinal = false;
    
    public Estado(int label){
        this.label = label;
        this.conjPos = new HashSet<>();
    }

    public int getLabel() {
        return label;
    }

    public void addConjPos(int posicion){
        this.conjPos.add(posicion);
    }
    
    public void setConjPos(Set<Integer> conjPos){
        this.conjPos = conjPos;
    }
    
    public Set<Integer> getConjPos(){
        return this.conjPos;
    }

    public boolean estaMarcado() {
        return this.marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
    
    public boolean esEstadoFinal(){
        return this.estadoFinal;
    }

    public void setEstadoFinal(int posNumeral) {
        if(conjPos.contains(posNumeral)) this.estadoFinal = true;
    }
}
