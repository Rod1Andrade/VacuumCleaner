package com.github.rod1andrade.ui.sensors;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

/**
 * @author Rodrigo Andrade
 */
public final class EastSensorUI extends SensorUI {


    private VacuumCleanerModel vacuumCleanerModel;

    public EastSensorUI(int posX, int posY, int width, int height, String label, VacuumCleanerModel vacuumCleanerModel, boolean isDebugMode) {
        super(posX, posY, width, height, label, isDebugMode);
        this.vacuumCleanerModel = vacuumCleanerModel;
    }

    @Override
    public synchronized boolean doAnimation() {
        return vacuumCleanerModel.hasCollision() && vacuumCleanerModel.collidedDirection() == VacuumCleanerRenderEntity.DIRECTION_UPPER;
    }
}
