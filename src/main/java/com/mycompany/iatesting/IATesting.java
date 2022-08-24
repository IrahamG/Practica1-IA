package com.mycompany.iatesting;


/**
 *
 * @author chaos
 */
public class IATesting {
    

    public static void main(String[] args) {
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
        // GENERACIÓN DE CAMPO DE JUEGO Y LLENADO CON 0s
        int[][] field = new int[5][5];
        
        for (int i=0; i < field.length; i++) {
            for (int j=0; j < field.length; j++) {
                field[i][j] = 0;
            }
        } 
        
        // CREACIÓN DEL AGENTE DE NOMBRE AVELINE
        
        Agent aveline = new Agent();
     
        // CREACIÓN DEL ENEMIGO LLAMADO ERINA
        /*
        Se generan dos números al azar entre 0 y 4 que serán las coordenadas
        de inicio
        */
        
        int eCorX = (int)Math.floor(Math.random()*(4-0+1)+0);
        int eCorY = (int)Math.floor(Math.random()*(4-0+1)+0);
        Enemy erina = new Enemy(eCorX, eCorY);
        
        // CREACIÓN DEL TESORO
        
        int tCorX = (int)Math.floor(Math.random()*(4-0+1)+0);
        int tCorY = (int)Math.floor(Math.random()*(4-0+1)+0);
        Treasure tesoro = new Treasure(tCorX, tCorY);
        
        // CREACIÓN DE LOS POZOS
        // Creación del pozo 1
        int p1CorX = (int)Math.floor(Math.random()*(4-0+1)+0);
        int p1CorY = (int)Math.floor(Math.random()*(4-0+1)+0);
        
        Pit pozo1 = new Pit(p1CorX, p1CorY);
        
        // Creación del pozo 2
        int p2CorX = (int)Math.floor(Math.random()*(4-0+1)+0);
        int p2CorY = (int)Math.floor(Math.random()*(4-0+1)+0);
        
        Pit pozo2 = new Pit(p2CorX, p2CorY);
        
        // ~~~~~~~~~~ COLOCAR ELEMENTOS EN EL TABLERO ~~~~~~~~~~
        // Colocar a Aveline
        field[aveline.getCorX()][aveline.getCorY()] = 1;

        // Colocar a Erina
        int eVal = 1;
        while(eVal == 1) {
            if(field[erina.getCoorX()][erina.getCoorY()] == 0) {
                field[erina.getCoorX()][erina.getCoorY()] = 2;
                eVal = 0;
            } else {
                erina.genCor();
            }    
        }
       
        
        // Colocar el tesoro
        int tVal = 1;
        while(tVal == 1) {
            if(field[tesoro.getCorX()][tesoro.getCorY()] == 0) {
                field[tesoro.getCorX()][tesoro.getCorY()] = 4;
                tVal = 0;
            } else {
                tesoro.genCor();
            }    
        }
        
        // Colocar los pozos
        // Pozo 1
        int p1Val = 1;
        while(p1Val == 1) {
            if(field[pozo1.getCorX()][pozo2.getCorY()] == 0) {
                field[pozo1.getCorX()][pozo2.getCorY()] = 3;
                p1Val = 0;
            } else {
                pozo1.genCor();
            }    
        }
        
        // Pozo 2
        int p2Val = 1;
        while(p2Val == 1) {
            if(field[pozo1.getCorX()][pozo2.getCorY()] == 0) {
                field[pozo1.getCorX()][pozo2.getCorY()] = 3;
                p2Val = 0;
            } else {
                pozo2.genCor();
            }    
        }
        
        /*while(lp == 1) {
           int aCorX = aveline.getCorX();
           int aCorY = aveline.getCorY();
           lp = 0;
        } */
        
        //Comprobar lo que hay dentro de la matriz
        for (int i=0; i < field.length; i++) {
            for (int j=0; j < field.length; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.print("\n");
        }
        
        // BUCLE PRINCIPAL
        
        
    }
}
