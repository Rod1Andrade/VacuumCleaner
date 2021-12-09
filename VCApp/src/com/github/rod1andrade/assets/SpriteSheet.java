package com.github.rod1andrade.assets;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Rodrigo Andrade
 */
public class SpriteSheet {

    protected final String path;
    protected final int width;
    protected final int height;
    protected BufferedImage bufferedImage;

    public SpriteSheet(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;

        File file = new File(path);
        if(file.exists()) {
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Image crop(int x, int y, int w, int h) {
        return bufferedImage.getSubimage(x, y, w, h);
    }
}
