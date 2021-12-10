package com.github.rod1andrade.commands.vacuumcleaner;

import com.github.rod1andrade.commands.trash.CollectedTrashCommand;
import com.github.rod1andrade.entities.TrashRenderEntity;
import com.github.rod1andrade.entities.VacuumCleanerRenderEntity;
import com.github.rod1andrade.models.TrashModel;
import com.github.rod1andrade.models.VacuumCleanerModel;

import java.util.List;

/**
 * @author Rodrigo Andrade
 */
public class CollectTrashVacuumCleanerCommand extends VacuumCleanerCommand {

    List<TrashModel> trashModelList;
    List<TrashRenderEntity> trashRenderEntityList;

    public CollectTrashVacuumCleanerCommand(
            VacuumCleanerModel vacuumCleanerModel, VacuumCleanerRenderEntity vacuumCleanerRenderEntity,
            List<TrashModel> trashModelList,
            List<TrashRenderEntity> trashRenderEntityList
    ) {
        super(vacuumCleanerModel, vacuumCleanerRenderEntity);
        this.trashModelList = trashModelList;
        this.trashRenderEntityList = trashRenderEntityList;
    }

    @Override
    public void execute() {
        for(int i = 0; i < trashModelList.size(); i++) {
           if(
                   !trashModelList.get(i).isCollected() &&
                   vacuumCleanerRenderEntity.getBoxCollision().hasOverlap(trashRenderEntityList.get(i).getBoxCollision())
           ) {
              vacuumCleanerModel.collect(trashModelList.get(i));
              new CollectedTrashCommand(trashModelList.get(i), trashRenderEntityList.get(i)).execute();
              vacuumCleanerModel.setHasCollision(true);
           }
        }
    }
}
