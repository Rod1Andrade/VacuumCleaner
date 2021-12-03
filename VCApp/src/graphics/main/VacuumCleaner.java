package graphics.main;

import graphics.screen.DisplayScreen;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main extends Canvas implements Runnable{

    public static final int SCALE = 16 / 9;
    public static final int WIDTH = 240;
    public static final int HEIGHT = WIDTH * SCALE;

    private volatile boolean isRunning = false;

    private final BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final int[] pixels = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();

    private final DisplayScreen displayScreen = new DisplayScreen(WIDTH, HEIGHT);

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        displayScreen.render();

        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = displayScreen.getPixel(i);
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.drawImage(bufferedImage, 0, 0, WIDTH, HEIGHT, null);

        g.dispose();
        bs.show();
    }

    private void update() {}

    private void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(isRunning) {
            render();
            update();
            sleep();
        }
    }
}
