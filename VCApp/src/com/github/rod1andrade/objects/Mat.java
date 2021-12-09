package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Mat extends Entity {

    private static final Sprite matSprite = new Sprite(32, 0, 48, 32, Loader.spriteSheet);

    public Mat(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, matSprite, null);
    }

    @Override
    public void update(float deltaTime) {

    }
}
