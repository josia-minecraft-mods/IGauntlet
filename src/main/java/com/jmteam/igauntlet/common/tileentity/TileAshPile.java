package com.jmteam.igauntlet.common.tileentity;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;


public class TileAshPile extends TileEntity implements ITickable {


    public static String entity = "";
    private int tick = 0;


    public void setEntity(EntityLiving entity) {
       this.entity = EntityList.getKey(entity).toString();
    }

    public EntityLiving getEntity() {
        return  (EntityLiving) EntityList.createEntityByIDFromName(new ResourceLocation(entity), getWorld());
    }

    public void summonEntity() {
        EntityLiving e = (EntityLiving) getEntity();
        e.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 90,90);
        getWorld().spawnEntity(e);
        getWorld().setBlockState(getPos(), Blocks.AIR.getDefaultState());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        entity = compound.getString("entity_name");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("entity_name", entity);
        return compound;
    }

    @Override
    public void update() {
        tick++;
        if (tick == 1200)
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }
}