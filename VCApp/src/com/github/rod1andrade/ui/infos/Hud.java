package com.github.rod1andrade.ui.infos;

import com.github.rod1andrade.models.VacuumCleanerModel;
import com.github.rod1andrade.states.MainManagerState;
import com.github.rod1andrade.ui.sensors.*;
import com.github.rod1andrade.util.GlobalConfig;
import com.github.rod1andrade.util.Timer;

import java.awt.*;

public class Hud {

    private final int posX;
    private final int posY;
    private final int width;
    private final int height;

    private final DirectionBoxUI directionBoxUI;
    private final SensorUI[] sensors = new SensorUI[4];

    private final GlobalConfig globalConfig;

    public Hud(int posX, int posY, int width, int height, GlobalConfig globalConfig) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.globalConfig = globalConfig;

        directionBoxUI = new DirectionBoxUI(posX, posY, 41, 32);
    }

    /**
     * Define a entidade na qual o HUD eh responsavel.
     *
     * @param vacuumCleanerModel VacuumCleanerModel.
     */
    public void setHudVacuumCleanerInfo(VacuumCleanerModel vacuumCleanerModel) {
        directionBoxUI.setVacuumCleaner(vacuumCleanerModel);
        sensors[0] = new NorthSensorUI(width - 128, (posY + (height - 64) / 2), 16, 16, "North", vacuumCleanerModel, globalConfig.isDebugMode());
        sensors[1] = new SouthSensorUI(width - (128 + 64), (posY + (height - 64) / 2), 16, 16, "South", vacuumCleanerModel, globalConfig.isDebugMode());
        sensors[2] = new WestSensorUI(width - (128 + 64 + 64), (posY + (height - 64) / 2), 16, 16, "West", vacuumCleanerModel, globalConfig.isDebugMode());
        sensors[3] = new EastSensorUI(width - (128 + 64 + 64 + 64), (posY + (height - 64) / 2), 16, 16, "East", vacuumCleanerModel, globalConfig.isDebugMode());
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(posX, posY, width, height);
        directionBoxUI.render(graphics);
        for (int i = 0; i < sensors.length; i++) sensors[i].render(graphics);

        if(GlobalConfig.getInstance().isDebugMode()) {
            graphics.drawString(
                    "FPS/UPS: " + MainManagerState.fps + "/" + MainManagerState.ups,
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
