package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.util.InfinityConfig;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityQuickSand extends TileEntity implements ITickable {

    public long created = System.currentTimeMillis();
    public boolean placed = false;

    public void setManuallyPlaced() {
        this.placed = true;
    }

    @Override
    public void update() {

        if (!placed) {
            if (created != 0 && ((System.currentTimeMillis() - created) / 1000L) >= InfinityConfig.Gauntlet.RealityStone.SandTimer && getWorld().getWorldTime() % 20 == 0 && !placed) {
                world.setBlockState(pos, Blocks.SAND.getDefaultState());
            }
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.created = compound.getLong("created");
        this.placed = compound.getBoolean("placed");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setLong("created", created);
        compound.setBoolean("placed", placed);

        return compound;
    }
}
