package com.github.rod1andrade.states;

import com.github.rod1andrade.commands.simulation.StartRandomTrashsCommand;
import com.github.rod1andrade.commands.vacuumcleaner.*;
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

    // Controle de movimentacao do aspirador em decorrer do tempo.
    private long deltaCollisionTime = 0;
    private long countTimeToChangeDirection = System.currentTimeMillis();
    public static final int RANDOM_MOVEMENT_COOLDOWN = 2000;

    private Hud hud;

    private final VacuumCleanerRenderEntity vacuumCleanerRenderEntity;
    private final VacuumCleanerModel vacuumCleanerModel;

    private MakeMovementVacuumCleanerCommand makeMovementVacuumCleanerCommand;
    private RandomMovemenVacuumCleanertCommand randomMovemenVacuumCleanertCommand;
    private MoveToOppositeDirectionVacuumCleanerCommand moveToOppositeDirectionVacuumCleanerCommand;
    private BoundsCollisionVacuumCleanerCommand boundsCollisionVacuumCleanerCommand;
    private EntityRenderColisionVacuumCleanerCommand entityRenderColisionVacuumCleanerCommand;

    private StartRandomTrashsCommand startRandomTrashsCommand;

    private final RenderEntity[] collidablesRenderEntities = new RenderEntity[6];

    private final Render render = new Render(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

    public SimulationState(String name, int actualState, Config config) {
        super(name, actualState, config);
        vacuumCleanerModel = new VacuumCleanerModel(config.getVacuumCleanerVelocity(), VacuumCleanerRenderEntity.DIRECTION_RIGHT, false);
        vacuumCleanerRenderEntity = new VacuumCleanerRenderEntity(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2, 16, 16, config.isDebugMode());
    }

    @Override
    public void initializeResources() {
        hud = new Hud(0, Config.WINDOW_HEIGHT - 130, Config.WINDOW_WIDTH, 130, config);

        collidablesRenderEntities[0] = new Table(88, Config.WINDOW_HEIGHT / 2, 30, 22, config.isDebugMode());
        collidablesRenderEntities[1] = new Lamp(Config.WINDOW_WIDTH - 64, 128 - 80, 13, 27, config.isDebugMode());
        collidablesRenderEntities[2] = new Shelf((Config.WINDOW_WIDTH / 2) + 64, 128 - 80, 16, 27, config.isDebugMode());
        collidablesRenderEntities[3] = new MiniTable(32, (128 + 32), 16, 16, config.isDebugMode());
        collidablesRenderEntities[4] = new Television(32, 128, 16, 16, config.isDebugMode());
        collidablesRenderEntities[5] = new Plant(Config.WINDOW_WIDTH / 2 + 130, 128 - 60, 16, 20, config.isDebugMode());

        makeMovementVacuumCleanerCommand = new MakeMovementVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        randomMovemenVacuumCleanertCommand = new RandomMovemenVacuumCleanertCommand(vacuumCleanerModel,vacuumCleanerRenderEntity);
        moveToOppositeDirectionVacuumCleanerCommand = new MoveToOppositeDirectionVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        boundsCollisionVacuumCleanerCommand = new BoundsCollisionVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity, 0, 130, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT - 130);
        entityRenderColisionVacuumCleanerCommand = new EntityRenderColisionVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity, collidablesRenderEntities);

        startRandomTrashsCommand = new StartRandomTrashsCommand(
                collidablesRenderEntities,
                Config.QUANTITY_THRASH_LIMIT,
                0, 130,
                Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT - hud.getHeight()
        );

        // Define a posicao de renderizacao das paredes
        for (int x = 0; x < Config.WINDOW_WIDTH; x += 64) {
            render.addEntityToRender(new StripedWall(x, 0, 16, 32));
        }

        // Define a posicao de renderizcao do chao
        for (int y = 128; y < Config.WINDOW_HEIGHT - hud.getHeight(); y += 64) {
            for (int x = 0; x < Config.WINDOW_WIDTH; x += 64) {
                render.addEntityToRender(new Floor(x, y, 16, 16));
            }
        }

        // Objetos
        render.addEntityToRender(new Window(48, (64 - 16) / 2, 14, 14));

        render.addEntityToRender(new Mat(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2, 48, 32));
        render.addEntityToRender(new OilPaint((Config.WINDOW_WIDTH / 2) - 16, (64 - 16) / 2, 16, 16));
        render.addEntityToRender(collidablesRenderEntities[5]);
        render.addEntityToRender(collidablesRenderEntities[2]);
        render.addEntityToRender(collidablesRenderEntities[3]);
        render.addEntityToRender(collidablesRenderEntities[4]);
        render.addEntityToRender(collidablesRenderEntities[1]);

//        renderEntity.addEntityToRender(new LeftChair(88 - 36, Config.WINDOW_HEIGHT / 2, 14, 19, config.isDebugMode()));
//        renderEntity.addEntityToRender(new RightChair(88 + 105, Config.WINDOW_HEIGHT / 2, 14, 19, config.isDebugMode()));
//        renderEntity.addEntityToRender(new BackChair(88 + 30, Config.WINDOW_HEIGHT / 2 - 52, 14, 19, config.isDebugMode()));
        render.addEntityToRender(collidablesRenderEntities[0]);
//        renderEntity.addEntityToRender(new FrontChair(88 + 30, Config.WINDOW_HEIGHT / 2 + 66, 14, 19, config.isDebugMode()));

        render.addEntityToRender(new TrashRenderEntity(88 + 128, Config.WINDOW_HEIGHT / 2, 16, 16, config.isDebugMode()));

        startRandomTrashsCommand.execute();

        for(TrashRenderEntity trashRenderEntity : startRandomTrashsCommand.getTrashRenderEntities()) {
            render.addEntityToRender(trashRenderEntity);
        }

        render.addEntityToRender(vacuumCleanerRenderEntity);

        hud.setHudVacuumCleanerInfo(vacuumCleanerModel);
        vacuumCleanerRenderEntity.initResources();
    }


    @Override
    public void update(float deltaTime) {
        if (actualState == State.RUNNING) {
            hud.update(deltaTime);

            vacuumCleanerModel.setHasCollision(false);

            entityRenderColisionVacuumCleanerCommand.execute();
            boundsCollisionVacuumCleanerCommand.execute();

            makeMovementVacuumCleanerCommand.execute();

            if(vacuumCleanerModel.hasCollision() && deltaCollisionTime > vacuumCleanerModel.getVelocity()) {
                moveToOppositeDirectionVacuumCleanerCommand.execute();
                deltaCollisionTime = 0;
            }

            if (!vacuumCleanerModel.hasCollision() && System.currentTimeMillis() - countTimeToChangeDirection > RANDOM_MOVEMENT_COOLDOWN) {
                randomMovemenVacuumCleanertCommand.execute();
                countTimeToChangeDirection = System.currentTimeMillis();
            }

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
        for (RenderEntity renderEntity : collidablesRenderEntities)
            renderEntity.updateConfig(config);
        vacuumCleanerRenderEntity.updateConfig(config);
        vacuumCleanerModel.setVelocity(config.getVacuumCleanerVelocity());
    }
}
