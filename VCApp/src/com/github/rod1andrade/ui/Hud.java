package com.github.rod1andrade.ui;

import com.github.rod1andrade.objects.VacuumCleaner;
import com.github.rod1andrade.ui.sensors.*;

import java.awt.*;

public class Hud {

    private int x;
    private int y;
    private int width;
    private int height;

    private DirectionBoxUI DirectionBoxUI;
    private SensorUI[] sensors = new SensorUI[4];

    public Hud(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        DirectionBoxUI = new DirectionBoxUI(x, y, 41, 32);
    }

    /**
     * Define a entidade na qual o HUD eh responsavel.
     *
     * @param vacuumCleaner VacuumCleaner.
     */
    public void setHudVacuumCleanerInfo(VacuumCleaner vacuumCleaner) {
        DirectionBoxUI.setVacuumCleaner(vacuumCleaner);
        sensors[0] = new NorthSensorUI(width - 128, (y + (height - 64) / 2), 16, 16, "North", vacuumCleaner);
        sensors[1] = new SouthSensorUI(width - (128 + 64), (y + (height - 64) / 2), 16, 16, "South", vacuumCleaner);
        sensors[2] = new WestSensorUI(width - (128 + 64 + 64), (y + (height - 64) / 2), 16, 16, "West", vacuumCleaner);
        sensors[3] = new EastSensorUI(width - (128 + 64 + 64 + 64), (y + (height - 64) / 2), 16, 16, "East", vacuumCleaner);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, width, height);
        DirectionBoxUI.render(graphics);
        for (int i = 0; i < sensors.length; i++) sensors[i].render(graphics);
    }

    public void update(float deltaTime) {
        DirectionBoxUI.update(deltaTime);
        for (int i = 0; i < sensors.length; i++) {
            sensors[i].update(deltaTime);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public DirectionBoxUI getHudVacuumCleanerInfo() {
        return DirectionBoxUI;
    }
}
