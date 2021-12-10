package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.commands.Command;
import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.models.VacuumCleanerModel;

/**
 * @author Rorigo Andrade
 */
public abstract class VacuumCleanerCommand implements Command {

    protected VacuumCleanerModel vacuumCleanerModel;
    protected VacuumCleanerRenderEntity vacuumCleanerRenderEntity;

    public VacuumCleanerCommand(VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity) {
        this.vacuumCleanerModel = vacuumCleanerModel;
        this.vacuumCleanerRenderEntity = vacuumCleanerRenderEntity;
    }
}
