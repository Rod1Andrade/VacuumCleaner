package graphics.objects;

import graphics.assets.Sprite;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Window extends Entity {

    private static final Sprite windowSprite = new Sprite(1, 66, 14, 14, Loader.spriteSheet);


    public Window(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, windowSprite);
    }

    @Override
    public void update(float deltaTime) {

    }
}
