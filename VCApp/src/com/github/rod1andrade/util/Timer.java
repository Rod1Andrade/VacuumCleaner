package com.github.rod1andrade.util;

/**
 * @author Rodrigo Andrade
 */
public class Timer {

    private long startTime;
    private long elapsedTime;
    private long currentTime;
    private long stopTime;
    private boolean canUpdate;

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
        getInstance().canUpdate = true;
    }

    public static void stop() {
        getInstance().stopTime = getElapsedTime();
        getInstance().canUpdate = false;
    }

    public static void resume() {
        getInstance().canUpdate = true;
    }

    public static void updateCurrentTime() {
        if(getInstance().canUpdate)
            getInstance().currentTime = System.currentTimeMillis();
    }

    public static long getElapsedTime() {
        getInstance().elapsedTime = getInstance().currentTime - getInstance().startTime;

        return getInstance().elapsedTime;
    }

    public static long getStopTime() {
        return getInstance().stopTime;
    }

    public String stopTimeFomartToMinutes() {
        int seconds = (int) (getStopTime() / 1000) % 60 ;
        int minutes = (int) ((getStopTime() / (1000*60)) % 60);
        return parsetToMinuteSecondsFormat(minutes, seconds);
    }

    public String elapseTimeFomartToMinutes() {
        int seconds = (int) (getElapsedTime() / 1000) % 60 ;
        int minutes = (int) ((getElapsedTime() / (1000*60)) % 60);
        return parsetToMinuteSecondsFormat(minutes, seconds);
    }

    public static String parsetToMinuteSecondsFormat(long minutes, long seconds) {
        return String.format("%02d:%02d", minutes, seconds);
    }
}
