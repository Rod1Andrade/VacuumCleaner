package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Shelf extends Entity {

    private static final Sprite shelfSprite = new Sprite(80, 22, 16, 27, Loader.spriteSheet);

    public Shelf(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, shelfSprite, new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void update(float deltaTime) {

    }
}
