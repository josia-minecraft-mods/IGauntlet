package com.jmteam.igauntlet.common.function.gems;

import com.jmteam.igauntlet.common.blocks.InfinityBlocks;
import com.jmteam.igauntlet.util.InfinityConfig;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;

public class GemReality {

    public static void makeBubbles(EntityPlayer player) {
        if(!player.world.isRemote) {
            for (Entity e : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(20, 20, 20))) {
                if (e instanceof IProjectile) {
                    BlockPos pos = new BlockPos(e.posX, e.posY, e.posZ);
                    e.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, e.posX, e.posY, e.posZ, 1.0, 1.0, 1.0);
                    e.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, e.posX - 0.1, e.posY, e.posZ - 0.1, 1.0, 1.0, 1.0);
                    e.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, e.posX + 0.1, e.posY, e.posZ + 0.1, 1.0, 1.0, 1.0);
                    e.setDead();
                }
            }
        }
    }

    public static void QuickSand(EntityPlayer player) {
        int r = InfinityConfig.Gauntlet.RealityStone.SandRange;
        for (BlockPos b : BlockPos.getAllInBox((int) player.posX - r, (int) player.posY - 10, (int) player.posZ - r, (int) player.posX + r, (int) player.posY + r, (int) player.posZ + r)) {
            if (player.world.getBlockState(b).getBlock() instanceof BlockSand) {
                player.world.setBlockState(b, InfinityBlocks.quick_sand.getDefaultState());
            }
        }
    }
}