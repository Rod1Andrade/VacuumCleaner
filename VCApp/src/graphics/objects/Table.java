package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Table extends Entity {

    private static final Sprite tableSprite = new Sprite(97, 7, 30, 22, Loader.spriteSheet);

    public Table(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, tableSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
