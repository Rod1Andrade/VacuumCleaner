package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.models.VacuumCleanerModel;

/**
 * @author Rodrigo Andrade
 */
public class MoveToOppositeDirectionVacuumCleanerCommand extends VacuumCleanerCommand {

    private MoveToRightCommand moveToRightCommand;
    private MoveToLeftCommand moveToLeftCommand;

    public MoveToOppositeDirectionVacuumCleanerCommand(VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
        moveToRightCommand = new MoveToRightCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
        moveToLeftCommand = new MoveToLeftCommand(vacuumCleanerModel, vacuumCleanerRenderEntity);
    }

    @Override
    public void execute() {
        switch (vacuumCleanerModel.getDirection()) {
            case VacuumCleanerRenderEntity.DIRECTION_LEFT:
                moveToRightCommand.execute();
                break;
            case VacuumCleanerRenderEntity.DIRECTION_RIGHT:
                moveToLeftCommand.execute();
                break;
            case VacuumCleanerRenderEntity.DIRECTION_UPPER:
                new MoveToDownCommand(vacuumCleanerModel, vacuumCleanerRenderEntity).execute();
                break;
            case VacuumCleanerRenderEntity.DIRECTION_DOWN:
                new MoveToUpCommand(vacuumCleanerModel, vacuumCleanerRenderEntity).execute();
                break;
        }
    }
}
