package com.github.rod1andrade.commands;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

public class MoveToUpCommand extends VacuumCleanerCommand {

    public MoveToUpCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
    }

    @Override
    public void execute() {
        vacuumCleanerRenderEntity.setPreviousDirection(vacuumCleanerModel.getDirection());
        vacuumCleanerModel.setDirection(VacuumCleanerRenderEntity.DIRECTION_UPPER);

        vacuumCleanerRenderEntity.setAxisVelocity(0, -vacuumCleanerModel.getVelocity());
        vacuumCleanerRenderEntity.setDirection(vacuumCleanerModel.getDirection());
    }
}
