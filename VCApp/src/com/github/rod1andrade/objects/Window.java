package com.github.rod1andrade.objects;

import com.github.rod1andrade.util.Loader;
import com.github.rod1andrade.assets.Sprite;

/**
 * @author Rodrigo Andrade
 */
public final class Window extends Entity {

    private static final Sprite windowSprite = new Sprite(1, 66, 14, 14, Loader.spriteSheet);


    public Window(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, windowSprite, null, false);
    }

    @Override
    public void update(float deltaTime) {

    }
}
