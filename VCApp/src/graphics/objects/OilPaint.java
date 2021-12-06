package graphics.objects;

import graphics.assets.Sprite;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class OilPaint extends Entity {

    private static final Sprite oilPaintSprite = new Sprite(32, 32, 16, 16, Loader.spriteSheet);

    public OilPaint(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, oilPaintSprite);
    }

    @Override
    public void update(float deltaTime) {

    }
}
