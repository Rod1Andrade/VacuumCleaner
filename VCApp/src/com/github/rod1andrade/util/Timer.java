package com.github.rod1andrade.util;

/**
 * @author Rodrigo Andrade
 */
public class Timer {

    private long startTime;
    private long elapsedTime;
    private long currentTime;

    private static Timer instance;

    private Timer() {
    }

    public static Timer getInstance() {
        if (instance == null) instance = new Timer();
        return instance;
    }

    public static void start() {
        getInstance().startTime = System.currentTimeMillis();
        getInstance().currentTime = getInstance().startTime;
    }

    public static void updateCurrentTime() {
        getInstance().currentTime = System.currentTimeMillis();
    }

    public static long getElapsedTime() {
        getInstance().elapsedTime = getInstance().currentTime - getInstance().startTime;

        return getInstance().elapsedTime;
    }

    public String elapseTimeFomartToMinutes() {
        int seconds = (int) (getElapsedTime() / 1000) % 60 ;
        int minutes = (int) ((getElapsedTime() / (1000*60)) % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
