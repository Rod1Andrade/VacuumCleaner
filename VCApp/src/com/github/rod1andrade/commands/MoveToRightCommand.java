package com.github.rod1andrade.commands;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

public class MoveToRightCommand extends VacuumCleanerCommand {

    public MoveToRightCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
    }

    @Override
    public void execute() {
        vacuumCleanerRenderEntity.setPreviousDirection(vacuumCleanerModel.getDirection());
        vacuumCleanerModel.setDirection(VacuumCleanerRenderEntity.DIRECTION_RIGHT);

        vacuumCleanerRenderEntity.setAxisVelocity(vacuumCleanerModel.getVelocity(), 0);
        vacuumCleanerRenderEntity.setDirection(vacuumCleanerModel.getDirection());
    }
}
