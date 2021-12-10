package com.github.rod1andrade.entities;

import com.github.rod1andrade.util.Loader;
import com.github.rod1andrade.assets.Sprite;

/**
 * @author Rodrigo Andrade
 */
public final class OilPaint extends RenderEntity {

    private static final Sprite oilPaintSprite = new Sprite(32, 32, 16, 16, Loader.spriteSheet);

    public OilPaint(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, oilPaintSprite, null, false);
    }

    @Override
    public void update(float deltaTime) {

    }
}
