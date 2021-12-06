package graphics.render;

import app.Log;
import graphics.objects.Window;
import graphics.objects.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Rodrigo Andrade
 */
public final class WindowRender extends Canvas implements Runnable {

    // Constantes de configuracao
    private final static double NS_PER_60_UPS = 1_000_000_000.0 / 60.0;
    private final static int ONE_SECOND_IN_MILIS = 1000;
    private final static int BUFFER_STRATEGY = 2;

    // Informacoes de tamanho da tela de renderizacao
    public static final int ASPECT_RATIO = 4 / 3;
    public static final int WIDTH = 750;
    public static final int HEIGHT = WIDTH * ASPECT_RATIO;

    private boolean isRunning;
    private Thread windowRenderThread;

    private final BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private final RenderEngine renderEngine = new RenderEngine(WIDTH, HEIGHT);

    HashMap<String, Entity> entities = new HashMap<>();

    public WindowRender() {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Inicia recursos.
     */
    public synchronized void initResources()  {

        entities.put("table", new Table(88, getHeight() / 2, 30, 22));
        entities.put("vacuumCleaner", new VacuumCleaner(getWidth() / 2, getHeight() / 2, 16, 16, new ArrayList<Entity>(entities.values())));

        // Renderizando as paredes
        for (int x = 0; x < getWidth(); x += 64) {
            renderEngine.addEntityToRender(new StripedWall(x, 0, 16, 32));
        }

        // Renderizando o chao
        for (int y = 128; y < getHeight(); y += 64) {
            for (int x = 0; x < getWidth(); x += 64) {
                renderEngine.addEntityToRender(new Floor(x, y, 16, 16));
            }
        }

        // Objetos
        renderEngine.addEntityToRender(new Window(48, (64 - 16) / 2, 14, 14));

        renderEngine.addEntityToRender(new Mat(getWidth() / 2, getHeight() / 2, 48, 32));
        renderEngine.addEntityToRender(new OilPaint((getWidth() / 2) - 16, (64 - 16) / 2, 16, 16));

        renderEngine.addEntityToRender(new MiniTable(32, (128 + 32), 16, 16));
        renderEngine.addEntityToRender(new Television(32, 128, 16, 16));

        renderEngine.addEntityToRender(new Lamp(getWidth() - 64, 128 - 80, 13, 27));
        renderEngine.addEntityToRender(new Shelf((getWidth() / 2) + 64, 128 - 80, 16, 27));

        renderEngine.addEntityToRender(new Plant(getWidth() / 2 + 130, 128 - 60, 16, 20));

        renderEngine.addEntityToRender(new LeftChair(88 - 36, getHeight() / 2, 14, 19));
        renderEngine.addEntityToRender(new RightChair(88 + 105, getHeight() / 2, 14, 19));
        renderEngine.addEntityToRender(new BackChair(88 + 30, getHeight() / 2 - 52, 14, 19));
        renderEngine.addEntityToRender(entities.get("table"));
        renderEngine.addEntityToRender(new FrontChair(88 + 30, getHeight() / 2 + 66, 14, 19));

        renderEngine.addEntityToRender(entities.get("vacuumCleaner"));

        addNotify();
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
        // TODO: Ideia de zooom in e zoom out: Camera seguir o aspirador de po
//        bufferStrategyDrawGraphics.drawImage(bufferedImage.getScaledInstance(WIDTH * 2, HEIGHT * 2, BufferedImage.SCALE_DEFAULT), (getWidth() / 2) * - 1, (getHeight() / 2) * -1, null);
        bufferStrategyDrawGraphics.drawImage(bufferedImage, 0,0, null);

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
