package com.github.rod1andrade.objects;

import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;
import com.github.rod1andrade.assets.Sprite;

/**
 * @author Rodrigo Andrade
 */
public final class LeftChair extends Entity{

    private static final Sprite lChairSprite = new Sprite(113, 52, 14, 19, Loader.spriteSheet);

    public LeftChair(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, lChairSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
