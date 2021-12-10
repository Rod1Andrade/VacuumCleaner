package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

public class MoveToDownCommand extends VacuumCleanerCommand {

    public MoveToDownCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
    }

    @Override
    public void execute() {
        vacuumCleanerRenderEntity.setPreviousDirection(vacuumCleanerModel.getDirection());
        vacuumCleanerModel.setDirection(VacuumCleanerRenderEntity.DIRECTION_DOWN);

        vacuumCleanerRenderEntity.setAxisVelocity(0, vacuumCleanerModel.getVelocity());
        vacuumCleanerRenderEntity.setDirection(vacuumCleanerModel.getDirection());
    }
}
