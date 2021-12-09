package graphics.ui;

import graphics.objects.VacuumCleaner;

import java.awt.*;

public class Hud {

    private int x;
    private int y;
    private int width;
    private int height;

    private HudVacuumCleanerInfo hudVacuumCleanerInfo;

    public Hud(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        hudVacuumCleanerInfo = new HudVacuumCleanerInfo(x, y, 41, 32);
    }

    /**
     * Define a entidade na qual o HUD eh responsavel.
     *
     * @param vacuumCleaner VacuumCleaner.
     */
    public void setHudVacuumCleanerInfo(VacuumCleaner vacuumCleaner) {
        hudVacuumCleanerInfo.setVacuumCleaner(vacuumCleaner);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, width, height);
        hudVacuumCleanerInfo.render(graphics);
    }

    public void update(float deltaTime) {
        hudVacuumCleanerInfo.update(deltaTime);
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

    public HudVacuumCleanerInfo getHudVacuumCleanerInfo() {
        return hudVacuumCleanerInfo;
    }
}
