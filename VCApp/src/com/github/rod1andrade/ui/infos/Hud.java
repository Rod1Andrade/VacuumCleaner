package com.github.rod1andrade.ui.infos;

import com.github.rod1andrade.model.VacuumCleanerModel;
import com.github.rod1andrade.render.ManagerRender;
import com.github.rod1andrade.ui.sensors.*;
import com.github.rod1andrade.util.Config;
import com.github.rod1andrade.util.Timer;

import java.awt.*;

public class Hud {

    private final int posX;
    private final int posY;
    private final int width;
    private final int height;

    private final DirectionBoxUI directionBoxUI;
    private final SensorUI[] sensors = new SensorUI[4];

    private final Config config;

    public Hud(int posX, int posY, int width, int height, Config config) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.config = config;

        directionBoxUI = new DirectionBoxUI(posX, posY, 41, 32);
    }

    /**
     * Define a entidade na qual o HUD eh responsavel.
     *
     * @param vacuumCleanerModel VacuumCleanerModel.
     */
    public void setHudVacuumCleanerInfo(VacuumCleanerModel vacuumCleanerModel) {
        directionBoxUI.setVacuumCleaner(vacuumCleanerModel);
        sensors[0] = new NorthSensorUI(width - 128, (posY + (height - 64) / 2), 16, 16, "North", vacuumCleanerModel, config.isDebugMode());
        sensors[1] = new SouthSensorUI(width - (128 + 64), (posY + (height - 64) / 2), 16, 16, "South", vacuumCleanerModel, config.isDebugMode());
        sensors[2] = new WestSensorUI(width - (128 + 64 + 64), (posY + (height - 64) / 2), 16, 16, "West", vacuumCleanerModel, config.isDebugMode());
        sensors[3] = new EastSensorUI(width - (128 + 64 + 64 + 64), (posY + (height - 64) / 2), 16, 16, "East", vacuumCleanerModel, config.isDebugMode());
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(posX, posY, width, height);
        directionBoxUI.render(graphics);
        for (int i = 0; i < sensors.length; i++) sensors[i].render(graphics);

        if(Config.getInstance().isDebugMode()) {
            graphics.drawString(
                    "FPS/UPS: " + ManagerRender.fps + "/" + ManagerRender.ups,
                    directionBoxUI.getWidth(), directionBoxUI.getPosY() + 50
            );
        }

        graphics.drawString(
                Timer.getInstance().elapseTimeFomartToMinutes(),
                directionBoxUI.getWidth() + 10, directionBoxUI.getPosY() + 80
        );
    }

    public void update(float deltaTime) {
        directionBoxUI.update(deltaTime);
        for (int i = 0; i < sensors.length; i++) {
            sensors[i].update(deltaTime);
        }
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DirectionBoxUI getHudVacuumCleanerInfo() {
        return directionBoxUI;
    }
}
