package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.models.VacuumCleanerModel;

import java.util.Random;

/**
 * @author Rodrigo Andrade
 */
public class RandomMovemenVacuumCleanertCommand extends VacuumCleanerCommand {

    private Random random;

    public RandomMovemenVacuumCleanertCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
        random = new Random();
    }

    @Override
    public void execute() {
        int randomMovement = random.nextInt(4);

        switch (randomMovement) {
            case VacuumCleanerRenderEntity.DIRECTION_LEFT:
                new MoveToLeftCommand(vacuumCleanerModel, vacuumCleanerRenderEntity).execute();
                break;
            case VacuumCleanerRenderEntity.DIRECTION_RIGHT:
                new MoveToRightCommand(vacuumCleanerModel, vacuumCleanerRenderEntity).execute();
                break;
            case VacuumCleanerRenderEntity.DIRECTION_UPPER:
                new MoveToUpCommand(vacuumCleanerModel, vacuumCleanerRenderEntity).execute();
                break;
            case VacuumCleanerRenderEntity.DIRECTION_DOWN:
                new MoveToDownCommand(vacuumCleanerModel, vacuumCleanerRenderEntity).execute();
                break;
        }
    }
}
