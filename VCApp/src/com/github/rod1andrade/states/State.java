package com.github.rod1andrade.states;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public abstract class State {
    private String name;

    public abstract void initializeResources();
    public abstract void update(float deltaTime);
    public abstract void render(Graphics graphics);
}
