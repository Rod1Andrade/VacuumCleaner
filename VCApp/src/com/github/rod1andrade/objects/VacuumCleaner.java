package com.github.rod1andrade.objects;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.audio.AudioPlayer;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.util.Config;
import com.github.rod1andrade.util.Loader;

import java.util.Random;

/**
 * @author Rodrigo Andrade
 */
public class VacuumCleaner extends Entity {

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

    // Controle de colisao
    private boolean hasCollision = false;
    private int collisionDirection = -1;

    // Variaveis de controle da atualizacao de movimentos com base no deltaTime.
    private int controllingDeltaTimeToCollision = 0;
    private long countTimeToChangeDirection = System.currentTimeMillis();
    public static final int RANDOM_MOVEMENT_COOLDOWN = 2000;

    // Sprites de cada direcao
    private final Sprite[] sprites = {new Sprite(80, 48, 16, 16, Loader.spriteSheet001),
            new Sprite(80, 32, 16, 16, Loader.spriteSheet001), new Sprite(96, 48, 16, 16, Loader.spriteSheet001),
            new Sprite(96, 32, 16, 16, Loader.spriteSheet001),};

    // Entidades de colisao
    private Entity[] entities;

    // Classe Random (gerar valores aleatorios)
    private Random random = new Random();

    private AudioPlayer movimentfx;

    public VacuumCleaner(int posX, int posY, int width, int height, boolean isDebugMode) {
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

    /**
     * Este metodo deve ser chamado antes da chamada do metodo update.
     *
     * @param entities Entity[]
     */
    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    public boolean hasCollided() {
        return hasCollision;
    }

    public void collision() {
        collisionDirection = -1;
        hasCollision = false;
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null && !(entities[i] instanceof VacuumCleaner)
                    && this.boxCollision.hasCollision(entities[i].boxCollision)) {
                hasCollision = true;
            }
        }

        if (posX + width >= Config.WINDOW_WIDTH) {
            hasCollision = true;
        }

        if (posY + height >= Config.WINDOW_HEIGHT - 130) {
            hasCollision = true;
        }
        if (posX <= 0) {
            hasCollision = true;
        }

        if (posY <= 130) {
            hasCollision = true;
        }

        if (hasCollision) collisionDirection = actualDirection;
    }

    @Override
    public void updateConfig(Config config) {
        super.updateConfig(config);
        speed = config.getVacuumCleanerVelocity();
    }

    @Override
    public void update(float deltaTime) {

        makeMovement();

        if (hasCollision && Config.getInstance().hasSound())
            movimentfx.play();


        if (hasChangedDirection()) {
            changeSpriteDirection();
        }

        if (hasCollision && controllingDeltaTimeToCollision > speed) {
            moveToOppositeDirection(actualDirection);
            controllingDeltaTimeToCollision = 0;
        }

        if (!hasCollision && System.currentTimeMillis() - countTimeToChangeDirection > RANDOM_MOVEMENT_COOLDOWN) {
            randomMovement();
            countTimeToChangeDirection = System.currentTimeMillis();
        }

        controllingDeltaTimeToCollision += deltaTime;
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

    /**
     * Move para a posicao oposta a passada como argumento.
     *
     * @param actualDirection int
     */
    private void moveToOppositeDirection(int actualDirection) {
        switch (actualDirection) {
            case DIRECTION_LEFT:
                moveToRight();
                break;
            case DIRECTION_RIGHT:
                moveToLeft();
                break;
            case DIRECTION_UPPER:
                moveToDown();
                break;
            case DIRECTION_DOWN:
                moveToUpper();
                break;
        }
    }

    /**
     * Gera um movimento aleatorio.
     */
    public void randomMovement() {

        int randomMovement = random.nextInt(4);

        switch (randomMovement) {
            case DIRECTION_LEFT:
                moveToLeft();
                break;
            case DIRECTION_RIGHT:
                moveToRight();
                break;
            case DIRECTION_UPPER:
                moveToUpper();
                break;
            case DIRECTION_DOWN:
                moveToDown();
                break;
        }
    }

    private void makeMovement() {

        posY += yVelocity;
        posX += xVelocity;
        boxCollision.setValues(posX, posY);
    }

    private void moveToLeft() {
        yVelocity = 0;
        xVelocity = -speed;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_LEFT;
    }

    private void moveToRight() {
        yVelocity = 0;
        xVelocity = speed;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_RIGHT;
    }

    private void moveToUpper() {
        yVelocity = -speed;
        xVelocity = 0;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_UPPER;
    }

    private void moveToDown() {
        yVelocity = speed;
        xVelocity = 0;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_DOWN;
    }

    public synchronized int getActualDirection() {
        return actualDirection;
    }

    public synchronized int collidedDirection() {
        return collisionDirection;
    }
}
