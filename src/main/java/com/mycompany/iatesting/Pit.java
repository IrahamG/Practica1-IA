package com.mycompany.iatesting;

/**
 *
 * @author Iraha
 */
public class Pit {
    
    int corX, corY;
    
    public Pit(int corX, int corY) {
        this.corX = corX;
        this.corY = corY;
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
    
    public void genCor() {
       this.corX = (int)Math.floor(Math.random()*(4-0+1)+0);
       this.corY = (int)Math.floor(Math.random()*(4-0+1)+0);
    }
    
}
