package graphics.objects;

import graphics.assets.Sprite;

import java.awt.*;

public class Television extends Entity {

    public Television() {
    }

    public Television(int posX, int posY, int width, int height, Sprite sprite) {
        super(posX, posY, width, height, sprite);
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(sprite.getSprite(4), posX, posY, null);
    }
}
