package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class FrontChair extends RenderEntity {

    private static final Sprite fChairSprite = new Sprite(97, 54, 14, 19, Loader.spriteSheet);

    public FrontChair(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, fChairSprite, new BoxCollision(posX, posY, width, height), false);
    }

    @Override
    public void update(float deltaTime) {

    }
}
