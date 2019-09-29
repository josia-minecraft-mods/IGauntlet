package com.jmteam.igauntlet.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import java.util.ArrayList;
import java.util.List;

public class EntityHelper {
    public static boolean EntityIsFriend(Entity e) {
        return e.getEntityData().getBoolean("isfriend");
    }

    public static void setFriend(Entity e, boolean friend) {
        e.getEntityData().setBoolean("isfriend", friend);
    }

    public static void AttackBySource(Entity e, DamageSource source, Float damage) {
        e.attackEntityFrom(source, damage);
    }

    public static List<Entity> filterSnap(List<Entity> entities) {
        List<Entity> entities1 = new ArrayList<>();

        for (Entity e : entities) {

            if (e instanceof EntityLiving) {
                entities1.add(e);
            }

            if (e instanceof EntityPlayer) {
                entities1.add(e);
            }
        }
        return entities;
    }
}
