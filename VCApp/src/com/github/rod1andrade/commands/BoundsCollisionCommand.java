package com.github.rod1andrade.commands;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

/**
 * @author Rodrigo Andrade
 */
public class BoundsCollisionCommand extends VacuumCleanerCommand {

    private final int xMin;
    private final int yMin;
    private final int xBound;
    private final int yBound;

    public BoundsCollisionCommand(
            VacuumCleanerModel vacuumCleanerModel,
            VacuumCleanerRenderEntity vacuumCleanerRenderEntity,
            int xMin, int yMin,
            int xBound, int yBound
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
        this.xMin = xMin;
        this.yMin = yMin;
        this.xBound = xBound;
        this.yBound = yBound;
    }

    @Override
    public void execute() {
        if (vacuumCleanerRenderEntity.getPosX() + vacuumCleanerRenderEntity.getWidth() >= xBound) {
            vacuumCleanerModel.setHasCollision(true);
        }

        if (vacuumCleanerRenderEntity.getPosY() + vacuumCleanerRenderEntity.getHeight() >= yBound) {
            vacuumCleanerModel.setHasCollision(true);
        }

        if (vacuumCleanerRenderEntity.getPosX() <= xMin) {
            vacuumCleanerModel.setHasCollision(true);
        }

        if (vacuumCleanerRenderEntity.getPosY() <= yMin) {
            vacuumCleanerModel.setHasCollision(true);
        }
    }
}
