package com.mycompany.iatesting;

/**
 *
 * @author Iraha
 */
public class Enemy {
    
    int eCorX, eCorY, status;
    
    public Enemy(int coorX, int coorY) {
        this.eCorX = coorX;
        this.eCorY = coorY;
        this.status = 1;
    }

    public int getCoorX() {
        return eCorX;
    }

    public void setCoorX(int coorX) {
        this.eCorX = coorX;
    }

    public int getCoorY() {
        return eCorY;
    }

    public void setCoorY(int coorY) {
        this.eCorY = coorY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public void genCor() {
       this.eCorX = (int)Math.floor(Math.random()*(4-0+1)+0);
       this.eCorY = (int)Math.floor(Math.random()*(4-0+1)+0);
    }
    
}
