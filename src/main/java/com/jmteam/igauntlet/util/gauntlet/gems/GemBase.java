package com.jmteam.igauntlet.util.gauntlet.gems;

import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class GemBase {

    public void handleRightClick(PlayerEntity player) {
        GemHelper.notSetupMessage(player);
    }

    public void handleLeftClick(PlayerEntity player) {
        GemHelper.notSetupMessage(player);
    }

    public void handleEntityInteraction(PlayerEntity player, Entity entity) {
        GemHelper.notSetupMessage(player);
    }

    public void handleItemAction(PlayerEntity player) {
        GemHelper.notSetupMessage(player);
    }
}
