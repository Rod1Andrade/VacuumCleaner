package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class BackChair extends Entity {

    private static final Sprite bChairSprite = new Sprite(97, 32, 14, 19, Loader.spriteSheet);

    public BackChair(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, bChairSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }
}
