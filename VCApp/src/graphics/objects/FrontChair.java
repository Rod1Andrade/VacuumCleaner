package graphics.objects;

import graphics.assets.Sprite;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class FrontChair extends Entity {

    private static final Sprite fChairSprite = new Sprite(97, 54, 14, 19, Loader.spriteSheet);

    public FrontChair(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, fChairSprite);
    }

    @Override
    public void update(float deltaTime) {

    }
}
