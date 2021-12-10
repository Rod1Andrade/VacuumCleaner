package com.github.rod1andrade.commands;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

/**
 * @author Rodrigo Andrade
 */
public class MoveToOppositeDirectionCommand extends VacuumCleanerCommand {

    private MoveToRightCommand moveToRightCommand;
    private MoveToLeftCommand moveToLeftCommand;

    public MoveToOppositeDirectionCommand(VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity) {
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
