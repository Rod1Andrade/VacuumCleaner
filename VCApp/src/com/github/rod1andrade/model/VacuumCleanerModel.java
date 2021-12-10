package com.github.rod1andrade.model;

/**
 * @author Rodrigo Andrade
 */
public class VacuumCleanerModel {
    private int velocity;
    private int direction;
    private int collisionDirection;
    private boolean hasCollision;

    public VacuumCleanerModel() {
    }

    public VacuumCleanerModel(int velocity, int direction, boolean hasCollision) {
        this.velocity = velocity;
        this.direction = direction;
        this.hasCollision = hasCollision;
        collisionDirection = -1;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void setHasCollision(boolean hasCollision) {
        if(hasCollision)
            collisionDirection = direction;

        this.hasCollision = hasCollision;
    }

    public int collidedDirection() {
        return  collisionDirection;
    }
}
