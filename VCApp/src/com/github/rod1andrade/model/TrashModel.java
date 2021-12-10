package com.github.rod1andrade.model;

/**
 * @author Rodrigo Andrade
 */
public class TrashModel {
    private int id;
    private int posX;
    private int posY;
    private boolean isCollected;

    public TrashModel () {}

    public TrashModel(int id, int posX, int posY, boolean isCollected) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.isCollected = false;
    }

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }
}
