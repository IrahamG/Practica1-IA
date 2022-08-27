package com.mycompany.iatesting;

/**
 *
 * @author chaos
 */

/*
El agente iniciará en las coordenadas 0,0 de la matriz, siempre.
El status 1 indica el estado normal
El status 2 indica que el monstruo lo tocó o tocó un obstaculo
El status 3 indica que llegó al tesoro.
*/

public class Agent {
    
    int corX, corY, status;
    
    public Agent() {
        this.corX = 4;
        this.corY = 0;
        this.status = 1;
    }

    public int getCorX() {
        return corX;
    }

    public void setCorX(int corX) {
        this.corX = corX;
    }

    public int getCorY() {
        return corY;
    }

    public void setCorY(int corY) {
        this.corY = corY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    /*
    Generar un método que guarde todas las coordenadas en las que el agente
    ya ha estado y otro metodo que recupere esas coordenadas o que compare
    las coordenadas actuales con las que siguen.
    */
    
    /*
    TODO: Método para escanear las casillas adyacentes
    */
    
}
