package graphics.objects;

import graphics.assets.Sprite;
import graphics.physics.BoxCollision;
import graphics.render.WindowRender;
import graphics.util.Loader;

import java.util.List;

/**
 * @author Rodrigo Andrade
 */
public class VacuumCleaner extends Entity {

    private float xVelocity = 1.23f;
    private static final Sprite vacuumCleanerSprite = new Sprite(80, 32, 16, 16, Loader.spriteSheet001);
    private final List<Entity> entities;

    public VacuumCleaner(int posX, int posY, int width, int height, List<Entity> entities) {
        super(posX, posY, width, height, vacuumCleanerSprite, new BoxCollision(posX, posY, width, height));
        this.entities = entities;
        System.out.println(entities.size());
    }

    @Override
    public void update(float deltaTime) {
        boxCollision.setValues(posX, posY);

        for (Entity entity: entities) {
            if(this.boxCollision.hasCollision(entity.boxCollision))
                xVelocity = xVelocity * -1;
        }
        if(posX == WindowRender.WIDTH - width || posX == 0)
            xVelocity = xVelocity * -1;

        posX  += xVelocity;

    }
}
