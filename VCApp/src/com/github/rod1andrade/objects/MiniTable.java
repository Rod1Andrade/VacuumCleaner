package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class MiniTable extends Entity {

    private static final Sprite minTableSprite = new Sprite(64, 33, 16, 16, Loader.spriteSheet);

    public MiniTable(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, minTableSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {

    }
}
