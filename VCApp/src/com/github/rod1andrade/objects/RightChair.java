package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

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
