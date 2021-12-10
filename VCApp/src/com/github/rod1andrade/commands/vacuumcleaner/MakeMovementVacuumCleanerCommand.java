package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.models.VacuumCleanerModel;

/**
 * @author Rodrigo Andrade
 */
public class MakeMovementVacuumCleanerCommand extends VacuumCleanerCommand {
    public MakeMovementVacuumCleanerCommand(VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity) {
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
