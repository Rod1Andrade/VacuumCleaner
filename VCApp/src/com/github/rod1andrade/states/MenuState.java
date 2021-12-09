package com.github.rod1andrade.states;

import com.github.rod1andrade.inputs.KeyInput;
import com.github.rod1andrade.util.Config;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;

public class MenuState extends State {

    private final int space = (int) (Config.WINDOW_WIDTH * 0.10);
    private final int width = Config.WINDOW_WIDTH - space;
    private final int height = Config.WINDOW_HEIGHT - space;

    private final int xStart = space / 2;
    private final int yStart = space / 2;

    // Menu fonts
    private Font defaultMenuFont;
    private Font headerMenuFont;
    private Font labelMenuFont;
    private Font optionMenuFont;

    // Screen data
    private int menuLabelSelected = 0;

    private String[] menuLabel = {
            "Mode",
            "Time",
            "Render Thrash Type",
            "Vacuum Cleaner Velocity",
            "Debug"
    };

    private String[][] options = {
            {"Infinity", "Until Clean", "Time"},
            {"No Time", "1 min", "2 min", "3 min", "4 min", "5 min"},
            {"Infinity", "Random"},
            {"2x", "4x", "6x"},
            {"Yes", "No"},
    };

    private int[] selected = new int[5];

    private KeyInput keyInput;

    public MenuState(String name, int actualState, KeyInput keyInput) {
        super(name, actualState);
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

    long time = 0;
    @Override
    public void update(float deltaTime) {
        if(actualState == State.RUNNING) {
            if(time > 6) {
                if(keyInput.hasPressed(KeyEvent.VK_DOWN)) {
                    moveDown();
                }

                if(keyInput.hasPressed(KeyEvent.VK_UP)) {
                    moveUp();
                }

                if(keyInput.hasPressed(KeyEvent.VK_RIGHT)) {
                    moveForward();
                }

                if(keyInput.hasPressed(KeyEvent.VK_LEFT)) {
                    moveBackward();
                }

                time = 0;
            }
            time += deltaTime;
        }
    }

    public void moveForward() {
        if(selected[menuLabelSelected] == options[menuLabelSelected].length - 1)
            selected[menuLabelSelected] = 0;

        selected[menuLabelSelected]++;
    }

    public void moveBackward() {
        if(selected[menuLabelSelected] == 0)
            selected[menuLabelSelected] = options[menuLabelSelected].length - 1;

        selected[menuLabelSelected]--;
    }

    public void moveUp() {
        if(menuLabelSelected == 0)
            menuLabelSelected = menuLabel.length - 1;

        menuLabelSelected--;
    }

    public void moveDown() {
        if(menuLabelSelected == menuLabel.length - 1)
            menuLabelSelected = 0;

        menuLabelSelected++;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 0, 0, 255));
        graphics.fillRect(xStart, yStart, width, height);

        graphics.setFont(headerMenuFont);
        graphics.setColor(Color.WHITE);
        graphics.drawString("Settings", xStart + (headerMenuFont.getSize() + 10), yStart + (headerMenuFont.getSize() * 2));

        for(int i = 0; i < menuLabel.length; i++) {
            if(i == menuLabelSelected)
                graphics.setColor(Color.RED);
            else
                graphics.setColor(Color.WHITE);

            graphics.setFont(labelMenuFont);
            graphics.drawString(menuLabel[i], xStart + (labelMenuFont.getSize() + 10), (headerMenuFont.getSize() * 3 + 40) + (yStart + (labelMenuFont.getSize() + 80) * i));

            graphics.setFont(optionMenuFont);
            graphics.drawString(options[i][selected[i]], width - (optionMenuFont.getSize() + 100), (headerMenuFont.getSize() * 3 + 40) + (yStart + (optionMenuFont.getSize() + 80) * i));
        }
    }
}
