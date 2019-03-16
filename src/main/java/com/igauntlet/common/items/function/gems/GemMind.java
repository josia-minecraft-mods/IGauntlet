package com.igauntlet.common.items.function.gems;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class GemMind {

    public static void Attack(EntityPlayer player) {
        if (!player.world.isRemote) {
        }
    }

    public static void MakeFriendly(EntityLivingBase e) {

        if (!e.getEntityData().getBoolean("isfriend"))
            e.getEntityData().setBoolean("isfriend", true);
        else
            e.getEntityData().setBoolean("isfriend", false);

    }

}
