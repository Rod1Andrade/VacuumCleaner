package com.github.rod1andrade.states;

import com.github.rod1andrade.models.VacuumCleanerModel;
import com.github.rod1andrade.util.GlobalConfig;
import com.github.rod1andrade.util.Timer;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Rodrigo Andrade
 */
public class ResumeSimulationState extends State {

    private final int space = (int) (GlobalConfig.WINDOW_WIDTH * 0.10);
    private final int width = GlobalConfig.WINDOW_WIDTH - space;
    private final int height = GlobalConfig.WINDOW_HEIGHT - space;

    private final int xStart = space / 2;
    private final int yStart = space / 2;

    // Menu fonts
    private Font defaultMenuFont;
    private Font headerMenuFont;
    private Font labelFont;
    private Font infoFont;

    private VacuumCleanerModel vacuumCleanerModel;

    public ResumeSimulationState(String name, int actualState, GlobalConfig globalConfig, VacuumCleanerModel vacuumCleanerModel) {
        super(name, actualState, globalConfig);
        this.vacuumCleanerModel = vacuumCleanerModel;
    }

    @Override
    public void initializeResources() {
        try {
            defaultMenuFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Pixeled.ttf"));
            headerMenuFont = defaultMenuFont.deriveFont(36f);
            labelFont = defaultMenuFont.deriveFont(16f);
            infoFont = defaultMenuFont.deriveFont(12f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float deltaTime) {
        if(actualState == State.RUNNING)
            Timer.stop();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(xStart, yStart, width, height);

        graphics.setFont(headerMenuFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString(name, xStart + (headerMenuFont.getSize() + 10), yStart + (headerMenuFont.getSize() * 2));

        graphics.setFont(labelFont);
        graphics.drawString("Simulation Time", xStart + (labelFont.getSize() + 10), (headerMenuFont.getSize() * 3 + 40) + (yStart + (labelFont.getSize() + 80) * 0));

        graphics.setFont(infoFont);
        graphics.drawString(Timer.getInstance().stopTimeFomartToMinutes(), width - (infoFont.getSize() + 100), (headerMenuFont.getSize() * 3 + 40) + (yStart + (infoFont.getSize() + 80) * 0));

        graphics.setFont(labelFont);
        graphics.drawString("Collected Trashs", xStart + (labelFont.getSize() + 10), (headerMenuFont.getSize() * 3 + 40) + (yStart + (labelFont.getSize() + 80) * 1));

        graphics.setFont(infoFont);
        graphics.drawString("" + vacuumCleanerModel.amountCollectedTrashs(), width - (infoFont.getSize() + 100), (headerMenuFont.getSize() * 3 + 40) + (yStart + (infoFont.getSize() + 80) * 1));

        graphics.setFont(labelFont);
        graphics.drawString("Collisions Detecteds", xStart + (labelFont.getSize() + 10), (headerMenuFont.getSize() * 3 + 40) + (yStart + (labelFont.getSize() + 80) * 2));

        graphics.setFont(infoFont);
        graphics.drawString("" + vacuumCleanerModel.getAmountCollisions(), width - (infoFont.getSize() + 100), (headerMenuFont.getSize() * 3 + 40) + (yStart + (infoFont.getSize() + 80) * 2));
    }

    @Override
    public void dispose() {
        super.dispose();
        Timer.resume();
    }
}
