package com.igauntlet.common.tileentity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;


public class TileAshPile extends TileEntity {


    public static final List<EntityLiving> ENTITY = new ArrayList<EntityLiving>();


    public void setEntity(EntityLiving entity) {
        ENTITY.add(entity);
    }

    public EntityLivingBase getEntity() {
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
