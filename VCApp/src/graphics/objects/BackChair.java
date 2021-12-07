package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class BackChair extends Entity {

    private static final Sprite bChairSprite = new Sprite(97, 32, 14, 19, Loader.spriteSheet);

    public BackChair(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, bChairSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}