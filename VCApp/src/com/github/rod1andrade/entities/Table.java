package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Table extends RenderEntity {

    private static final Sprite tableSprite = new Sprite(97, 7, 30, 22, Loader.spriteSheet);

    public Table(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, tableSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }
}
