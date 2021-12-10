package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Lamp extends RenderEntity {

    private static final Sprite lampSprite = new Sprite(82, 51, 13, 27, Loader.spriteSheet);

    public Lamp(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, lampSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {
    }
}
