package graphics.render;

import java.awt.*;

public class Render {

    protected final int width;
    protected final int height;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
    }


    public void clear(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
    }

    public void render(Graphics graphics) {

    }
}

