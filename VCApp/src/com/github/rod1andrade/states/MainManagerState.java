package com.github.rod1andrade.states;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.inputs.KeyInput;
import com.github.rod1andrade.models.VacuumCleanerModel;
import com.github.rod1andrade.util.GlobalConfig;
import com.github.rod1andrade.util.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


/**
 * @author Rodrigo Andrade
 */
public final class MainManagerState extends Canvas implements Runnable {

    private static final long serialVersionUID = 3060247783915240461L;

    private KeyInput keyInput;

    public static int ups = 0;
    public static int fps = 0;

    private boolean isRunning;
    private Thread windowRenderThread;

    private final BufferedImage bufferedImage = new BufferedImage(GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    private int currentState = GlobalConfig.STATE_SIMULATION;
    private State[] states = new State[GlobalConfig.STATE_QUANTITY_STATE];

    private  VacuumCleanerModel vacuumCleanerModel;

    private GlobalConfig globalConfig;

    public MainManagerState(KeyInput keyInput) {
        setMinimumSize(new Dimension(GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT));
        setMaximumSize(new Dimension(GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT));
        setPreferredSize(new Dimension(GlobalConfig.WINDOW_WIDTH, GlobalConfig.WINDOW_HEIGHT));
        setFocusable(true);

        this.keyInput = keyInput;
        globalConfig = GlobalConfig.getInstance();
    }

    /**
     * Inicia recursos.
     */
    public synchronized void initResources() {

        addKeyListener(keyInput);

        globalConfig.defaultConfig();
        vacuumCleanerModel = new VacuumCleanerModel(globalConfig.getVacuumCleanerVelocity(), VacuumCleanerRenderEntity.DIRECTION_RIGHT, false);

        states[GlobalConfig.STATE_MENU] = new MenuState("Settings", State.STOP, keyInput, globalConfig);
        states[GlobalConfig.STATE_SIMULATION] = new SimulationState("Simulation", State.RUNNING, globalConfig, vacuumCleanerModel);
        states[GlobalConfig.STATE_RESUME_SIMULATION] = new ResumeSimulationState("Resume", State.STOP, globalConfig, vacuumCleanerModel);

        // Inicia os recurso de cada state
        for (State state : states)
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
        if (keyInput.hasPressed(KeyEvent.VK_ESCAPE)) {
            changeState(GlobalConfig.STATE_MENU);
        }

        if (keyInput.hasPressed(KeyEvent.VK_S)) {
            changeState(GlobalConfig.STATE_SIMULATION);
        }

        if (keyInput.hasPressed(KeyEvent.VK_R)) {
            changeState(GlobalConfig.STATE_RESUME_SIMULATION);
        }
    }

    /**
     * Altera o estado.
     *
     * @param state
     */
    private synchronized void changeState(int state) {
        states[currentState].pause();
        states[currentState].dispose();
        currentState = state;
        states[currentState].resume();
    }

    /**
     * Metodo que vai ser chamado a uma taxa aproximada
     * de 60 updates por segundo para fazer acoes.
     */
    public void update(float deltaTime) {

        // Regra de funcinalide para o modo de simulacao temporal
        if(globalConfig.getSimulationMode() == GlobalConfig.SIMULATION_MODE_TIME) {
            if(Timer.getElapsedTime() > globalConfig.getTimeLimit()) {
                changeState(GlobalConfig.STATE_RESUME_SIMULATION);
            }
        }


        // Regra de funcinalidade para o modo de simulacao ate limpar
        if(globalConfig.getSimulationMode() == GlobalConfig.SIMULATION_MODE_UNTIL_CLEAN) {
            if(vacuumCleanerModel.amountCollectedTrashs() == GlobalConfig.QUANTITY_THRASH_LIMIT) {
                changeState(GlobalConfig.STATE_RESUME_SIMULATION);
            }

            // Regra para o modo de simulacao ate limpar + limite de tempo.
            if(globalConfig.getTimeLimit() != 0) {
                if(Timer.getElapsedTime() > globalConfig.getTimeLimit()) {
                    changeState(GlobalConfig.STATE_RESUME_SIMULATION);
                }
            }
        }

        states[currentState].update(deltaTime);
    }

    /**
     * Metodo que vai ser chamado em uma taxa aproximada
     * de 60 frames por segundo para renderizar na tela.
     */
    public void render() {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(GlobalConfig.RENDER_BUFFER_STRATEGY);
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

        Timer.start();

        long millisBeforeTime = System.currentTimeMillis();
        long nsBeforeTime = System.nanoTime();

        double delta = 0;

        int fpsCounter = 0;
        int upsCounter = 0;

        while (isRunning) {
            boolean shouldRender = false;
            long nsNowTime = System.nanoTime();
            long difference = nsNowTime - nsBeforeTime;

            nsBeforeTime = nsNowTime;
            delta += difference / GlobalConfig.RENDER_NS_PER_60_UPS;

            userInputs();

            while (delta >= 1) {
                update((float) delta);
                upsCounter++;
                delta--;
                shouldRender = true;
            }

            waitTime(5);

            if (shouldRender) {
                render();
                fpsCounter++;
            }

            if (System.currentTimeMillis() - millisBeforeTime > 1000) {
                millisBeforeTime += 1000;
                ups = upsCounter;
                fps = fpsCounter;
                fpsCounter = 0;
                upsCounter = 0;
            }

            Timer.updateCurrentTime();
        }
    }
}
