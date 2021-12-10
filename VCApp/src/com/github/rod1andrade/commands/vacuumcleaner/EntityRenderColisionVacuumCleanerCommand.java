package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.entities.RenderEntity;
import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.models.VacuumCleanerModel;

public class EntityRenderColisionVacuumCleanerCommand extends VacuumCleanerCommand {
    private RenderEntity[] renderEntities;

    public EntityRenderColisionVacuumCleanerCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity,
            RenderEntity[] renderEntities
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
        this.renderEntities = renderEntities;

    }

    @Override
    public void execute() {
        for (int i = 0; i < renderEntities.length; i++) {
            if (renderEntities[i] != null && vacuumCleanerRenderEntity.getBoxCollision().hasCollision(renderEntities[i].getBoxCollision())) {
                vacuumCleanerModel.setHasCollision(true);
            }
        }
    }
}
