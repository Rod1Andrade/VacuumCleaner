package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class FrontChair extends Entity {

    private static final Sprite fChairSprite = new Sprite(97, 54, 14, 19, Loader.spriteSheet);

    public FrontChair(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, fChairSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
