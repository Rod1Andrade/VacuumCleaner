package com.github.rod1andrade.commands.trash;

import com.github.rod1andrade.entities.TrashRenderEntity;
import com.github.rod1andrade.models.TrashModel;

public class PositinateTrashCommand extends TrashCommand{
    public PositinateTrashCommand(TrashModel trashModel) {
        super(trashModel);
    }

    public PositinateTrashCommand(TrashRenderEntity trashRenderEntity) {
        super(trashRenderEntity);
    }

    public PositinateTrashCommand(TrashModel trashModel, TrashRenderEntity trashRenderEntity) {
        super(trashModel, trashRenderEntity);
    }

    @Override
    public void execute() {
        if(trashModel == null || trashRenderEntity == null) return;

        trashRenderEntity.setPosition(trashModel.getPosX(), trashModel.getPosY());
        trashModel.setCollected(false);
        trashRenderEntity.setVisible(true);
    }
}
