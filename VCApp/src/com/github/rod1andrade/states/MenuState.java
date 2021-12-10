package com.github.rod1andrade.states;

import com.github.rod1andrade.inputs.KeyInput;
import com.github.rod1andrade.ui.menu.MenuData;
import com.github.rod1andrade.ui.menu.MenuOption;
import com.github.rod1andrade.util.Config;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Rodrigo Andrade
 */
public class MenuState extends State {

    private final int space = (int) (Config.WINDOW_WIDTH * 0.10);
    private final int width = Config.WINDOW_WIDTH - space;
    private final int height = Config.WINDOW_HEIGHT - space;

    private final int xStart = space / 2;
    private final int yStart = space / 2;

    private long inputLagControllingTime = 0;

    // Menu fonts
    private Font defaultMenuFont;
    private Font headerMenuFont;
    private Font labelMenuFont;
    private Font optionMenuFont;

    private int menuLabelSelected = 0;
    private MenuData simulationMode = new MenuData("Mode", config.getSimulationMode(),
            new MenuOption("Infinity", 1, Config.SIMULATION_MODE_INFINITY),
            new MenuOption("Until Clean", 2, Config.SIMULATION_MODE_UNTIL_CLEAN),
            new MenuOption("Time", 3, Config.SIMULATION_MODE_TIME)
    );

    private MenuData time = new MenuData("Time", config.getTimeLimit(),
            new MenuOption("No Time", 1, Config.TIME_MODE_NO_TIME),
            new MenuOption("1 min", 2, Config.TIME_MODE_MILLIS_MINUTES_1),
            new MenuOption("2 min", 3, Config.TIME_MODE_MILLIS_MINUTES_2),
            new MenuOption("3 min", 4, Config.TIME_MODE_MILLIS_MINUTES_3),
            new MenuOption("4 min", 5, Config.TIME_MODE_MILLIS_MINUTES_4),
            new MenuOption("5 min", 6, Config.TIME_MODE_MILLIS_MINUTES_5)
    );

    private MenuData renderThrashType = new MenuData("Render Thrash Type", config.getAmountRenderThrashType(),
            new MenuOption("Infinity", 1, Config.AMOUNT_RENDER_THRASH_TYPE_INFINITY),
            new MenuOption("Random", 2, Config.AMOUNT_RENDER_THRASH_TYPE_RANDOM)
    );

    private MenuData vacuumCleanerVelocity = new MenuData("Vacuum Cleaner Velocity", config.getVacuumCleanerVelocity(),
            new MenuOption("2x", 1, Config.VACUUM_CLEANER_SPEED_2),
            new MenuOption("4x", 2, Config.VACUUM_CLEANER_SPEED_4),
            new MenuOption("6x", 3, Config.VACUUM_CLEANER_SPEED_6)
    );

    private MenuData debug = new MenuData("Debug", config.getMode(),
            new MenuOption("Yes", 1, Config.MODE_DEBUG),
            new MenuOption("No", 2, Config.MODE_NO_DEBUG)
    );

    private MenuData sound = new MenuData("Sound", config.getSound(),
            new MenuOption("On", 1, Config.SOUND_ON),
            new MenuOption("Off", 2, Config.SOUND_OFF)
    );

    private MenuData[] menuData = {simulationMode, time, renderThrashType, vacuumCleanerVelocity, debug, sound};

    private KeyInput keyInput;

    public MenuState(String name, int actualState, KeyInput keyInput, Config config) {
        super(name, actualState, config);
        this.keyInput = keyInput;
    }

    @Override
    public void initializeResources() {
        try {
            defaultMenuFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("assets/fonts/Pixeled.ttf"));
            headerMenuFont = defaultMenuFont.deriveFont(36f);
            labelMenuFont = defaultMenuFont.deriveFont(16f);
            optionMenuFont = defaultMenuFont.deriveFont(12f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(float deltaTime) {
        if (actualState == State.RUNNING) {
            if (inputLagControllingTime > 6) {
                if (keyInput.hasPressed(KeyEvent.VK_DOWN)) {
                    moveDown();
                }

                if (keyInput.hasPressed(KeyEvent.VK_UP)) {
                    moveUp();
                }

                if (keyInput.hasPressed(KeyEvent.VK_RIGHT)) {
                    moveForward();
                }

                if (keyInput.hasPressed(KeyEvent.VK_LEFT)) {
                    moveBackward();
                }

                inputLagControllingTime = 0;
            }
            inputLagControllingTime += deltaTime;
        }
    }

    public void moveForward() {
        menuData[menuLabelSelected].nextOption();
    }

    public void moveBackward() {
        menuData[menuLabelSelected].prevOption();
    }

    public void moveUp() {
        if (menuLabelSelected > 0)
            menuLabelSelected--;
    }

    public void moveDown() {
        if (menuLabelSelected < menuData.length - 1)
            menuLabelSelected++;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(xStart, yStart, width, height);

        graphics.setFont(headerMenuFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Settings", xStart + (headerMenuFont.getSize() + 10), yStart + (headerMenuFont.getSize() * 2));

        for (int i = 0; i < menuData.length; i++) {
            if (i == menuLabelSelected)
                graphics.setColor(Color.RED);
            else
                graphics.setColor(Color.WHITE);

            graphics.setFont(labelMenuFont);
            graphics.drawString(menuData[i].getLabel(), xStart + (labelMenuFont.getSize() + 10), (headerMenuFont.getSize() * 3 + 40) + (yStart + (labelMenuFont.getSize() + 80) * i));

            graphics.setFont(optionMenuFont);
            graphics.drawString(menuData[i].getSelectedMenuOption().getLabel(), width - (optionMenuFont.getSize() + 100), (headerMenuFont.getSize() * 3 + 40) + (yStart + (optionMenuFont.getSize() + 80) * i));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        config.setSimulationMode(simulationMode.getSelectedMenuOption().getOptionCode());
        config.setTimeLimit(time.getSelectedMenuOption().getOptionCode());
        config.setAmountRenderThrashType(renderThrashType.getSelectedMenuOption().getOptionCode());
        config.setVacuumCleanerVelocity(vacuumCleanerVelocity.getSelectedMenuOption().getOptionCode());
        config.setMode(debug.getSelectedMenuOption().getOptionCode());
        config.setSound(sound.getSelectedMenuOption().getOptionCode());
    }
}
