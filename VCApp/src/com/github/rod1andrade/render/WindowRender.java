package com.github.rod1andrade.render;

import com.github.rod1andrade.enums.Mode;
import com.github.rod1andrade.inputs.KeyInput;
import com.github.rod1andrade.states.MenuState;
import com.github.rod1andrade.states.SimulationState;
import com.github.rod1andrade.states.State;
import com.github.rod1andrade.util.Config;
import com.github.rod1andrade.util.GlobalInfo;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


/**
 * @author Rodrigo Andrade
 */
public final class WindowRender extends Canvas implements Runnable {

    private static final long serialVersionUID = 3060247783915240461L;

    private KeyInput keyInput;

    // Constantes de configuracao
    private final static double NS_PER_60_UPS = 1_000_000_000.0 / 60.0;
    private final static int ONE_SECOND_IN_MILIS = 1000;
    private final static int BUFFER_STRATEGY = 2;

    private boolean isRunning;
    private Thread windowRenderThread;

    private final BufferedImage bufferedImage = new BufferedImage(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    private int currentState = Config.STATE_SIMULATION;
    private State[] states = new State[Config.STATE_QUANTITY_STATE];

    public WindowRender(KeyInput keyInput) {
        setMinimumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setMaximumSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setFocusable(true);

        this.keyInput = keyInput;
    }

    /**
     * Inicia recursos.
     */
    public synchronized void initResources() {
        addKeyListener(keyInput);

        states[Config.STATE_MENU] = new MenuState("Menu State", State.RUNNING, keyInput);
        states[Config.STATE_SIMULATION] = new SimulationState("Simulation", State.RUNNING);

        // Inicia os recurso de cada state
        for(State state : states)
            state.initializeResources();

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
     */
    public synchronized void stop() throws InterruptedException {
        isRunning = false;
        windowRenderThread.join();
    }

    /**
     * Trabalha com as entradas do usuario.
     */
    public synchronized void userInputs() {
        if (keyInput.hasPressed(KeyEvent.VK_D)) {
            GlobalInfo.mode = Mode.DEBUG;
        }

        if (keyInput.hasPressed(KeyEvent.VK_N)) {
            GlobalInfo.mode = Mode.DEV;
        }

        if(keyInput.hasPressed(KeyEvent.VK_ESCAPE)) {
            states[currentState].pause();
            currentState = Config.STATE_MENU;
            states[currentState].resume();
        }

        if(keyInput.hasPressed(KeyEvent.VK_S)) {
            states[currentState].pause();
            currentState = Config.STATE_SIMULATION;
            states[currentState].resume();
        }
    }


    /**
     * Metodo que vai ser chamado a uma taxa aproximada
     * de 60 updates por segundo para fazer acoes.
     */
    public void update(float deltaTime) {
        states[currentState].update(deltaTime);
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

        states[currentState].render(bufferedImageGraphics);

        // Cria um grafico a partir do buffer strategy (prove o page flip)
        Graphics bufferStrategyDrawGraphics = bufferStrategy.getDrawGraphics();

        // Desenha a imagem no grafico do buffer strategy
        bufferStrategyDrawGraphics.drawImage(bufferedImage, 0, 0, null);

        bufferedImageGraphics.dispose();
        do {
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Thread sleep encapsulado.
     *
     * @param time
     */
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

            userInputs();

            while (delta >= 1) {
                update((float) delta);
                ups++;
                delta--;
                shouldRender = true;
            }

            waitTime(5);

            if (shouldRender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - millisBeforeTime > ONE_SECOND_IN_MILIS) {
                millisBeforeTime += ONE_SECOND_IN_MILIS;
                ups = 0;
                fps = 0;
            }
        }
    }
}
