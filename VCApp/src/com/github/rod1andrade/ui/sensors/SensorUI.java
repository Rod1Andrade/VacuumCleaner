package com.github.rod1andrade.ui.sensors;

import com.github.rod1andrade.assets.Sprite;
import com.github.rod1andrade.entities.RenderEntity;
import com.github.rod1andrade.util.GlobalConfig;
import com.github.rod1andrade.util.Loader;

import java.awt.*;

/**
 * @author Rodrigo Andrade
 */
public abstract class SensorUI extends RenderEntity {

    private int currentSprite = 0;
    private int controllingAnimationTime = 0;

    private boolean cooldown = true;
    private long time;

    private String label = "Label";

    private Sprite[] sprites = {
            new Sprite(112, 64, 16, 16, Loader.spriteSheet001),
            new Sprite(128, 64, 16, 16, Loader.spriteSheet001),
    };

    public SensorUI(int posX, int posY, int width, int height, String label, boolean isDebugMode) {
        this.posX = posX;
        this.posY = posY;
        this.width = width * SCALE_FACTOR;
        this.height = height * SCALE_FACTOR;

        this.sprite = sprites[currentSprite];
        this.isDebugMode = isDebugMode;

        setLabel(label);
    }

    private void setLabel(String label) {
        if (label.isEmpty()) {
            this.label = "Label";
        } else {
            this.label = label;
        }
    }

    public abstract boolean doAnimation();

    @Override
    public void update(float deltaTime) {
        if(System.currentTimeMillis() - time > 500) {
            cooldown = true;
            currentSprite = 0;
            sprite = sprites[currentSprite];
        }

        if(doAnimation()) {
            cooldown = false;
            time = System.currentTimeMillis();
        }

       if(!cooldown) {
           if (currentSprite >= sprites.length) {
               currentSprite = 0;
           }

           if (controllingAnimationTime > 6) {
               sprite = sprites[currentSprite++];
               controllingAnimationTime = 0;
           }

           controllingAnimationTime += deltaTime;
       }
    }

    @Override
    public void render(Graphics graphics) {
        Font font = new Font("Dialog", Font.PLAIN, 12);

        graphics.setColor(Color.WHITE);
        graphics.drawString(label, posX + font.getSize() + label.length() / 2, posY - font.getSize() / 2);

        if(GlobalConfig.getInstance().isDebugMode()) {
            graphics.drawString("" + doAnimation(), posX, (posY + height) + 10);
        }

        graphics.drawImage(sprite.getSprite(SCALE_FACTOR), posX, posY, null);
    }
}
