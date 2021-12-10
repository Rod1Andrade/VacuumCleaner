package com.github.rod1andrade.commands;

import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.model.VacuumCleanerModel;

/**
 * @author Rorigo Andrade
 */
public abstract class VacuumCleanerCommand {

    protected VacuumCleanerModel vacuumCleanerModel;
    protected VacuumCleanerRenderEntity vacuumCleanerRenderEntity;

    public VacuumCleanerCommand(VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity) {
        this.vacuumCleanerModel = vacuumCleanerModel;
        this.vacuumCleanerRenderEntity = vacuumCleanerRenderEntity;
    }

    public abstract void execute();
}
