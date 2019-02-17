package com.igauntlet.common.items.function.gems;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class GemMind {

    public static void Attack(EntityPlayer player) {
        for(EntityLiving e : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(50,50,50))) {
            BlockPos pos1 = new BlockPos(e.posX, e.posY, e.posZ);
           e.setPartying(pos1, true);
        }
    }
}
