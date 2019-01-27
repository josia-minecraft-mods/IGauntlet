package com.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;


public class TileAshPile extends TileEntity {

    private int entity;
    private String player;

    public void setEntity(int entity) { this.entity = entity; this.markDirty(); }
    public int getEntity() { return this.entity; }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        if(nbt == null) nbt = new NBTTagCompound();
        nbt.setInteger("entity", this.entity);
        return super.writeToNBT(nbt);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if(nbt != null && nbt.hasKey("entity"))
            this.entity = nbt.getInteger("entity");

        super.readFromNBT(nbt);
    }
}
