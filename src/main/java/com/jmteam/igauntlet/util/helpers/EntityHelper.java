package com.jmteam.igauntlet.util.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityHelper {

    public static Entity createEntityFromNBT(CompoundNBT compoundNBT, World world) {
        Entity entity = null;

        if (compoundNBT != null && world != null) {
            entity = createEntityFromID(compoundNBT.getString("id"), world);

            if (entity != null) {
                entity.deserializeNBT(compoundNBT);
            }
        }

        return entity;
    }

    public static CompoundNBT getEntityCompound(Entity entity) {
        return entity != null ? entity.serializeNBT() : null;
    }

    public static Entity createEntityFromID(String id, World world) {
        Entity entity = null;

        if (!id.isEmpty() && world != null)  {
            entity =  ForgeRegistries.ENTITIES.getValue(new ResourceLocation(id)).create(world);
        }

        return entity;
    }

    public static String getEntityID(Entity e) {
        return e.getEntityString();
    }
}
