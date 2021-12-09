package com.github.rod1andrade.ui.sensors;

import com.github.rod1andrade.objects.VacuumCleaner;

/**
 * @author Rodrigo Andrade
 */
public final class SouthSensorUI extends SensorUI {

    private VacuumCleaner vacuumCleaner;

    public SouthSensorUI(int posX, int posY, int width, int height, String label, VacuumCleaner vacuumCleaner, boolean isDebugMode) {
        super(posX, posY, width, height, label, isDebugMode);
        this.vacuumCleaner = vacuumCleaner;
    }

    @Override
    public synchronized boolean doAnimation() {
        return vacuumCleaner.hasCollided() && vacuumCleaner.collidedDirection() == VacuumCleaner.DIRECTION_DOWN;
    }
}
