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
            if (!level.isClientSide() && level.getGameTime() % 20 == 0) {
                if (timer >= 60)  {
                    level.setBlockAndUpdate(worldPosition, Blocks.SAND.defaultBlockState());
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
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        placed = compound.getBoolean("placed");
        timer = compound.getInt(InfinityNBT.TIMER);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.putBoolean("placed", placed);
        compound.putInt(InfinityNBT.TIMER, timer);

        return compound;
    }
}
