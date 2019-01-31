package com.common.tileentity;

import com.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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

    public void RemoveStone(EntityPlayer player) {
        if(stone == 1) {
           // world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5));
            player.addItemStackToInventory(new ItemStack(ModItems.SPACESTONE));
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
