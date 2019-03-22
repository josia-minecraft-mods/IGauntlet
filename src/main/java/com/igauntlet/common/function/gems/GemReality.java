package com.igauntlet.common.function.gems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
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
}