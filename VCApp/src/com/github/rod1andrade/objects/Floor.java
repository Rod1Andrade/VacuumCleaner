package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Floor extends Entity {

    private static final Sprite floorSprite = new Sprite(0, 32, 16, 16, Loader.spriteSheet);

    public Floor(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, floorSprite, null);
    }

    @Override
    public void update(float deltaTime) {

    }
}
