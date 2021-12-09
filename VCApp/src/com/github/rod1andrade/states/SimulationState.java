package com.github.rod1andrade.states;

import com.github.rod1andrade.objects.*;
import com.github.rod1andrade.objects.Window;
import com.github.rod1andrade.render.RenderEngine;
import com.github.rod1andrade.ui.Hud;
import com.github.rod1andrade.util.Config;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public final class SimulationState extends State {

    private Hud hud;
    private Entity[] entities = new Entity[6];

    private final RenderEngine renderEngine = new RenderEngine(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

    public SimulationState(String name, int actualState, Config config) {
        super(name, actualState, config);
    }

    @Override
    public void initializeResources() {
        hud = new Hud(0, Config.WINDOW_HEIGHT - 130, Config.WINDOW_WIDTH, 130, config);

        entities[0] = new Table(88, Config.WINDOW_HEIGHT / 2, 30, 22, config.isDebugMode());
        entities[1] = new VacuumCleaner(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2, 16, 16, config.isDebugMode());
        entities[2] = new Lamp(Config.WINDOW_WIDTH - 64, 128 - 80, 13, 27, config.isDebugMode());
        entities[3] = new Shelf((Config.WINDOW_WIDTH / 2) + 64, 128 - 80, 16, 27, config.isDebugMode());
        entities[4] = new MiniTable(32, (128 + 32), 16, 16, config.isDebugMode());
        entities[5] = new Television(32, 128, 16, 16, config.isDebugMode());

        // Renderizando as paredes
        for (int x = 0; x < Config.WINDOW_WIDTH; x += 64) {
            renderEngine.addEntityToRender(new StripedWall(x, 0, 16, 32));
        }

        // Renderizando o chao
        for (int y = 128; y < Config.WINDOW_HEIGHT - hud.getHeight(); y += 64) {
            for (int x = 0; x < Config.WINDOW_WIDTH; x += 64) {
                renderEngine.addEntityToRender(new Floor(x, y, 16, 16));
            }
        }

        // Objetos
        renderEngine.addEntityToRender(new Window(48, (64 - 16) / 2, 14, 14));

        renderEngine.addEntityToRender(new Mat(Config.WINDOW_WIDTH / 2, Config.WINDOW_HEIGHT / 2, 48, 32));
        renderEngine.addEntityToRender(new OilPaint((Config.WINDOW_WIDTH / 2) - 16, (64 - 16) / 2, 16, 16));

        renderEngine.addEntityToRender(entities[4]);
        renderEngine.addEntityToRender(entities[5]);

        renderEngine.addEntityToRender(entities[2]);
        renderEngine.addEntityToRender(entities[3]);

        renderEngine.addEntityToRender(new Plant(Config.WINDOW_WIDTH / 2 + 130, 128 - 60, 16, 20, config.isDebugMode()));

//        renderEngine.addEntityToRender(new LeftChair(88 - 36, Config.WINDOW_HEIGHT / 2, 14, 19));
//        renderEngine.addEntityToRender(new RightChair(88 + 105, Config.WINDOW_HEIGHT / 2, 14, 19));
//        renderEngine.addEntityToRender(new BackChair(88 + 30, Config.WINDOW_HEIGHT / 2 - 52, 14, 19));
        renderEngine.addEntityToRender(entities[0]);
//        renderEngine.addEntityToRender(new FrontChair(88 + 30, Config.WINDOW_HEIGHT / 2 + 66, 14, 19));

        renderEngine.addEntityToRender(entities[1]);

        hud.setHudVacuumCleanerInfo(((VacuumCleaner) entities[1]));
        ((VacuumCleaner) entities[1]).setEntities(entities);

        entities[1].initResources();
    }

    @Override
    public void update(float deltaTime) {
        if (actualState == State.RUNNING) {
            ((VacuumCleaner) entities[1]).collision();
            entities[1].update(deltaTime);
            hud.update(deltaTime);
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (actualState == State.RUNNING) {
            renderEngine.clear(graphics);
            renderEngine.render(graphics);
            hud.render(graphics);
        }
    }

    @Override
    public void dispose() {
        for (Entity entity : entities)
            entity.updateConfig(config);
    }
}
