/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.sintactico;

/**
 *
 * @author LinkRs
 */
public class Clase_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String er = "(a|bb*)+aa(ab)?gb+";
        //String er = "(a|b)*abb";
        String er = "(a|b(c|d)*)+";
        //String er = "a&";
        ArbolSintactico st = new ArbolSintactico(er);
        st.crearArbol();
        st.calculoPosiciones();
        st.preOrden();
        AFD afd = new AFD(st, er);
        afd.crearAFD();
        afd.print();
        if(afd.reconoceCadena("&&&&&&")) System.out.println("La reconoce");
        else System.out.println("No la reconoce");
    }
    
}
