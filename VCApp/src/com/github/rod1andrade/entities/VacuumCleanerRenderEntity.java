package com.github.rod1andrade.entities;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.audio.AudioPlayer;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Config;
import com.github.rod1andrade.util.Loader;

import java.util.Random;

/**
 * @author Rodrigo Andrade
 */
public class VacuumCleanerRenderEntity extends RenderEntity {

    // Constante de direcoes
    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UPPER = 2;
    public static final int DIRECTION_DOWN = 3;

    // Fatores de modulo e direcao
    private int speed = 2;
    private int xVelocity = speed;
    private int yVelocity = 0;
    private int previousDirection = DIRECTION_LEFT;
    private int actualDirection = DIRECTION_RIGHT;



    // Sprites de cada direcao
    private final Sprite[] sprites = {new Sprite(80, 48, 16, 16, Loader.spriteSheet001),
            new Sprite(80, 32, 16, 16, Loader.spriteSheet001), new Sprite(96, 48, 16, 16, Loader.spriteSheet001),
            new Sprite(96, 32, 16, 16, Loader.spriteSheet001),};

    // Entidades de colisao
    private RenderEntity[] entities;

    // Classe Random (gerar valores aleatorios)
    private Random random = new Random();

    private AudioPlayer movimentfx;

    public VacuumCleanerRenderEntity(int posX, int posY, int width, int height, boolean isDebugMode) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;

        this.sprite = sprites[actualDirection];
        this.isDebugMode = isDebugMode;

        setBoxCollision(new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void initResources() {
        super.initResources();
        movimentfx = new AudioPlayer("assets/audios/mixkit-small-hit-in-a-game-2072.wav");
        speed = Config.getInstance().getVacuumCleanerVelocity();
    }

    @Override
    public void update(float deltaTime) {
        if (hasChangedDirection()) {
            changeSpriteDirection();
        }
    }

    /**
     * Vefifica se houve mudanca de direcao.
     *
     * @return
     */
    private boolean hasChangedDirection() {
        return previousDirection != actualDirection;
    }

    /**
     * Muda a direcao do sprite.
     */
    private void changeSpriteDirection() {
        this.sprite = this.sprites[actualDirection];
    }

    public void setPreviousDirection(int direction) {
        this.previousDirection = direction;
    }

    public void setAxisVelocity(int xVelocity, int yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void setDirection(int direction) {
        this.actualDirection = direction;
    }

    public void setPositionValues(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        boxCollision.setValues(posX, posY);
    }

    public int getYVelocitoy() {
        return yVelocity;
    }

    public int getXVelocity() {
        return xVelocity;
    }

}
