package com.github.rod1andrade.ui.infos;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.entities.RenderEntity;
import com.github.rod1andrade.models.VacuumCleanerModel;
import com.github.rod1andrade.util.Loader;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public class DirectionBoxUI extends RenderEntity {

    private static Sprite directionHudSprite = new Sprite(84, 95, 41, 32, Loader.spriteSheet001);
    private VacuumCleanerModel vacuumCleanerModel;
    private int arrowDirection = 1;

    private Sprite[] arrowDirections = {
            new Sprite(18, 112, 18, 15, Loader.spriteSheet001), // LEFT_SPRITE
            new Sprite(0, 112, 18, 15, Loader.spriteSheet001), // RIGHT_SPRITE
            new Sprite(51, 111, 16, 17, Loader.spriteSheet001), // UPPER_SPRITE
            new Sprite(37, 111, 14, 17, Loader.spriteSheet001), // DOWN_SPRITE
    };

    public DirectionBoxUI(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, directionHudSprite, null, false);
    }

    public void setVacuumCleaner(VacuumCleanerModel vacuumCleanerModel) {
        this.vacuumCleanerModel = vacuumCleanerModel;
        arrowDirection = vacuumCleanerModel.getDirection();
    }

    public void update(float deltaTime) {
        arrowDirection = vacuumCleanerModel.getDirection();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(directionHudSprite.getSprite(SCALE_FACTOR), posX, posY, null);
        graphics.drawImage(arrowDirections[arrowDirection].getSprite(SCALE_FACTOR), posX  + 50, posY + 32, null);
    }
}
