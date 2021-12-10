package com.github.rod1andrade.commands.simulation;

import com.github.rod1andrade.commands.trash.PositinateTrashCommand;
import com.github.rod1andrade.entities.RenderEntity;
import com.github.rod1andrade.entities.TrashRenderEntity;
import com.github.rod1andrade.model.TrashModel;
import com.github.rod1andrade.util.Config;

import java.util.ArrayList;
import java.util.Random;

/**
 * Inicializa aleatoriamente sujeiras com base nos seguintes elementos:
 * <p>
 * 1) Elementos ja posicionados (para nao renderizar sujeira nesses locais)
 * 2) Quantidade de sujeiras a serem renderizadas
 * 3) Posicoes limites: minima, maxima em ambos os eixos.
 * <p>
 * execute () -> posiciona os elementos com base na aleatoriedade gerada.
 *
 * @author Rodrigo Andrade
 */
public class StartRandomTrashsCommand extends SimulationCommand {

    private int quantityPositionad;
    private RenderEntity[] renderEntitiesPositineds;

    private Random random = new Random();

    public StartRandomTrashsCommand(RenderEntity[] renderEntitiesPositineds, int quantity, int xMin, int yMin, int xBound, int yBound) {
        this.renderEntitiesPositineds = renderEntitiesPositineds;
        trashModels = new ArrayList<>();
        trashRenderEntities = new ArrayList<>();

        while (quantityPositionad < quantity) {
            boolean flag = false;

            int posX = random.nextInt(xMin + 64, xBound - 64);
            int posY = random.nextInt(yMin + 64, yBound - 64);

            TrashModel trashModel = new TrashModel(quantityPositionad, posX, posY, false);
            TrashRenderEntity trashRenderEntity = new TrashRenderEntity(posX, posY, 16, 16, Config.getInstance().isDebugMode());

            // Se esta colidindo com alguma entidade renderizada ja posicionada
            for (RenderEntity renderEntitiesPositined : renderEntitiesPositineds) {
                if (trashRenderEntity.getBoxCollision().hasCollision(renderEntitiesPositined.getBoxCollision())) {
                    flag = true;
                    break;
                }
            }

            // Se esta colidindo com algum lixo ja posicionado
            for (TrashRenderEntity trashRenderEntityPositined : trashRenderEntities) {
                if (trashRenderEntity.getBoxCollision().hasCollision(trashRenderEntityPositined.getBoxCollision())) {
                    flag = true;
                    break;
                }
            }

            if (flag) continue;

            trashModels.add(trashModel);
            trashRenderEntities.add(trashRenderEntity);
            quantityPositionad++;
        }
    }

    @Override
    public void execute() {
        for (int i = 0; i < quantityPositionad; i++) {
            new PositinateTrashCommand(trashModels.get(i), trashRenderEntities.get(i)).execute();
        }
    }
}
