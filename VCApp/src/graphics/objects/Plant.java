package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Plant extends Entity {

    private static final Sprite plantSprite = new Sprite(81, 1, 16, 20, Loader.spriteSheet);

    public Plant(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, plantSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
