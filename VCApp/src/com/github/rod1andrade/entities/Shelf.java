package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Shelf extends RenderEntity {

    private static final Sprite shelfSprite = new Sprite(80, 22, 16, 27, Loader.spriteSheet);

    public Shelf(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, shelfSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }
}
