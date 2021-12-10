package com.github.rod1andrade.render;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rodrigo Andraade
 */
public final class Render {

    private final List<com.github.rod1andrade.entities.RenderEntity> entities = new ArrayList<>();
    private final int width;
    private final int height;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Adiciona entidade que vai ser renderizada.
     * A ordem na qual e adicionada define a ordem de renderizacao.
     *
     * @param renderEntity Entity.
     */
    public void addEntityToRender(com.github.rod1andrade.entities.RenderEntity renderEntity) {
        entities.add(renderEntity);
    }

    public void clear(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
    }

    public void render(Graphics graphics) {
        for (com.github.rod1andrade.entities.RenderEntity renderEntity : entities) {
            renderEntity.render(graphics);
        }
    }

    public synchronized void update(float deltaTime) {
        for (com.github.rod1andrade.entities.RenderEntity renderEntity : entities) {
            renderEntity.update(deltaTime);
        }
    }
}

