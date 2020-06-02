package com.jmteam.igauntlet.util.gauntlet.gems;

import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class GemBase {

    public void handleRightClick(PlayerEntity player) {
        // TODO find a way to see how we can stop it from executing together with usedclick
        GemHelper.notSetupMessage(player);
    }

    public void handleUsedClick(ItemUseContext context) {
        GemHelper.notSetupMessage(context.getPlayer());
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
