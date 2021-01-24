package com.jmteam.igauntlet.util.gauntlet.gems;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.util.gauntlet.GemHelper;
import com.jmteam.igauntlet.util.helpers.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemReality extends GemBase {

    @Override
    public void handleRightClick(PlayerEntity player) {
        super.handleRightClick(player);
    }

    @Override
    public void handleUsedClick(ItemUseContext context) {

        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getPos();
        World world = context.getWorld();

        if (player.isCrouching()) {
            Block b = player.world.getBlockState(pos).getBlock();

            if (b instanceof SandBlock) {
                for (BlockPos p : WorldHelper.getSameBlocksFromPosRanged(world, pos, 20)) {
                    WorldHelper.setBlockState(world, InfinityBlocks.QUICK_SAND.get().getDefaultState(), p);
                }
            }
        }
    }

    @Override
    public void handleLeftClick(PlayerEntity player) {
        super.handleLeftClick(player);
    }

    @Override
    public void handleEntityInteraction(PlayerEntity player, Entity entity) {
        super.handleEntityInteraction(player, entity);
    }

    @Override
    public void handleItemAction(PlayerEntity player) {
        super.handleItemAction(player);
    }
}
