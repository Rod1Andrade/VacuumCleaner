package com.github.rod1andrade.commands;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

/**
 * @author Rodrigo Andrade
 */
public class MakeMovementCommand extends VacuumCleanerCommand {
    public MakeMovementCommand(VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
    }

    @Override
    public void execute() {
        vacuumCleanerRenderEntity.setPositionValues(
                vacuumCleanerRenderEntity.getPosX() + vacuumCleanerRenderEntity.getXVelocity(),
                vacuumCleanerRenderEntity.getPosY() + vacuumCleanerRenderEntity.getYVelocitoy()
        );
    }
}
