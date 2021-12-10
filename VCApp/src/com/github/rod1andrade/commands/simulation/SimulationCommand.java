package com.github.rod1andrade.commands.simulation;

import com.github.rod1andrade.commands.Command;
import com.github.rod1andrade.entities.TrashRenderEntity;
import com.github.rod1andrade.model.TrashModel;

import java.util.List;

/**
 * @author Rodrigo Andrade
 */
public abstract class SimulationCommand implements Command {
    protected List<TrashModel> trashModels;
    protected List<TrashRenderEntity> trashRenderEntities;

    public List<TrashModel> getTrashModels() {
        return trashModels;
    }

    public List<TrashRenderEntity> getTrashRenderEntities() {
        return trashRenderEntities;
    }
}
