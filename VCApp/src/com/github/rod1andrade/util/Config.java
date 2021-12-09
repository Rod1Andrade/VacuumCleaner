package com.github.rod1andrade.util;

/**
 * Parametros de configuracao da aplicacao.
 *
 * @author Rodrigo Andrade
 */
public class Config {

    /**
     * Modo de simulacao
     */
    public static final int SIMULATION_MODE_TIME = 1;
    public static final int SIMULATION_MODE_INFINITY = 0;
    public static final int SIMULATION_MODE_UNTIL_CLEAN = 3;

    /**
     * Padroes de tempo para a simulacao (minutos para milisegundos)
     */
    public static final int TIME_MODE_NO_TIME = 0;
    public static final int TIME_MODE_MILLIS_MINUTES_1 = 1000 * 60; // 1 minuto
    public static final int TIME_MODE_MILLIS_MINUTES_2 = TIME_MODE_MILLIS_MINUTES_1 * 2; // 2 minutos
    public static final int TIME_MODE_MILLIS_MINUTES_3 = TIME_MODE_MILLIS_MINUTES_1 * 3; // 3 minutos
    public static final int TIME_MODE_MILLIS_MINUTES_4 = TIME_MODE_MILLIS_MINUTES_1 * 4; // 4 mintuos
    public static final int TIME_MODE_MILLIS_MINUTES_5 = TIME_MODE_MILLIS_MINUTES_1 * 5; // 5 miutos

    /**
     * Padroes de Quantidade de lixos
     */
    public static final int AMOUNT_RENDER_THRASH_TYPE_INFINITY = 4;
    public static final int AMOUNT_RENDER_THRASH_TYPE_RANDOM = 5;

    /**
     * Velocidades
     */
    public static final int VACUUM_CLEANER_SPEED_2 = 2;
    public static final int VACUUM_CLEANER_SPEED_4 = 4;
    public static final int VACUUM_CLEANER_SPEED_6 = 6;

    public static final int QUANTITY_THRASH_LIMIT = 10;

    /**
     * Parametros de configuracao
     */
    private int simulationMode;
    private int timeLimit;
    private int amountRenderThrashType;
    private int vacuumCleanerVelocity;

    public Config() {
        defaultConfig();
    }

    /**
     * Configuracoes padrao.
     */
    public void defaultConfig() {
        simulationMode = SIMULATION_MODE_UNTIL_CLEAN;
        timeLimit = TIME_MODE_NO_TIME;
        amountRenderThrashType = AMOUNT_RENDER_THRASH_TYPE_RANDOM;
        vacuumCleanerVelocity = VACUUM_CLEANER_SPEED_2;
    }

    public int getSimulationMode() {
        return simulationMode;
    }

    public void setSimulationMode(int simulationMode) {
        this.simulationMode = simulationMode;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getAmountRenderThrashType() {
        return amountRenderThrashType;
    }

    public void setAmountRenderThrashType(int amountRenderThrashType) {
        this.amountRenderThrashType = amountRenderThrashType;
    }

    public int getVacuumCleanerVelocity() {
        return vacuumCleanerVelocity;
    }

    public void setVacuumCleanerVelocity(int vacuumCleanerVelocity) {
        this.vacuumCleanerVelocity = vacuumCleanerVelocity;
    }
}
