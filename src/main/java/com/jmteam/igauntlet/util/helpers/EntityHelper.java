package com.jmteam.igauntlet.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityHelper {

    public static Entity createEntityFromNBT(CompoundNBT compoundNBT, World world) {
        Entity e = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(compoundNBT.getString("id"))).create(world);
        e.deserializeNBT(compoundNBT);

        return e;
    }
    public static Entity createEntityFromID(String id, World world) {
        return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id)).create(world);
    }

    public static String getEntityID(Entity e) {
        return e.getEntityString();
    }
}
