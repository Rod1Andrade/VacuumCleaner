package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class RightChair extends RenderEntity {

    private static final Sprite rChairSprite = new Sprite(113, 32, 14, 19, Loader.spriteSheet);

    public RightChair(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, rChairSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }
}
