package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public class StripedWall extends Entity{

    private static final Sprite stripedWall = new Sprite(0, 0, 16, 32, Loader.spriteSheet);

    public StripedWall(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, stripedWall, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
