package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.init.InfinityItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEnityTesseract extends TileEntity {

    public boolean has_stone = true;

    public boolean addStone() {
        if (!has_stone) {
            has_stone = true;
            return true;
        }
        return false;
    }

    public void removeStone() {
        if (has_stone) {
            world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(InfinityItems.space_stone)));
            has_stone = false;
        }
    }

    public boolean GetStone() {
        return has_stone;
    }

    public void setHas_stone(boolean has_stone) {
        this.has_stone = has_stone;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("has_stone", this.has_stone);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.has_stone = compound.getBoolean("has_stone");
    }


}
