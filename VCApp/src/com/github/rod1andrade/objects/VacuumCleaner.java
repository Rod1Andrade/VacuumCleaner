package com.github.rod1andrade.objects;

import com.github.rod1andrade.audio.AudioPlayer;
import com.github.rod1andrade.enums.Mode;
import com.github.rod1andrade.physics.BoxCollision;
import com.github.rod1andrade.render.WindowRender;
import com.github.rod1andrade.util.Loader;
import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.util.GlobalInfo;

import java.awt.*;
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
    private final int speed = 2;
    private int xVelocity = speed;
    private int yVelocity = 0;
    private int previousDirection = DIRECTION_LEFT;
    private int actualDirection = DIRECTION_RIGHT;

    // Controle de colisao
    private boolean hasCollision = false;
    private int collisionDirection = -1;

    // Variaveis de controle da atualizacao de movimentos com base no deltaTime.
    private int controllingDeltaTimeToCollision = 0;
    private long time = System.currentTimeMillis();
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

    public VacuumCleaner(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;

        this.sprite = sprites[actualDirection];

        setBoxCollision(new BoxCollision(posX, posY, width, height));
    }

    @Override
    public void initResources() {
        super.initResources();
        movimentfx = new AudioPlayer("assets/audios/mixkit-small-hit-in-a-game-2072.wav");
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

        if (posX + width >= WindowRender.WIDTH) {
            hasCollision = true;
        }
        if (posY + height >= WindowRender.HEIGHT - 130) {
            hasCollision = true;
        }
        if (posX <= 0) {
            hasCollision = true;
        }
        if (posY <= 130) {
            hasCollision = true;
        }

        if(hasCollision) collisionDirection = actualDirection;
    }


    @Override
    public void update(float deltaTime) {

        makeMovement();

        if(hasCollision)
            movimentfx.play();


        if (hasChangedDirection()) {
            changeSpriteDirection();
        }

        if (hasCollision && controllingDeltaTimeToCollision > speed) {
            moveToOppositeDirection(actualDirection);
            controllingDeltaTimeToCollision = 0;
        }

        if (!hasCollision && System.currentTimeMillis() - time > RANDOM_MOVEMENT_COOLDOWN) {
            randomMovement();
            time = System.currentTimeMillis();
        }

        controllingDeltaTimeToCollision += deltaTime;
    }

    private boolean hasChangedDirection() {
        return previousDirection != actualDirection;
    }

    private void changeSpriteDirection() {
        this.sprite = this.sprites[actualDirection];
    }

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

    @Override
    public void render(Graphics graphics) {

        if (GlobalInfo.mode == Mode.DEBUG) {
            String prevDirection = "", actDirection = "";
            switch (previousDirection) {
                case DIRECTION_LEFT:
                    prevDirection = "Left";
                    break;
                case DIRECTION_RIGHT:
                    prevDirection = "Right";
                    break;
                case DIRECTION_UPPER:
                    prevDirection = "Upper";
                    break;
                case DIRECTION_DOWN:
                    prevDirection = "Down";
                    break;
            }

            switch (actualDirection) {
                case DIRECTION_LEFT:
                    actDirection = "Left";
                    break;
                case DIRECTION_RIGHT:
                    actDirection = "Right";
                    break;
                case DIRECTION_UPPER:
                    actDirection = "Upper";
                    break;
                case DIRECTION_DOWN:
                    actDirection = "Down";
                    break;
            }

            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Dialog", Font.PLAIN, 25));
            graphics.drawString("Previous Direction: " + prevDirection, 10, 25);
            graphics.drawString("Actual Direction: " + actDirection, 10, 55);

            graphics.setFont(new Font("Dialog", Font.PLAIN, 12));
            String position = "(" + boxCollision.getX() + ", " + boxCollision.getY() + ")";

            graphics.setColor(Color.WHITE);
            graphics.drawString(position, boxCollision.getX(), boxCollision.getY());

            String size = "(" + boxCollision.getWidth() + ", " + boxCollision.getHeight() + ")";
            graphics.setColor(Color.WHITE);
            graphics.drawString(size, boxCollision.getX() + boxCollision.getWidth(),
                    boxCollision.getY() + boxCollision.getHeight());

            if (hasCollision) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.GREEN);
            }

            graphics.drawRect(boxCollision.getX(), boxCollision.getY(), boxCollision.getWidth(),
                    boxCollision.getHeight());
        }
        graphics.drawImage(sprite.getSprite(SCALE_FACTOR), posX, posY, null);
    }

    public synchronized int getActualDirection() {
        return actualDirection;
    }

    public synchronized int collidedDirection() {
        return collisionDirection;
    }
}
