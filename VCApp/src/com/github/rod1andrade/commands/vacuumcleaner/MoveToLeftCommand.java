package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

public class MoveToLeftCommand extends VacuumCleanerCommand {

    public MoveToLeftCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
    }

    @Override
    public void execute() {
        vacuumCleanerRenderEntity.setPreviousDirection(vacuumCleanerModel.getDirection());
        vacuumCleanerModel.setDirection(VacuumCleanerRenderEntity.DIRECTION_LEFT);

        vacuumCleanerRenderEntity.setAxisVelocity(-vacuumCleanerModel.getVelocity(), 0);
        vacuumCleanerRenderEntity.setDirection(vacuumCleanerModel.getDirection());
    }
}
