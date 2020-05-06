package com.jmteam.igauntlet.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import java.util.List;

public class EntityHelper {

    public static void AttackBySource(Entity e, DamageSource source, Float damage) {
        e.attackEntityFrom(source, damage);
    }

    public static List<Entity> filterSnap(List<Entity> entities) {

        for (Entity e : entities) {

            if (e instanceof EntityLiving && e instanceof EntityPlayer) {
                entities.remove(e);
            }
        }

        return entities;
    }
}
