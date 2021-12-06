package graphics.objects;

import graphics.assets.Sprite;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class LeftChair extends Entity{

    private static final Sprite lChairSprite = new Sprite(113, 52, 14, 19, Loader.spriteSheet);

    public LeftChair(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, lChairSprite);
    }

    @Override
    public void update(float deltaTime) {

    }
}
