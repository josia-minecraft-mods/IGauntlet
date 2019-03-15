package com.igauntlet.common.items.function.gems;


import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class GemMind {

    public static void Attack(EntityPlayer player) {
        if (!player.world.isRemote) {
        }
    }

    public static void MakeFriendly(EntityPlayer player) {
            for (EntityLiving e : player.world.getEntitiesWithinAABB(EntityLiving.class, player.getEntityBoundingBox().grow(20, 20, 20))) {
                if(e.getEntityData().getBoolean("friendly")) {
                    System.out.println(e.getEntityData().getBoolean("friendly"));
                }
                e.getEntityData().setBoolean("friendly", true);
        }


    }
}
