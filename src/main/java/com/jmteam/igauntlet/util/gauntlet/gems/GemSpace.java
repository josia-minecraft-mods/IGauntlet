package com.jmteam.igauntlet.util.gauntlet.gems;

import com.jmteam.igauntlet.util.helpers.WorldUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GemSpace extends GemBase {

    @Override
    public void handleRightClick(PlayerEntity player) {
        super.handleRightClick(player);
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
        // Drifting
        if (!player.world.isRemote) {
            World world = player.world;
            Random r = world.rand;
            int xR = r.nextBoolean() ? r.nextInt(230) : -r.nextInt(230);
            int zR = r.nextBoolean() ? r.nextInt(230) : -r.nextInt(230);

            // TODO Config Values
            BlockPos calculcatedPosition = new BlockPos(player.getPosX() + xR, player.getPosY(), player.getPosZ() + zR);
            int y = WorldUtil.getTopBlockSolidOrWaterY(player.world, calculcatedPosition);
            player.setPositionAndUpdate(calculcatedPosition.getX(), y, calculcatedPosition.getZ());
        }
    }
}
