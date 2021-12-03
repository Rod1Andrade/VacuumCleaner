package graphics.render;

import graphics.objects.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RenderEngine {

    protected final List<Entity> entities = new ArrayList<>();
    protected final int width;
    protected final int height;

    public RenderEngine(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addEntityToRender(Entity entity) {
        entities.add(entity);
    }

    public void clear(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
    }

    public void render(Graphics graphics) {
        for (Entity entity : entities) {
            entity.render(graphics);
        }
    }

    public void update(float deltaTime) {
        for (Entity entity : entities) {
            entity.update(deltaTime);
        }
    }
}

