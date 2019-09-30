package arbol.sintactico;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author LinkRs
 */
public class ArbolSintactico {
    private String er;
    private Nodo raiz;
    private Set<Integer> sgtPos[];
    private Hashtable<Character, Set<Integer>> pos = null;
    private int cntCaracteres = 0;
   
    public ArbolSintactico(String er){
        this.er = "(" + er + ")#";
        this.raiz = new Nodo('.');
        this.pos = new Hashtable<>();
        this.initPos();
    }
    
    private void initPos(){
        for(int i = 0; i < er.length(); i++){
            if(esCaracter(er.charAt(i))){
                if(pos.get(er.charAt(i)) == null) pos.put(er.charAt(i), new HashSet<>());
                pos.get(er.charAt(i)).add(++cntCaracteres);
            }
        }
        this.sgtPos = new Set[cntCaracteres + 1];
        for(int i = 0; i <= cntCaracteres; i++) sgtPos[i] = new HashSet<>();
    }
    
    public Nodo getRaiz(){
        return this.raiz;
    }
    
    public Set<Integer>[] getSgtPos(){
        return this.sgtPos;
    }
    
    public Hashtable<Character, Set<Integer>>  getPos(){
        return this.pos;
    }
    
    public int getCntCaracteres(){
        return this.cntCaracteres;
    }
    
    private boolean esOperador(char simbolo){
        return simbolo == '|' || simbolo == '.' || simbolo == '*' || simbolo == '(' 
                || simbolo == ')' || simbolo == '?' || simbolo == '+';
    }
    
    private boolean esCaracter(char simbolo){
        return Character.isLetterOrDigit(simbolo) || simbolo == '&' || simbolo == '#';
    }
    
    private boolean puedeConcatenar(char actual, char sgt){
        return (esCaracter(actual) && esCaracter(sgt)) || (actual == ')' && sgt == '(') 
                || ((actual == '*' || actual == ')') && esCaracter(sgt)) || (esCaracter(actual) && sgt == '(')
                || (actual == '?' && (esCaracter(sgt) || sgt == '(') || (actual == '+' && (esCaracter(sgt) || sgt == '(')));
    }
    
    private void normalizarER(){
        for(int i = 0; i < er.length()-1; i++){
            char actual = er.charAt(i);
            char sgt = er.charAt(i + 1);
            String prefijo = er.substring(0, i + 1);
            String sufijo = er.substring(i + 1);
            if(puedeConcatenar(actual, sgt)) er = prefijo + "." + sufijo;
        }
    }
    
    private int prec(char symbol){
        switch(symbol){
            case '|':
                return 1;
            case '.':
                return 2;
            case '*':
            case '+':
            case '?':
                return 3;
        }
        return 0;
    }
   
    private String infixAPostfix(){
        Stack<Character> s = new Stack<>();
        String postfix = "";
        for(int i = 0; i < er.length(); i++){
            char simbolo = er.charAt(i);
            if(esCaracter(simbolo)) postfix+=simbolo;
            else if(simbolo == '(') s.push(simbolo);
            else if(simbolo == ')'){
                while(!s.empty() && s.peek() != '(') postfix+=s.pop();
                if(!s.empty() && s.peek() != '(') System.out.println("Expresion invalida");
                else s.pop();
            }else{
                while(!s.empty() && prec(simbolo) <= prec(s.peek())){
                    if(s.peek() == '(') System.out.println("Expresion invalida");
                    postfix+=s.pop();
                }
                s.push(simbolo);
            }
        }
        while(!s.empty()){
            if(s.peek() == '(') System.out.println("Expresion invalida");
            postfix+=s.pop();
        }
        return postfix;
    }
    
