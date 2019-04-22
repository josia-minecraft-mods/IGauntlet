package com.jmteam.igauntlet.common.function.gems;

import com.jmteam.igauntlet.util.helpers.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;

public class GemMind {

    public static void Attack(EntityPlayer player, EntityLiving entityLiving) {
        if (!entityLiving.world.isRemote) {
            for (EntityMob e : player.world.getEntitiesWithinAABB(EntityMob.class, player.getEntityBoundingBox().grow(20, 20, 20))) {
                if (!entityLiving.isDead && entityLiving.attackable())
                    if(e == entityLiving) return;
                    e.setAttackTarget(entityLiving);
            }
        }
    }


    public static void MakeFriendly(Entity e) {
        if (e != null) {
            if (!EntityHelper.EntityIsFriend(e))
                EntityHelper.setFriend(e, true);
            else
                EntityHelper.setFriend(e, false);
        }
    }


}
