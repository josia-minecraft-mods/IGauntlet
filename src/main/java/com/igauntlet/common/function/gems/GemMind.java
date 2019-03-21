package com.igauntlet.common.function.gems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class GemMind {

    public static void Attack(EntityPlayer player, EntityLiving entityLiving) {
        if (!entityLiving.world.isRemote) {
            for (EntityMob e : player.world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(20, 20, 20))) {
                e.setAttackTarget(entityLiving);
            }
        }
    }


    public static void MakeFriendly(EntityLivingBase e) {
        if (!e.world.isRemote) {
            if (!e.getEntityData().getBoolean("isfriend"))
                e.getEntityData().setBoolean("isfriend", true);
            else
                e.getEntityData().setBoolean("isfriend", false);
        }
    }


}
