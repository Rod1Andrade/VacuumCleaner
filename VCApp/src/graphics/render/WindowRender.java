package graphics.render;

import app.Log;
import graphics.assets.Sprite;
import graphics.assets.SpriteSheet;
import graphics.objects.Entity;
import graphics.objects.Television;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rodrigo Andrade
 */
public class WindowRender extends Canvas implements Runnable {

    // Constantes de configuracao
    private final static double NS_PER_60_UPS = 1_000_000_000.0 / 60.0;
    private final static int ONE_SECOND_IN_MILIS = 1000;
    private final static int BUFFER_STRATEGY = 2;

    // Informacoes de tamanho da tela de renderizacao
    public static final int ASPECT_RATIO = 16 / 9;
    public static final int WIDTH = 750;
    public static final int HEIGHT = WIDTH * ASPECT_RATIO;

    private boolean isRunning;
    private Thread windowRenderThread;

    private BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    private static final SpriteSheet spriteSheet = new SpriteSheet("assets/vc-assets.png", 128, 80);

    public static final Sprite tvSprite = new Sprite(1, 48, 16, 16, spriteSheet);
    public static final Sprite lampSprite = new Sprite(82, 51, 13, 27, spriteSheet);
    public static final Sprite plantSprite = new Sprite(81, 1, 16, 20, spriteSheet);

    private final RenderEngine renderEngine = new RenderEngine(WIDTH, HEIGHT);

    public WindowRender() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public synchronized void initResources() throws IOException {
        renderEngine.addEntityToRender(new Television(0,0, 16, 16, tvSprite));
    }

    /**
     * Inicia a Thread de renderizacao e coloca o loop em true.
     */
    public synchronized void start() {
        if (windowRenderThread == null)
            windowRenderThread = new Thread(this);

        isRunning = true;
        windowRenderThread.start();
    }

    /**
     * Coloca o loop em false e finaliza a thread de renderizacao.
     *
     * @throws InterruptedException
     */
    public synchronized void stop() throws InterruptedException {
        isRunning = false;
        windowRenderThread.join();
    }

    /**
     * Metodo que vai ser chamado a uma taxa aproximada
     * de 60 updates por segundo para fazer acoes.
     */
    public void update() {
        renderEngine.update(12.f);
    }

    /**
     * Metodo que vai ser chamado em uma taxa aproximada
     * de 60 frames por segundo para renderizar na tela.
     */
    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(BUFFER_STRATEGY);
            return;
        }

        // Cria grafico a partir do buffer da imagem e desenha nela
        Graphics bufferedImageGraphics = bufferedImage.createGraphics();

        renderEngine.clear(bufferedImageGraphics);
        renderEngine.render(bufferedImageGraphics);

        // Cria um grafico a partir do buffer strategy (prove o page flip)
        Graphics bufferStrategyDrawGraphics = bufferStrategy.getDrawGraphics();

        // Desenha a imagem no grafico do buffer strategy
        bufferStrategyDrawGraphics.drawImage(bufferedImage, 0, 0, null);

        bufferedImageGraphics.dispose();
        do {
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    public void waitTime(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long millisBeforeTime = System.currentTimeMillis();
        long nsBeforeTime = System.nanoTime();

        double delta = 0;
        int ups = 0;
        int fps = 0;

        while (isRunning) {
            boolean shouldRender = false;
            long nsNowTime = System.nanoTime();
            long difference = nsNowTime - nsBeforeTime;

            nsBeforeTime = nsNowTime;
            delta += difference / NS_PER_60_UPS;

            while (delta >= 1) {
                update();
                ups++;
                delta--;
                shouldRender = true;
            }

            waitTime(2);

            if (shouldRender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - millisBeforeTime > ONE_SECOND_IN_MILIS) {
                millisBeforeTime += ONE_SECOND_IN_MILIS;
                Log.consoleLog("ups: " + ups + ", fps: " + fps);
                ups = 0;
                fps = 0;
            }
        }
    }
}
