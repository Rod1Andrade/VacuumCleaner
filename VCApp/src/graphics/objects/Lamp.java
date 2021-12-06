package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Lamp extends Entity {

    private static final Sprite lampSprite = new Sprite(82, 51, 13, 27, Loader.spriteSheet);

    public Lamp(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, lampSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {
    }
}
