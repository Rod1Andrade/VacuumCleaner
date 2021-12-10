package com.github.rod1andrade.states;

import com.github.rod1andrade.inputs.KeyInput;
import com.github.rod1andrade.ui.menu.SingleMenuData;
import com.github.rod1andrade.ui.menu.MenuOption;
import com.github.rod1andrade.util.GlobalConfig;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Rodrigo Andrade
 */
public class MenuState extends State {

    private final int space = (int) (GlobalConfig.WINDOW_WIDTH * 0.10);
    private final int width = GlobalConfig.WINDOW_WIDTH - space;
    private final int height = GlobalConfig.WINDOW_HEIGHT - space;

    private final int xStart = space / 2;
    private final int yStart = space / 2;

    private long inputLagControllingTime = 0;

    // Menu fonts
    private Font defaultMenuFont;
    private Font headerMenuFont;
    private Font labelMenuFont;
    private Font optionMenuFont;

    private int menuLabelSelected = 0;
    private SingleMenuData simulationMode = new SingleMenuData("Mode", globalConfig.getSimulationMode(),
            new MenuOption("Until Clean", 2, GlobalConfig.SIMULATION_MODE_UNTIL_CLEAN),
            new MenuOption("Time", 3, GlobalConfig.SIMULATION_MODE_TIME)
    );

    private SingleMenuData time = new SingleMenuData("Time", globalConfig.getTimeLimit(),
            new MenuOption("No Time", 1, GlobalConfig.TIME_MODE_NO_TIME),
            new MenuOption("1 min", 2, GlobalConfig.TIME_MODE_MILLIS_MINUTES_1),
            new MenuOption("2 min", 3, GlobalConfig.TIME_MODE_MILLIS_MINUTES_2),
            new MenuOption("3 min", 4, GlobalConfig.TIME_MODE_MILLIS_MINUTES_3),
            new MenuOption("4 min", 5, GlobalConfig.TIME_MODE_MILLIS_MINUTES_4),
            new MenuOption("5 min", 6, GlobalConfig.TIME_MODE_MILLIS_MINUTES_5)
    );

    private SingleMenuData renderThrashType = new SingleMenuData("Render Thrash Type", globalConfig.getAmountRenderThrashType(),
            new MenuOption("Random", 2, GlobalConfig.AMOUNT_RENDER_THRASH_TYPE_RANDOM)
    );

    private SingleMenuData vacuumCleanerVelocity = new SingleMenuData("Vacuum Cleaner Velocity", globalConfig.getVacuumCleanerVelocity(),
            new MenuOption("2x", 1, GlobalConfig.VACUUM_CLEANER_SPEED_2),
            new MenuOption("4x", 2, GlobalConfig.VACUUM_CLEANER_SPEED_4),
            new MenuOption("6x", 3, GlobalConfig.VACUUM_CLEANER_SPEED_6)
    );

    private SingleMenuData debug = new SingleMenuData("Debug", globalConfig.getMode(),
            new MenuOption("Yes", 1, GlobalConfig.MODE_DEBUG),
            new MenuOption("No", 2, GlobalConfig.MODE_NO_DEBUG)
    );

    private SingleMenuData sound = new SingleMenuData("Sound", globalConfig.getSound(),
            new MenuOption("On", 1, GlobalConfig.SOUND_ON),
            new MenuOption("Off", 2, GlobalConfig.SOUND_OFF)
    );

    private SingleMenuData[] singleMenuData = {simulationMode, time, renderThrashType, vacuumCleanerVelocity, debug, sound};

    private KeyInput keyInput;

    public MenuState(String name, int actualState, KeyInput keyInput, GlobalConfig globalConfig) {
        super(name, actualState, globalConfig);
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
        singleMenuData[menuLabelSelected].nextOption();
    }

    public void moveBackward() {
        singleMenuData[menuLabelSelected].prevOption();
    }

    public void moveUp() {
        if (menuLabelSelected > 0)
            menuLabelSelected--;
    }

    public void moveDown() {
        if (menuLabelSelected < singleMenuData.length - 1)
            menuLabelSelected++;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(xStart, yStart, width, height);

        graphics.setFont(headerMenuFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString(name, xStart + (headerMenuFont.getSize() + 10), yStart + (headerMenuFont.getSize() * 2));

        for (int i = 0; i < singleMenuData.length; i++) {
            if (i == menuLabelSelected)
                graphics.setColor(Color.RED);
            else
                graphics.setColor(Color.WHITE);

            graphics.setFont(labelMenuFont);
            graphics.drawString(singleMenuData[i].getLabel(), xStart + (labelMenuFont.getSize() + 10), (headerMenuFont.getSize() * 3 + 40) + (yStart + (labelMenuFont.getSize() + 80) * i));

            graphics.setFont(optionMenuFont);
            graphics.drawString(singleMenuData[i].getSelectedMenuOption().getLabel(), width - (optionMenuFont.getSize() + 100), (headerMenuFont.getSize() * 3 + 40) + (yStart + (optionMenuFont.getSize() + 80) * i));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        globalConfig.setSimulationMode(simulationMode.getSelectedMenuOption().getOptionCode());
        globalConfig.setTimeLimit(time.getSelectedMenuOption().getOptionCode());
        globalConfig.setAmountRenderThrashType(renderThrashType.getSelectedMenuOption().getOptionCode());
        globalConfig.setVacuumCleanerVelocity(vacuumCleanerVelocity.getSelectedMenuOption().getOptionCode());
        globalConfig.setMode(debug.getSelectedMenuOption().getOptionCode());
        globalConfig.setSound(sound.getSelectedMenuOption().getOptionCode());

        // caso especial
        if(globalConfig.getSimulationMode() == GlobalConfig.SIMULATION_MODE_TIME && globalConfig.getTimeLimit() == GlobalConfig.TIME_MODE_NO_TIME) {
            time.setOption(2);
            globalConfig.setTimeLimit(GlobalConfig.TIME_MODE_MILLIS_MINUTES_1);
        }

    }
}
