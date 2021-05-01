package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.init.InfinityNBT;
import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class TileEntityQuickSand extends InfinityTileEntityBase implements ITickableTileEntity {

    public boolean placed = false;
    private int timer = 0;

    public TileEntityQuickSand() {
        super(InfinityTileEntities.QUICK_SAND.get());
    }

    @Override
    public void tick() {

        if (!placed) {

            // TODO Replace with a config value for max sand time
            if (!world.isRemote() && getWorld().getGameTime() % 20 == 0) {
                if (timer >= 60)  {
                    world.setBlockState(pos, Blocks.SAND.getDefaultState());
                    timer = 0;
                }
                timer++;
            }
        }
    }

    public void setPlaced() {
        placed = true;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        placed = compound.getBoolean("placed");
        timer = compound.getInt(InfinityNBT.TIMER);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putBoolean("placed", placed);
        compound.putInt(InfinityNBT.TIMER, timer);

        return compound;
    }
}
