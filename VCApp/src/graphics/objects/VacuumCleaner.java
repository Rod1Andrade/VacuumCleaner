package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.render.WindowRender;
import graphics.util.Loader;

/**
 * @author Rodrigo Andrade
 */
public class VacuumCleaner extends Entity {

    private float xVelocity = -1.23f;

    private Entity[] entities;
    private static final Sprite vacuumCleanerSprite = new Sprite(80, 32, 16, 16, Loader.spriteSheet001);

    public VacuumCleaner(int posX, int posY, int width, int height) {
        super(posX, posY, width, height, vacuumCleanerSprite, new BoxCollision(posX, posY, width, height));
    }

    /**
     * Recomendacao: Este metodo deve ser chamado antes da chamada do metodo update.
     *
     * @param entities Entity[]
     */
    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    @Override
    public void update(float deltaTime) {

        synchronized (this) {
            for(int i = 0; i < entities.length; i++) {
                if(boxCollision.hasCollision(entities[i].boxCollision)) {
                    xVelocity = xVelocity * -1;
                }
            }
        }

        if (posX >= WindowRender.WIDTH - width || posX <= 0) {
            xVelocity = xVelocity * -1;
        }

        posX += xVelocity;
        boxCollision.setValues(posX, posY);
    }
}
