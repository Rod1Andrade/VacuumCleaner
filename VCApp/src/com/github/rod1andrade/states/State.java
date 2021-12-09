package com.github.rod1andrade.states;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public abstract class State {

    public static final int STOP = 0;
    public static final int RUNNING = 1;
    public static final int PAUSE = 3;

    protected String name;
    protected int actualState;

    public State(String name, int actualState) {
        this.name = name;
        this.actualState = actualState;
    }

    public abstract void initializeResources();

    public abstract void update(float deltaTime);

    public abstract void render(Graphics graphics);

    public void pause() {
        this.actualState = STOP;
    }

    public void resume() {
        this.actualState = RUNNING;
    }
}
