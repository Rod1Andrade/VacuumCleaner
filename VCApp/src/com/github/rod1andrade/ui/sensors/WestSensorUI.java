package com.github.rod1andrade.ui.sensors;

import com.github.rod1andrade.objects.VacuumCleaner;

/**
 * @author Rodrigo Andrade
 */
public final class WestSensorUI extends SensorUI {

    private VacuumCleaner vacuumCleaner;

    public WestSensorUI(int posX, int posY, int width, int height, String label, VacuumCleaner vacuumCleaner) {
        super(posX, posY, width, height, label);
        this.vacuumCleaner = vacuumCleaner;
    }

    @Override
    public synchronized boolean doAnimation() {
        return vacuumCleaner.hasCollided() && vacuumCleaner.collidedDirection() == VacuumCleaner.DIRECTION_LEFT;
    }
}
