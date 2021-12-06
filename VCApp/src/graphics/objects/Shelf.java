package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Shelf extends Entity {

    private static final Sprite shelfSprite = new Sprite(80, 22, 16, 27, Loader.spriteSheet);

    public Shelf(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, shelfSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
