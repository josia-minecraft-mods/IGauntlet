package com.jmteam.igauntlet.common.tileentity;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;


public class TileEntityAshPile extends TileEntity implements ITickable {

    private String entity = "";
    private int timer = 0;

    public void setEntity(EntityLiving entity) {
        NBTTagCompound tagCompound = entity.serializeNBT();
        tagCompound.setFloat("Health", entity.getMaxHealth());

        this.entity = tagCompound.toString();
    }

    private EntityLiving getEntity() {
        try {
            return (EntityLiving) EntityList.createEntityFromNBT(JsonToNBT.getTagFromJson(entity), getWorld());
        } catch (NBTException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void summonEntity() {
        if (!getWorld().isRemote) {
            EntityLiving e = getEntity();

            if (e != null) {
                e.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 90, 0);
                world.spawnEntity(e);
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.entity = compound.getString("entity_data");
        this.timer = compound.getInteger("timer");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setString("entity_data", entity);
        compound.setInteger("timer", timer);

        return compound;
    }

    @Override
    public void update() {
        if (!world.isRemote && getWorld().getWorldTime() % 20 == 0) {
           if(timer >= 60) {
               timer = 0;
               world.setBlockState(pos, Blocks.AIR.getDefaultState());
           }
           timer++;
        }
    }
}