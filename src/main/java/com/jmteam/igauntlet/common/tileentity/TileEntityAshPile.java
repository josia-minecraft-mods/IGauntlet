package com.jmteam.igauntlet.common.tileentity;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityAshPile extends InfinityTileEntityBase implements ITickableTileEntity {

    public String entity = "";
    public long created = System.currentTimeMillis();

    public TileEntityAshPile() {
        super();
    }

    @Override
    public void tick() {

    }

    public void setEntity(LivingEntity entity) {
        CompoundNBT nbt = entity.serializeNBT();
        nbt.putString("registry_location", ForgeRegistries.ENTITIES.getKey(entity.getType()).toString());
        this.entity = nbt.toString();
    }

    public LivingEntity getEntity() {
        try {
            CompoundNBT compoundNBT = JsonToNBT.getTagFromJson(this.entity);
            Entity e =  ForgeRegistries.ENTITIES.getValue(new ResourceLocation(compoundNBT.getString("registry_location"))).create(world);
            e.deserializeNBT(compoundNBT);
            world.addEntity(e);
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
