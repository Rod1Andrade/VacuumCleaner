package com.github.rod1andrade.commands.trash;

import com.github.rod1andrade.commands.Command;
import com.github.rod1andrade.entities.TrashRenderEntity;
import com.github.rod1andrade.models.TrashModel;

/**
 * @author Rodrigo Andrade
 */
public abstract class TrashCommand implements Command {
    protected TrashModel trashModel;
    protected TrashRenderEntity trashRenderEntity;

    public TrashCommand(TrashModel trashModel) {
        this.trashModel = trashModel;
    }

    public TrashCommand(TrashRenderEntity trashRenderEntity) {
        this.trashRenderEntity = trashRenderEntity;
    }

    public TrashCommand(TrashModel trashModel, TrashRenderEntity trashRenderEntity) {
        this.trashModel = trashModel;
        this.trashRenderEntity = trashRenderEntity;
    }
}
