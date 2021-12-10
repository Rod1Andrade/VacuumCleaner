package com.github.rod1andrade.states;

import com.github.rod1andrade.commands.*;
import com.github.rod1andrade.entities.*;
import com.github.rod1andrade.entities.Window;
import com.github.rod1andrade.model.VacuumCleanerModel;
import com.github.rod1andrade.render.Render;
import com.github.rod1andrade.ui.infos.Hud;
import com.github.rod1andrade.util.Config;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public final class SimulationState extends State {

    private long deltaCollisionTime = 0;
    private long countTimeToChangeDirection = System.currentTimeMillis();
    public static final int RANDOM_MOVEMENT_COOLDOWN = 2000;

    private Hud hud;

    private VacuumCleanerRenderEntity vacuumCleanerRenderEntity;
    private VacuumCleanerModel vacuumCleanerModel;
    private MakeMovementCommand makeMovementCommand;
    private RandomMovementCommand randomMovementCommand;
    private MoveToOppositeDirectionCommand moveToOppositeDirectionCommand;
    private BoundsCollisionCommand boundsCollisionCommand;
    private EntityRenderColisionCommand entityRenderColisionCommand;

    private RenderEntity[] renderEntities = new RenderEntity[6];

    private final Render render = new Render(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

    public SimulationState(String name, int actualState, Config config) {
        super(name, actualState, config);
        vacuumCleanerModel = new VacuumCleanerModel(config.getVacuumCleanerVelocity(), VacuumCleanerRenderEntity.DIRECTION_RIGHT, false);
        vacuumCleanerRenderEntity = new VacuumCleanerRenderEntity(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2, 16, 16, config.isDebugMode());
    }

    @Override
    public void initializeResources() {
        hud = new Hud(0, Config.WINDOW_HEIGHT - 130, Config.WINDOW_WIDTH, 130, config);
        makeMovementCommand = new MakeMovementCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        randomMovementCommand = new RandomMovementCommand(vacuumCleanerModel,vacuumCleanerRenderEntity);
        moveToOppositeDirectionCommand = new MoveToOppositeDirectionCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        boundsCollisionCommand = new BoundsCollisionCommand(vacuumCleanerModel, vacuumCleanerRenderEntity, 0, 130, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT - 130);

        renderEntities[0] = new Table(88, Config.WINDOW_HEIGHT / 2, 30, 22, config.isDebugMode());
        renderEntities[1] = new Lamp(Config.WINDOW_WIDTH - 64, 128 - 80, 13, 27, config.isDebugMode());
        renderEntities[2] = new Shelf((Config.WINDOW_WIDTH / 2) + 64, 128 - 80, 16, 27, config.isDebugMode());
        renderEntities[3] = new MiniTable(32, (128 + 32), 16, 16, config.isDebugMode());
        renderEntities[4] = new Television(32, 128, 16, 16, config.isDebugMode());
        renderEntities[5] = new Plant(Config.WINDOW_WIDTH / 2 + 130, 128 - 60, 16, 20, config.isDebugMode());

        entityRenderColisionCommand = new EntityRenderColisionCommand(vacuumCleanerModel, vacuumCleanerRenderEntity, renderEntities);

        // Renderizando as paredes
        for (int x = 0; x < Config.WINDOW_WIDTH; x += 64) {
            render.addEntityToRender(new StripedWall(x, 0, 16, 32));
        }

        // Renderizando o chao
        for (int y = 128; y < Config.WINDOW_HEIGHT - hud.getHeight(); y += 64) {
            for (int x = 0; x < Config.WINDOW_WIDTH; x += 64) {
                render.addEntityToRender(new Floor(x, y, 16, 16));
            }
        }

        // Objetos
        render.addEntityToRender(new Window(48, (64 - 16) / 2, 14, 14));

        render.addEntityToRender(new Mat(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2, 48, 32));
        render.addEntityToRender(new OilPaint((Config.WINDOW_WIDTH / 2) - 16, (64 - 16) / 2, 16, 16));
        render.addEntityToRender(renderEntities[5]);
        render.addEntityToRender(renderEntities[2]);
        render.addEntityToRender(renderEntities[3]);
        render.addEntityToRender(renderEntities[4]);
        render.addEntityToRender(renderEntities[1]);

//        renderEntity.addEntityToRender(new LeftChair(88 - 36, Config.WINDOW_HEIGHT / 2, 14, 19, config.isDebugMode()));
//        renderEntity.addEntityToRender(new RightChair(88 + 105, Config.WINDOW_HEIGHT / 2, 14, 19, config.isDebugMode()));
//        renderEntity.addEntityToRender(new BackChair(88 + 30, Config.WINDOW_HEIGHT / 2 - 52, 14, 19, config.isDebugMode()));
        render.addEntityToRender(renderEntities[0]);
//        renderEntity.addEntityToRender(new FrontChair(88 + 30, Config.WINDOW_HEIGHT / 2 + 66, 14, 19, config.isDebugMode()));

        render.addEntityToRender(vacuumCleanerRenderEntity);
//
//        ((VacuumCleanerRenderEntity) renderEntities[1]).setEntities(renderEntities);

        hud.setHudVacuumCleanerInfo(vacuumCleanerModel);
        vacuumCleanerRenderEntity.initResources();
    }


    @Override
    public void update(float deltaTime) {
        if (actualState == State.RUNNING) {
            vacuumCleanerModel.setHasCollision(false);

            entityRenderColisionCommand.execute();
            boundsCollisionCommand.execute();

            makeMovementCommand.execute();

            if(vacuumCleanerModel.hasCollision() && deltaCollisionTime > vacuumCleanerModel.getVelocity()) {
                moveToOppositeDirectionCommand.execute();
                deltaCollisionTime = 0;
            }

            if (!vacuumCleanerModel.hasCollision() && System.currentTimeMillis() - countTimeToChangeDirection > RANDOM_MOVEMENT_COOLDOWN) {
                randomMovementCommand.execute();
                countTimeToChangeDirection = System.currentTimeMillis();
            }

            hud.update(deltaTime);
            vacuumCleanerRenderEntity.update(deltaTime);
            deltaCollisionTime += deltaTime;
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (actualState == State.RUNNING) {
            render.clear(graphics);
            render.render(graphics);
            hud.render(graphics);
        }
    }

    @Override
    public void dispose() {
        for (RenderEntity renderEntity : renderEntities)
            renderEntity.updateConfig(config);
        vacuumCleanerRenderEntity.updateConfig(config);
    }
}
