package com.common.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileTesseract extends TileEntity {

    private int stone = 0;

    public boolean AddStone() {
        if(stone == 0) {
            stone = 1;
            return true;
        }
        return false;
    }

    public void RemoveStone() {
        if(stone == 1) {
            world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
            stone = 0;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("stone", this.stone);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.stone = compound.getInteger("stone");
    }
}
