package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public class StripedWall extends RenderEntity {

    private static final Sprite stripedWall = new Sprite(0, 0, 16, 32, Loader.spriteSheet);

    public StripedWall(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, stripedWall, new BoxCollision(posX, posY, width, height), false);
    }

    @Override
    public void update(float deltaTime) {

    }
}
