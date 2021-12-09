package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Plant extends Entity {

    private static final Sprite plantSprite = new Sprite(81, 1, 16, 20, Loader.spriteSheet);

    public Plant(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, plantSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }
}
