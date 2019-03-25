package com.igauntlet.common.function.gems;

import com.igauntlet.init.InfinityBlocks;
import com.igauntlet.init.InfinityConfig;
import net.minecraft.block.BlockSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class GemReality {

    public static void ShootFireBall(EntityPlayer player) {
        if (!player.world.isRemote) {
            Vec3d v3 = player.getLook(1);
            EntitySmallFireball smallfireball = new EntitySmallFireball(player.world, player.posX, player.posY + player.eyeHeight, player.posZ, v3.x, v3.y, v3.z);
            smallfireball.shootingEntity = player;
            player.world.spawnEntity(smallfireball);
        }
    }

    public static void SurvivalFlight(EntityPlayer player, boolean selected) {

        if (!player.isCreative()) {
            if (selected) {
                player.capabilities.allowFlying = true;
            } else {
                player.capabilities.allowFlying = false;
            }
            player.sendPlayerAbilities();
        }
    }

    public static void QuickSand(EntityPlayer player) {
        int r = InfinityConfig.Gauntlet.RealityStone.SandRange;
        for (BlockPos b : BlockPos.getAllInBox((int) player.posX - r, (int) player.posY - 10, (int) player.posZ - r, (int) player.posX + r, (int) player.posY + r, (int) player.posZ + r)) {
            if (player.world.getBlockState(b).getBlock() instanceof BlockSand) {
                player.world.setBlockState(b, InfinityBlocks.QUICK_SAND.getDefaultState());
            }
        }
    }
}