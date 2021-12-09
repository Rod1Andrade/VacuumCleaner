package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Lamp extends Entity {

    private static final Sprite lampSprite = new Sprite(82, 51, 13, 27, Loader.spriteSheet);

    public Lamp(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, lampSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {
    }
}
