package com.jmteam.igauntlet.common.gems;

import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

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