    private void postfixAArbol(String postfix){
        Stack<Nodo> s = new Stack<>();
        Nodo actual, hijoDer, hijoIzq;
        int cnt = 1;
        for(int i = 0; i < postfix.length(); i++){
            char simbolo = postfix.charAt(i);
            if(!esOperador(simbolo)){
                actual = new Nodo(simbolo);
                actual.setPosicion(cnt++);
                s.push(actual);
            }else{
                actual = new Nodo(simbolo);
                if(simbolo == '*' || simbolo == '+' || simbolo == '?'){
                    hijoIzq = s.pop();
                    actual.setHijoIzq(hijoIzq);
                }else{
                    hijoDer = s.pop();
                    hijoIzq = s.pop();
                    actual.setHijoDer(hijoDer);
                    actual.setHijoIzq(hijoIzq);
                }
                s.push(actual);
            }
        }
        raiz = s.pop();
    }
   
    public void crearArbol(){
        normalizarER();
        String postfix = infixAPostfix();
        postfixAArbol(postfix);
    }
    
    private void unionPosiciones(Nodo actual, boolean esPpos){
        if(esPpos){
            Nodo hijoIzq = actual.getHijoIzq();
            Nodo hijoDer = actual.getHijoDer();
            if(hijoIzq != null){
                for(int posicion : hijoIzq.getPpos()) actual.addPpos(posicion);
            }
            if(hijoDer != null){
                for(int posicion : hijoDer.getPpos()) actual.addPpos(posicion);
            }
        }else{
            Nodo hijoIzq = actual.getHijoIzq();
            Nodo hijoDer = actual.getHijoDer();
            if(hijoIzq != null){
                for(int posicion : hijoIzq.getUpos()) actual.addUpos(posicion);
            }
            if(hijoDer != null){
                for(int posicion : hijoDer.getUpos()) actual.addUpos(posicion);
            }
        }
    }
    
    private void ppos_Upos(Nodo actual){
        if(esCaracter(actual.getLabel()) && actual.getLabel() != '&'){
            actual.setAnulable(false);
            actual.addPpos(actual.getPosicion());
            actual.addUpos(actual.getPosicion());
        } else if(esOperador(actual.getLabel())){
            boolean anulable1, anulable2;
            switch(actual.getLabel()){
                case '|':
                    anulable1 = actual.getHijoIzq().esAnulable();
                    anulable2 = actual.getHijoDer().esAnulable();
                    actual.setAnulable(anulable1 | anulable2);
                    unionPosiciones(actual, true);
                    unionPosiciones(actual, false);
                    break;
                case '.':
                    anulable1 = actual.getHijoIzq().esAnulable();
                    anulable2 = actual.getHijoDer().esAnulable();
                    actual.setAnulable(anulable1 & anulable2);
                    if(anulable1) unionPosiciones(actual, true);
                    else actual.setPpos(actual.getHijoIzq().getPpos());
                    if(anulable2) unionPosiciones(actual, false);
                    else actual.setUpos(actual.getHijoDer().getUpos());
                    break;
                case '*':
                case '?':
                case '+':
                    actual.setPpos(actual.getHijoIzq().getPpos());
                    actual.setUpos(actual.getHijoIzq().getUpos());
                    if(actual.getLabel() == '+') actual.setAnulable(false);
                    break;
            }
        }
    }
    
    private void sgtPost(Nodo actual){
        Set<Integer> uposC1 = actual.getHijoIzq().getUpos();
        switch(actual.getLabel()){
            case '.':
                Set<Integer> pposC2 = actual.getHijoDer().getPpos();
                for(Integer i : uposC1) sgtPos[i].addAll(pposC2);
                break;
            case '*':
            case '+':
                Set<Integer> pposC1 = actual.getHijoIzq().getPpos();
                for(Integer i : uposC1) sgtPos[i].addAll(pposC1);
                break;
        }
    }
    
    private void calculoPosiciones(Nodo actual){
        if(actual == null) return;
        calculoPosiciones(actual.getHijoIzq());
        calculoPosiciones(actual.getHijoDer());
        ppos_Upos(actual);
        if(esOperador(actual.getLabel())) sgtPost(actual);
    }
    
    public void calculoPosiciones(){
        this.calculoPosiciones(this.raiz);
    }
}