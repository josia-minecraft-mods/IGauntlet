package com.jmteam.igauntlet.common.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;

import java.util.ArrayList;
import java.util.List;


public class TileAshPile extends TileEntity implements ITickable {


    public static final List<Entity> ENTITY = new ArrayList<Entity>();
    private int tick = 0;


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

    @Override
    public void update() {
        tick++;
        if(tick == 1200)
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }
}