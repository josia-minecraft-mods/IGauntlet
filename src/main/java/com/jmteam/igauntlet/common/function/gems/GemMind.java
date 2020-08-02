package com.jmteam.igauntlet.common.function.gems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class GemMind {

    public static void attack(EntityPlayer player, EntityLiving entityLiving) {
        if (!entityLiving.world.isRemote) {
            for (EntityMob e : player.world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(20, 20, 20))) {
                if (!entityLiving.isDead && entityLiving.attackable())
                    if (e == entityLiving) return;
                e.setAttackTarget(entityLiving);
            }
        }
    }
}
