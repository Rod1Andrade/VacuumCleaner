package graphics.objects;

import graphics.assets.Sprite;
import graphics.enums.Mode;
import graphics.physics.BoxCollision;
import graphics.render.WindowRender;
import graphics.util.Info;
import graphics.util.Loader;

import java.awt.*;
import java.util.Random;

/**
 * @author Rodrigo Andrade
 */
public class VacuumCleaner extends Entity {

    protected static final int DIRECTION_LEFT = 0;
    protected static final int DIRECTION_RIGHT = 1;
    protected static final int DIRECTION_UPPER = 2;
    protected static final int DIRECTION_DOWN = 3;

    private int xVelocity = 2;
    private int yVelocity = 0;
    private final int velocity = 2;
    private int previousDirection = DIRECTION_LEFT;
    private int actualDirection = DIRECTION_RIGHT;

    private boolean hasCollision = false;
    private int collisionDirection = previousDirection;

    private Entity[] entities;
    private final Sprite[] sprites = {
            new Sprite(80, 48, 16, 16, Loader.spriteSheet001),
            new Sprite(80, 32, 16, 16, Loader.spriteSheet001),
            new Sprite(96, 48, 16, 16, Loader.spriteSheet001),
            new Sprite(96, 32, 16, 16, Loader.spriteSheet001),
    };

    private Random random = new Random();

    public VacuumCleaner(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;

        this.sprite = sprites[actualDirection];

        setBoxCollision(new BoxCollision(posX, posY, width, height));
    }

    /**
     * Recomendacao: Este metodo deve ser chamado antes da chamada do metodo update.
     *
     * @param entities Entity[]
     */
    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    public void collision() {
        hasCollision = false;
        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null && !(entities[i] instanceof VacuumCleaner) && this.boxCollision.hasCollision(entities[i].boxCollision)) {
                hasCollision = true;
            }
        }

        if (posX + width >= WindowRender.WIDTH) hasCollision = true;
        if(posY + height >= WindowRender.HEIGHT) hasCollision = true;
        if(posX <= 0) hasCollision = true;
        if(posY <= 130) hasCollision = true;
    }

    int time = 0;
    @Override
    public void update(float deltaTime) {
        makeMovement();

        if (hasChangedDirection())
            changeSpriteDirection();

        if(hasCollision) {
            moveToOppositeDirection(actualDirection);
        }
    }

    private boolean hasChangedDirection() {
        return previousDirection != actualDirection;
    }

    private void changeSpriteDirection() {
        this.sprite = this.sprites[actualDirection];
    }

    private void moveToOppositeDirection (int actualDirection) {
       synchronized (this) {
           switch (actualDirection) {
               case DIRECTION_LEFT: moveToRight(); break;
               case DIRECTION_RIGHT: moveToLeft(); break;
               case DIRECTION_UPPER: moveToDown(); break;
               case DIRECTION_DOWN: moveToUpper(); break;
           }
       }
    }

    public void randomMovement() {
        synchronized (this) {
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
    }

    private void makeMovement() {
        posY += yVelocity;
        posX += xVelocity;
        boxCollision.setValues(posX, posY);
    }

    private void moveToLeft() {
        yVelocity = 0;
        xVelocity = -velocity;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_LEFT;
    }

    private void moveToRight() {
        yVelocity = 0;
        xVelocity = velocity;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_RIGHT;
    }

    private void moveToUpper() {
        yVelocity = -velocity;
        xVelocity = 0;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_UPPER;
    }

    private void moveToDown() {
        yVelocity = velocity;
        xVelocity = 0;
        previousDirection = actualDirection;
        actualDirection = DIRECTION_DOWN;
    }

    @Override
    public void render(Graphics graphics) {

        if (Info.mode == Mode.DEBUG) {
            String prevDirection = "", actDirection = "";
            switch (previousDirection) {
                case DIRECTION_LEFT: prevDirection = "Left";break;
                case DIRECTION_RIGHT: prevDirection = "Right";break;
                case DIRECTION_UPPER: prevDirection = "Upper";break;
                case DIRECTION_DOWN: prevDirection = "Down";break;
            }

            switch (actualDirection){
                case DIRECTION_LEFT: actDirection = "Left";break;
                case DIRECTION_RIGHT: actDirection = "Right";break;
                case DIRECTION_UPPER: actDirection = "Upper";break;
                case DIRECTION_DOWN: actDirection = "Down";break;
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
            graphics.drawString(size, boxCollision.getX() + boxCollision.getWidth(), boxCollision.getY() + boxCollision.getHeight());

            if (hasCollision) {
                graphics.setColor(Color.RED);
            } else {
                graphics.setColor(Color.GREEN);
            }

            graphics.drawRect(boxCollision.getX(), boxCollision.getY(), boxCollision.getWidth(), boxCollision.getHeight());
        }
        graphics.drawImage(sprite.getSprite(SCALE_FACTOR), posX, posY, null);
    }
}
