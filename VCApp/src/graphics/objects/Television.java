package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Television extends Entity {

    private static final Sprite tvSprite = new Sprite(1, 48, 16, 16, Loader.spriteSheet);

    public Television(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, tvSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {
    }

}
