package com.mycompany.iatesting;


/**
 *
 * @author chaos
 */
public class IATesting {

    public static void main(String[] args) {
        int lp = 1;
        /*
        Los elementos del campo pueden ser representados con valores
        numericos:
        1 - El agente
        2 - El enemigo
        3 - Pozo
        4 - Tesoro
        Tanto los pozos como el teesoro seran estaticos y ellos y el monstruo
        serán generados al azar.
        */
        //Campo de juego. Matrix de 5x5
        int[][] field = new int[5][5];
        
        Agent agente = new Agent();
     
        while(lp == 1) {
           int aCorX = agente.getCorX();
           int aCorY = agente.getCorY();
           
        }
        
        //Comprobar lo que hay dentro de la matriz
        for (int i=0; i < field.length; i++) {
            for (int j=0; j < field.length; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.print("\n");
        }
        
    }
}
