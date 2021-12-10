package com.github.rod1andrade.states;

import com.github.rod1andrade.commands.simulation.StartRandomTrashsCommand;
import com.github.rod1andrade.commands.vacuumcleaner.*;
import com.github.rod1andrade.entities.*;
import com.github.rod1andrade.entities.Window;
import com.github.rod1andrade.models.VacuumCleanerModel;
import com.github.rod1andrade.render.Render;
import com.github.rod1andrade.ui.infos.Hud;
import com.github.rod1andrade.util.GlobalConfig;

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

    // Commands do aspirador de po
    private MakeMovementVacuumCleanerCommand makeMovementVacuumCleanerCommand;
    private RandomMovemenVacuumCleanertCommand randomMovemenVacuumCleanertCommand;
    private MoveToOppositeDirectionVacuumCleanerCommand moveToOppositeDirectionVacuumCleanerCommand;
    private BoundsCollisionVacuumCleanerCommand boundsCollisionVacuumCleanerCommand;
    private EntityRenderColisionVacuumCleanerCommand entityRenderColisionVacuumCleanerCommand;
    private CollectTrashVacuumCleanerCommand collectTrashVacuumCleanerCommand;

    // Commands da sujeiras
    private StartRandomTrashsCommand startRandomTrashsCommand;

    private final RenderEntity[] collidablesRenderEntities = new RenderEntity[6];

    private final Render render = new Render(GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT);

    public SimulationState(String name, int actualState, GlobalConfig globalConfig, VacuumCleanerModel vacuumCleanerModel) {
        super(name, actualState, globalConfig);
        this.vacuumCleanerModel = vacuumCleanerModel;
        vacuumCleanerRenderEntity = new VacuumCleanerRenderEntity(GlobalConfig.WINDOW_WIDTH / 2, GlobalConfig.WINDOW_HEIGHT / 2, 16, 16, globalConfig.isDebugMode());
    }

    @Override
    public void initializeResources() {
        hud = new Hud(0, GlobalConfig.WINDOW_HEIGHT - 130, GlobalConfig.WINDOW_WIDTH, 130, globalConfig);

        collidablesRenderEntities[0] = new Table(88, GlobalConfig.WINDOW_HEIGHT / 2, 30, 22, globalConfig.isDebugMode());
        collidablesRenderEntities[1] = new Lamp(GlobalConfig.WINDOW_WIDTH - 64, 128 - 80, 13, 27, globalConfig.isDebugMode());
        collidablesRenderEntities[2] = new Shelf((GlobalConfig.WINDOW_WIDTH / 2) + 64, 128 - 80, 16, 27, globalConfig.isDebugMode());
        collidablesRenderEntities[3] = new MiniTable(32, (128 + 32), 16, 16, globalConfig.isDebugMode());
        collidablesRenderEntities[4] = new Television(32, 128, 16, 16, globalConfig.isDebugMode());
        collidablesRenderEntities[5] = new Plant(GlobalConfig.WINDOW_WIDTH / 2 + 130, 128 - 60, 16, 20, globalConfig.isDebugMode());

        makeMovementVacuumCleanerCommand = new MakeMovementVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        randomMovemenVacuumCleanertCommand = new RandomMovemenVacuumCleanertCommand(vacuumCleanerModel,vacuumCleanerRenderEntity);
        moveToOppositeDirectionVacuumCleanerCommand = new MoveToOppositeDirectionVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        boundsCollisionVacuumCleanerCommand = new BoundsCollisionVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity, 0, 130, GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT - 130);
        entityRenderColisionVacuumCleanerCommand = new EntityRenderColisionVacuumCleanerCommand(vacuumCleanerModel, vacuumCleanerRenderEntity, collidablesRenderEntities);

        startRandomTrashsCommand = new StartRandomTrashsCommand(
                collidablesRenderEntities,
                GlobalConfig.QUANTITY_THRASH_LIMIT,
                0, 130,
                GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT - hud.getHeight()
        );

        collectTrashVacuumCleanerCommand = new CollectTrashVacuumCleanerCommand(
                vacuumCleanerModel, vacuumCleanerRenderEntity,
                startRandomTrashsCommand.getTrashModels(), startRandomTrashsCommand.getTrashRenderEntities()
        );

        // Define a posicao de renderizacao das paredes
        for (int x = 0; x < GlobalConfig.WINDOW_WIDTH; x += 64) {
            render.addEntityToRender(new StripedWall(x, 0, 16, 32));
        }

        // Define a posicao de renderizcao do chao
        for (int y = 128; y < GlobalConfig.WINDOW_HEIGHT - hud.getHeight(); y += 64) {
            for (int x = 0; x < GlobalConfig.WINDOW_WIDTH; x += 64) {
                render.addEntityToRender(new Floor(x, y, 16, 16));
            }
        }

        // Objetos
        render.addEntityToRender(new Window(48, (64 - 16) / 2, 14, 14));

        render.addEntityToRender(new Mat(GlobalConfig.WINDOW_WIDTH / 2, GlobalConfig.WINDOW_HEIGHT / 2, 48, 32));
        render.addEntityToRender(new OilPaint((GlobalConfig.WINDOW_WIDTH / 2) - 16, (64 - 16) / 2, 16, 16));
        render.addEntityToRender(collidablesRenderEntities[5]);
        render.addEntityToRender(collidablesRenderEntities[2]);
        render.addEntityToRender(collidablesRenderEntities[3]);
        render.addEntityToRender(collidablesRenderEntities[4]);
        render.addEntityToRender(collidablesRenderEntities[1]);
        render.addEntityToRender(collidablesRenderEntities[0]);

        // Adiciona cada Trash para a render pipeline...
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
            collectTrashVacuumCleanerCommand.execute();

            makeMovementVacuumCleanerCommand.execute();

            if(vacuumCleanerModel.hasCollision() && deltaCollisionTime > vacuumCleanerModel.getVelocity()) {
                moveToOppositeDirectionVacuumCleanerCommand.execute();
                deltaCollisionTime = 0;
            }

            if (System.currentTimeMillis() - countTimeToChangeDirection > RANDOM_MOVEMENT_COOLDOWN) {
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
            renderEntity.updateConfig(globalConfig);
        vacuumCleanerRenderEntity.updateConfig(globalConfig);
        vacuumCleanerModel.setVelocity(globalConfig.getVacuumCleanerVelocity());

        for(TrashRenderEntity trashRenderEntity : startRandomTrashsCommand.getTrashRenderEntities())
            trashRenderEntity.updateConfig(globalConfig);
    }
}
