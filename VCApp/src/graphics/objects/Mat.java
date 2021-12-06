package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Mat extends Entity {

    private static final Sprite matSprite = new Sprite(32, 0, 48, 32, Loader.spriteSheet);

    public Mat(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, matSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
