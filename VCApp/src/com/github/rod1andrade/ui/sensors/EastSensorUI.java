package com.github.rod1andrade.ui.sensors;

import com.github.rod1andrade.objects.VacuumCleaner;
import com.github.rod1andrade.ui.sensors.SensorUI;

/**
 * @author Rodrigo Andrade
 */
public final class EastSensorUI extends SensorUI {

    private VacuumCleaner vacuumCleaner;

    public EastSensorUI(int posX, int posY, int width, int height, String label, VacuumCleaner vacuumCleaner) {
        super(posX, posY, width, height, label);
        this.vacuumCleaner = vacuumCleaner;
    }

    @Override
    public synchronized boolean doAnimation() {
        return vacuumCleaner.hasCollided() && vacuumCleaner.collidedDirection() == VacuumCleaner.DIRECTION_RIGHT;
    }
}
