package com.github.rod1andrade.commands.trash;

import com.github.rod1andrade.entities.TrashRenderEntity;
import com.github.rod1andrade.models.TrashModel;

/**
 * @author Rodrigo Andrade
 */
public class CollectedTrashCommand extends TrashCommand {
    public CollectedTrashCommand(TrashModel trashModel) {
        super(trashModel);
    }

    public CollectedTrashCommand(TrashRenderEntity trashRenderEntity) {
        super(trashRenderEntity);
    }

    public CollectedTrashCommand(TrashModel trashModel, TrashRenderEntity trashRenderEntity) {
        super(trashModel, trashRenderEntity);
    }

    @Override
    public void execute() {
        if (trashModel == null || trashRenderEntity == null) return;

        trashModel.setCollected(true);
        trashRenderEntity.setVisible(false);
    }
}
