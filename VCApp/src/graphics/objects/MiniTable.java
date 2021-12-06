package graphics.objects;

import graphics.assets.Sprite;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class MiniTable extends Entity {

    private static final Sprite minTableSprite = new Sprite(64, 33, 16, 16, Loader.spriteSheet);

    public MiniTable(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, minTableSprite);
    }

    @Override
    public void update(float deltaTime) {

    }
}
