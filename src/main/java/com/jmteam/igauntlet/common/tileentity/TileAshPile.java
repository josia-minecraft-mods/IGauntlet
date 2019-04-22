package com.jmteam.igauntlet.common.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;


public class TileAshPile extends TileEntity {


    public static final List<Entity> ENTITY = new ArrayList<Entity>();


    public void setEntity(Entity entity) {
        ENTITY.add(entity);
    }

    public Entity getEntity() {
        return this.ENTITY.get(0);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if (nbt == null) nbt = new NBTTagCompound();
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (nbt != null && nbt.hasKey("entity"))
            super.readFromNBT(nbt);
    }
}