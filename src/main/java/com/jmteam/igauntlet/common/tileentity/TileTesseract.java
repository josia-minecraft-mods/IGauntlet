package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.items.InfinityItems;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileTesseract extends TileEntity {

    private int stone = 1;

    public boolean AddStone() {
        if (stone == 0) {
            stone = 1;
            return true;
        }
        return false;
    }

    public void RemoveStone(EntityPlayer player) {
        if (stone == 1) {
            world.spawnEntity(new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(InfinityItems.space_stone)));
            stone = 0; world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    public int GetStone() {
        return this.stone;
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
