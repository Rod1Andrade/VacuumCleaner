package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public final class Television extends Entity {

    private static final Sprite tvSprite = new Sprite(1, 48, 16, 16, Loader.spriteSheet);

    public Television(int posX, int posY, int width, int height, boolean isDebugMode) {
        super(posX, posY, width, height, tvSprite, new BoxCollision(posX, posY, width, height), isDebugMode);
    }

    @Override
    public void update(float deltaTime) {
    }

}
