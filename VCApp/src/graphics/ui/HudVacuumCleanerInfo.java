package graphics.ui;

import graphics.assets.Sprite;
import graphics.objects.Entity;
import graphics.objects.VacuumCleaner;
import graphics.util.Loader;

import java.awt.*;

public class HudVacuumCleanerInfo extends Entity {

    private static Sprite directionHudSprite = new Sprite(84, 95, 41, 32, Loader.spriteSheet001);
    private VacuumCleaner vacuumCleaner;
    private int arrowDirection = 1;

    private Sprite[] arrowDirections = {
            new Sprite(18, 112, 18, 15, Loader.spriteSheet001), // LEFT_SPRITE
            new Sprite(0, 112, 18, 15, Loader.spriteSheet001), // RIGHT_SPRITE
            new Sprite(51, 111, 16, 17, Loader.spriteSheet001), // UPPER_SPRITE
            new Sprite(37, 111, 14, 17, Loader.spriteSheet001), // DOWN_SPRITE
    };

    public HudVacuumCleanerInfo(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, directionHudSprite, null);
    }

    public void setVacuumCleaner(VacuumCleaner vacuumCleaner) {
        this.vacuumCleaner = vacuumCleaner;
        arrowDirection = vacuumCleaner.getActualDirection();
    }

    public void update(float deltaTime) {
        arrowDirection = vacuumCleaner.getActualDirection();
    }

    public void render(Graphics graphics) {
        graphics.drawImage(directionHudSprite.getSprite(SCALE_FACTOR), posX, posY, null);
        graphics.drawImage(arrowDirections[arrowDirection].getSprite(SCALE_FACTOR), (posX + width / 2) - 32, (posY + height / 2) - 22, null);
    }
}
