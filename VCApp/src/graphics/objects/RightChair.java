package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class RightChair extends Entity{

    private static final Sprite rChairSprite = new Sprite(113, 32, 14, 19, Loader.spriteSheet);

    public RightChair(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, rChairSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
