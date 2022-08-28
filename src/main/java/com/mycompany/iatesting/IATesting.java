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
        int eVal = 1; // Variable para evaluar el while
        while(eVal == 1) {
            if(field[erina.getCoorX()][erina.getCoorY()] == 0) {
                field[erina.getCoorX()][erina.getCoorY()] = 2;
                eVal = 0;
            } else {
                erina.genCor();
            }    
        }
       
        
        // Colocar el tesoro
        int tVal = 1; // Variable para evaluar el while
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
        int p1Val = 1; // Variable para evaluar el while
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

        
        //Comprobar lo que hay dentro de la matriz
        for (int i=0; i < field.length; i++) {
            for (int j=0; j < field.length; j++) {
                System.out.print("[" + field[i][j] + "]");
            }
            System.out.print("\n");
        }
        
        // ~~~~~~~~~~~~~~~~~~~ BUCLE PRINCIPAL ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GAMELOOP
        int game = 1;
        while(game == 1) {
            //ESCANEAR CASILLAS ADYACENTES
            // Matriz para almacenar los datos de las casillas adyacentes: valor y coordenadas
            int[][] data = new int[8][3];

            //Almacenando las coordenadas actuales de Aveline
            int currentX = aveline.getCorX();
            int currentY = aveline.getCorY();
            
            /*Coordenadas auxiliares
            Las auxiliares 1: Empiezan en la esquina inferior derecha
            Las auxiliares 2: Empiezan en la esquina superior izquierda
            */
            int auxX = currentX - 1;
            int auxY = currentY - 1;

            int aux2X = currentX + 1;
            int aux2Y = currentY + 1;
            
            // Variable auxiliar para el arreglo de datos
            int dataAux = 0;
            
            //Coordenadas auxiliares (posición en la esquina superior izquiera de Aveline)
            
            //~~~~~~Bucles para almacenar las casillas en un arreglo.~~~~~
            /*
            El bucle inicia en la esquina inferior derecha y continua hacia arriba.
            Si las coordenadas no existen en el area de juego no se almacena en el arreglo
            Si las coordenadas se encuentran se guarda en el arreglo un arreglo de la siguiente manera:
            [valor, X, Y]
            */
            for(int c = 1; c < 4; c++) {
                if(auxX <= 4 && auxX >= 0 && auxY <= 4 && auxY >= 0) {
                        data[dataAux][0] = field[auxX][auxY];
                        data[dataAux][1] = auxX;
                        data[dataAux][2] = auxY;
                        dataAux++;
                }
                auxY++;
                
            }
            //Se reinician las coordenadas auxiliares
            auxX = currentX - 1;
            auxY = currentY - 1;
            
            /*
            El bucle inicia en la esquina inferior derecha y continua hacia la izquierda en X
            */
            for(int c = 1; c < 4; c++) {
                if(auxX <= 4 && auxX >= 0 && auxY <= 4 && auxY >= 0) {
                    data[dataAux][0] = field[auxX][auxY];
                    data[dataAux][1] = auxX;
                    data[dataAux][2] = auxY;
                    dataAux++;
                }
                auxX++;
            }
            
            /*
            El bucle inicia en la esquina superior izquiera y continua hacia la derecha en X
            Utiliza las variables auxiliares 2
            */
            for(int c = 1; c < 3; c++) {
                if(aux2X <= 4 && aux2X >= 0 && aux2Y <= 4 && aux2Y >= 0) {
                   data[dataAux][0] = field[aux2X][aux2Y];
                   data[dataAux][1] = aux2X;
                   data[dataAux][2] = aux2Y;
                   dataAux++;
                }
                aux2X--;
            }
            
            aux2X = currentX + 1;
            aux2Y = currentY + 1;
            
            for(int c = 1; c < 3; c++) {
                if(aux2X <= 4 && aux2X >= 0 && aux2Y <= 4 && aux2Y >= 0) {
                   data[dataAux][0] = field[aux2X][aux2Y];
                   data[dataAux][1] = aux2X;
                   data[dataAux][2] = aux2Y;
                }
                aux2Y--;
            }
            
            
            for(int i = 0; i < 8; i++) {
                System.out.println("Valor: " + data[i][0] + " X: " + data[i][1] + " Y: " + data[i][2]);
            }
            
            game = 0;
        }
    }
}
