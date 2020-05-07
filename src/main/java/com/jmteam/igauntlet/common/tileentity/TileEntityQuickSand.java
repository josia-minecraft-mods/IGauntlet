package com.jmteam.igauntlet.common.tileentity;

import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;

public class TileEntityQuickSand extends InfinityTileEntityBase implements ITickableTileEntity {

    public boolean placed = false;
    public long created = System.currentTimeMillis();

    public TileEntityQuickSand() {
        super();
    }

    @Override
    public void tick() {

        if(!placed) {

            // TODO Replace with a config value for max sand time
            if(!world.isRemote && created != 0 && ((System.currentTimeMillis() - created) / 1000L)  >= 20 && getWorld().getGameTime() % 20 == 0) {
                world.setBlockState(pos, Blocks.SAND.getDefaultState());
            }
        }
    }

    public void setPlaced() {
        placed = true;
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        placed = compound.getBoolean("placed");
        created = compound.getLong("created");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putBoolean("placed", placed);
        compound.putLong("created", created);

        return compound;
    }
}
