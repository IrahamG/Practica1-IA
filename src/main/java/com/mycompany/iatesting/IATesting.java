package com.mycompany.iatesting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author chaos
 */
public class IATesting {
    

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        Los elementos del campo pueden ser representados con valores
        numericos:
        1 - El agente
        2 - El enemigo
        3 - Pozo
        4 - Tesoro
        5 - Casilla previamente visitada
        Tanto los pozos como el teesoro seran estaticos y ellos y el monstruo
        serán generados al azar.
        */
        // GENERACIÓN DE CAMPO DE JUEGO Y LLENADO CON LOS VALORES
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
            if(field[pozo1.getCorX()][pozo1.getCorY()] == 0) {
                field[pozo1.getCorX()][pozo1.getCorY()] = 3;
                p1Val = 0;
            } else {
                pozo1.genCor();
            }    
        }
        
        // Pozo 2
        int p2Val = 1;
        while(p2Val == 1) {
            if(field[pozo2.getCorX()][pozo2.getCorY()] == 0) {
                field[pozo2.getCorX()][pozo2.getCorY()] = 3;
                p2Val = 0;
            } else {
                pozo2.genCor();
            }    
        }


        int turnos = 0;
        //Arreglo de casillas prohibidas. Se encuentra fuera del bucle por que es un arreglo que permanece
        //a lo largo de la partida
        ArrayList<int[]> casillasProhibidas = new ArrayList<int[]>();
        
        
        // ~~~~~~~~~~~~~~~~~~~ BUCLE PRINCIPAL ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GAMELOOP
        while(true) {
            
            // ~~~ MOSTRAR LA MATRIZ EN PANTALLA ~~~
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    System.out.print("[" + field[i][j] + "]");
                }
                System.out.print("\n");
            }
            System.out.print("\n"); 
            
            if (aveline.getStatus() == 2) {
                break;
            }
            
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            //~~~~~~ TURNO DE AVELINE ~~~~~~~~~~~
            //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

           
            if(turnos != 3) {
                int[] coordenadasProhibidas = new int[2];

                //~~~~~~~~~~~~~~~~~~~~~ESCANEAR CASILLAS ADYACENTES~~~~~~~~~~~~~~~~~~~~~~~~~
                // Matriz para almacenar los datos de las casillas adyacentes: valor y coordenadas
                int[][] data = new int[8][3];

                //Almacenando las coordenadas actuales de Aveline
                int currentX = aveline.getCorX();
                int currentY = aveline.getCorY();

                /*Coordenadas auxiliares
                Las auxiliares 1: Empiezan en la esquina inferior derecha
                Las auxiliares 2: Empiezan en la esquina superior izquierda
                */
                int auxX = currentX + 1;
                int auxY = currentY + 1;

                int aux2X = currentX - 1;
                int aux2Y = currentY - 1;

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
                for (int c = 1; c < 4; c++) {
                    if (auxX <= 4 && auxX >= 0 && auxY <= 4 && auxY >= 0) {
                        if(field[auxX][auxY] == 3) {
                            coordenadasProhibidas[0] = auxX;
                            coordenadasProhibidas[1] = auxY;
                            casillasProhibidas.add(coordenadasProhibidas);
                        }
                        data[dataAux][0] = field[auxX][auxY];
                        data[dataAux][1] = auxX;
                        data[dataAux][2] = auxY;
                        dataAux++;
                    }
                    auxX--;

                }
                //Se reinician las coordenadas auxiliares
                auxX = currentX + 1;
                auxY = currentY;

                /*
                El bucle inicia debajo de la coordenada y continua hacia la izquierda en X
                */
                for (int c = 1; c < 3; c++) {
                    if (auxX <= 4 && auxX >= 0 && auxY <= 4 && auxY >= 0) {
                        if(field[auxX][auxY] == 3) {
                            coordenadasProhibidas[0] = auxX;
                            coordenadasProhibidas[1] = auxY;
                            casillasProhibidas.add(coordenadasProhibidas);
                        }
                        data[dataAux][0] = field[auxX][auxY];
                        data[dataAux][1] = auxX;
                        data[dataAux][2] = auxY;
                        dataAux++;
                    }
                    auxY--;
                }

                /*
                El bucle inicia en la esquina superior izquiera y continua hacia la derecha en X
                Utiliza las variables auxiliares 2
                */
                for (int c = 1; c < 3; c++) {
                    if (aux2X <= 4 && aux2X >= 0 && aux2Y <= 4 && aux2Y >= 0) {
                        if(field[aux2X][aux2Y] == 3) {
                            coordenadasProhibidas[0] = auxX;
                            coordenadasProhibidas[1] = auxY;
                            casillasProhibidas.add(coordenadasProhibidas);
                        }
                        data[dataAux][0] = field[aux2X][aux2Y];
                        data[dataAux][1] = aux2X;
                        data[dataAux][2] = aux2Y;
                        dataAux++;
                    }
                    aux2Y++;
                }

                aux2X = currentX;
                aux2Y = currentY - 1;

                for (int c = 1; c < 2; c++) {
                    if (aux2X <= 4 && aux2X >= 0 && aux2Y <= 4 && aux2Y >= 0) {
                        if(field[aux2X][aux2Y] == 3) {
                            coordenadasProhibidas[0] = auxX;
                            coordenadasProhibidas[1] = auxY;
                            casillasProhibidas.add(coordenadasProhibidas);
                        }
                        data[dataAux][0] = field[aux2X][aux2Y];
                        data[dataAux][1] = aux2X;
                        data[dataAux][2] = aux2Y;
                    }
                }

                
                

                //FIN DE LA ETAPA DE ESCANEO


                //~~~~ FUNCIÓN DE MOVIMIENTO DE AVELINE ~~~~~
                // Aveline solo puede moverse a las casillas adyacentes a la que esta
                // Aveline no puede moverse en diagonal ni a una casilla que no este vacía
                // Si la casilla es 0, se mueve a la casilla
                ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();

                //Iterar las casillas adyacentes para encontrar las casillas a las que se puede mover
                for (int i = 0; i <= dataAux; i++) {
                        int[] movimiento = new int[2];
                        
                            if(data[i][1] == currentX-1 && data[i][2] == currentY && field[data[i][1]][data[i][2]] != 5) {
                                movimiento[0] = data[i][1];
                                movimiento[1] = data[i][2];
                                movimientosPosibles.add(movimiento);
                            } else if(data[i][1] == currentX+1 && data[i][2] == currentY && field[data[i][1]][data[i][2]] != 5) {
                                movimiento[0] = data[i][1];
                                movimiento[1] = data[i][2];
                                movimientosPosibles.add(movimiento);
                            } else if(data[i][1] == currentX && data[i][2] == currentY-1 && field[data[i][1]][data[i][2]] != 5) {
                                movimiento[0] = data[i][1];
                                movimiento[1] = data[i][2];
                                movimientosPosibles.add(movimiento);
                            } else if(data[i][1] == currentX && data[i][2] == currentY+1 && field[data[i][1]][data[i][2]] != 5) {
                                movimiento[0] = data[i][1];
                                movimiento[1] = data[i][2];
                                movimientosPosibles.add(movimiento);
                            }
                        
                }

                if(movimientosPosibles.size() == 0) {
                    System.out.println("Aveline no puede moverse");
                    break;
                }

                //Crear un número aleatorio para elegir un movimiento
                Random r = new Random();
                int random = r.nextInt(movimientosPosibles.size());


                //Mover aveline a la casilla aleatoria
                aveline.setCorX(movimientosPosibles.get(random)[0]);
                aveline.setCorY(movimientosPosibles.get(random)[1]);

                //Comprobar si Aveline se encuentra en el tesoro
                if (aveline.getCorX() == tesoro.getCorX() && aveline.getCorY() == tesoro.getCorY()) {
                    System.out.println("Aveline ha encontrado el tesoro!");
                    aveline.setStatus(2);
                }
                
                if (aveline.getCorX() == pozo1.getCorX() && aveline.getCorY() == pozo1.getCorY()) {
                    System.out.println("Aveline ha caido en un pozo!");
                    aveline.setStatus(2);
                }
                
                if (aveline.getCorX() == pozo2.getCorX() && aveline.getCorY() == pozo2.getCorY()) {
                    System.out.println("Aveline ha caido en un pozo!");
                    aveline.setStatus(2);
                }
                
                if (aveline.getCorX() == erina.getCoorX() && aveline.getCorY() == erina.getCoorY()) {
                    System.out.println("Erina ha atrapado a Aveline!");
                    aveline.setStatus(2);
                }

                //Hacer la posición previa de Aveline 5
                field[currentX][currentY] = 5;

                field[aveline.getCorX()][aveline.getCorY()] = 1;

                /*for(int i = 0; i < 8; i++) {
                    System.out.println("Valor: " + data[i][0] + " X: " + data[i][1] + " Y: " + data[i][2]);
                }

                //Imprimir el arreglo de movimientos posibles
                for(int i = 0; i < movimientosPosibles.size(); i++) {
                    System.out.println("X: " + movimientosPosibles.get(i)[0] + " Y: " + movimientosPosibles.get(i)[1]);
                }

                System.out.println("Coordenada X: " + aveline.getCorX() + " Coordenada Y: " + aveline.getCorY()); */

                turnos++;
            }

            // MOVIMIENTO DE ERINA
            // Erina se mueve cada 3 turnos de Aveline
            if(turnos == 3) {
                //~~~~~~~~~~~~~~~~~~~~~ESCANEAR CASILLAS ADYACENTES~~~~~~~~~~~~~~~~~~~~~~~~~
                // Matriz para almacenar los datos de las casillas adyacentes: valor y coordenadas
                int[][] data = new int[8][3];

                //Almacenando las coordenadas actuales de Aveline
                int currentX = erina.getCoorX();
                int currentY = erina.getCoorY();

                /*Coordenadas auxiliares
                Las auxiliares 1: Empiezan en la esquina inferior derecha
                Las auxiliares 2: Empiezan en la esquina superior izquierda
                */
                int auxX = currentX + 1;
                int auxY = currentY + 1;

                int aux2X = currentX - 1;
                int aux2Y = currentY - 1;

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
                for (int c = 1; c < 4; c++) {
                    if (auxX <= 4 && auxX >= 0 && auxY <= 4 && auxY >= 0) {
                        data[dataAux][0] = field[auxX][auxY];
                        data[dataAux][1] = auxX;
                        data[dataAux][2] = auxY;
                        dataAux++;
                    }
                    auxX--;

                }
                //Se reinician las coordenadas auxiliares
                auxX = currentX + 1;
                auxY = currentY;

                /*
                El bucle inicia debajo de la coordenada y continua hacia la izquierda en X
                */
                for (int c = 1; c < 3; c++) {
                    if (auxX <= 4 && auxX >= 0 && auxY <= 4 && auxY >= 0) {
                        data[dataAux][0] = field[auxX][auxY];
                        data[dataAux][1] = auxX;
                        data[dataAux][2] = auxY;
                        dataAux++;
                    }
                    auxY--;
                }

                /*
                El bucle inicia en la esquina superior izquiera y continua hacia la derecha en X
                Utiliza las variables auxiliares 2
                */
                for (int c = 1; c < 3; c++) {
                    if (aux2X <= 4 && aux2X >= 0 && aux2Y <= 4 && aux2Y >= 0) {
                        data[dataAux][0] = field[aux2X][aux2Y];
                        data[dataAux][1] = aux2X;
                        data[dataAux][2] = aux2Y;
                        dataAux++;
                    }
                    aux2Y++;
                }

                aux2X = currentX;
                aux2Y = currentY - 1;

                for (int c = 1; c < 2; c++) {
                    if (aux2X <= 4 && aux2X >= 0 && aux2Y <= 4 && aux2Y >= 0) {
                        data[dataAux][0] = field[aux2X][aux2Y];
                        data[dataAux][1] = aux2X;
                        data[dataAux][2] = aux2Y;
                    }
                }


                //~~~~~ FUNCIÓN DE MOVIMIENTO DE ERINA ~~~~~
                //Arreglo para almacenar los movimientos posibles
                ArrayList<int[]> movimientosPosibles = new ArrayList<int[]>();

                for (int i = 0; i < dataAux; i++) {
                    if (data[i][0] == 0) {
                        int[] movimiento = new int[2];
                        movimiento[0] = data[i][1];
                        movimiento[1] = data[i][2];
                        movimientosPosibles.add(movimiento);
                    }
                }

                if(movimientosPosibles.size() == 0) {
                    System.out.println("Erina no puede moverse");
                    break;
                }

                //Crear un número aleatorio para elegir un movimiento
                Random r = new Random();
                int random = r.nextInt(movimientosPosibles.size());


                //Mover aveline a la casilla aleatoria
                erina.setCoorX(movimientosPosibles.get(random)[0]);
                erina.setCoorY(movimientosPosibles.get(random)[1]);

                //Comprobar si Erina está en la casilla de Aveline
                if (erina.getCoorX() == aveline.getCorX() && erina.getCoorY() == aveline.getCorY()) {
                    System.out.println("Erina ha ganado");
                    break;
                }

                //Hacer la posición previa de Erina 0
                field[currentX][currentY] = 0;

                field[erina.getCoorX()][erina.getCoorY()] = 2;

                /*for(int i = 0; i < 8; i++) {
                    System.out.println("Valor: " + data[i][0] + " X: " + data[i][1] + " Y: " + data[i][2]);
                }

                //Imprimir el arreglo de movimientos posibles
                for(int i = 0; i < movimientosPosibles.size(); i++) {
                    System.out.println("X: " + movimientosPosibles.get(i)[0] + " Y: " + movimientosPosibles.get(i)[1]);
                }

                System.out.println("Coordenada X: " + erina.getCoorX() + " Coordenada Y: " + erina.getCoorY()); */

                turnos = 0;

            }

            TimeUnit.SECONDS.sleep(1);

        }
    }
}
