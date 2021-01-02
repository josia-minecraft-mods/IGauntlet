package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinityNBT;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class TileEntityTesseract extends InfinityTileEntityBase {

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

            has_stone = false;
            ItemEntity itemEntity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.2f, pos.getZ() + 0.5, new ItemStack(InfinityItems.SPACE_STONE.get(), 1));
            world.addEntity(itemEntity);
        }
    }

    public void setHas_stone(boolean has_stone) {
        this.has_stone = has_stone;
    }

    public boolean hasStone() {
        return has_stone;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        has_stone = compound.getBoolean(InfinityNBT.HAS_STONE);

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);

        compound.putBoolean(InfinityNBT.HAS_STONE, has_stone);

        return compound;
    }
}
