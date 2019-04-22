package com.jmteam.igauntlet.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

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
}
