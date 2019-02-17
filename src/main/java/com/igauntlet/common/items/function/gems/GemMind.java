package com.igauntlet.common.items.function.gems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class GemMind {

    public static void Attack(EntityPlayer player) {
        for(EntityLiving e : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(50,50,50))) {
           e.setNoAI(true);
        }
    }
}
