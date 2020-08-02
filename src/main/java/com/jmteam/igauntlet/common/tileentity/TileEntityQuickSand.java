package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.util.InfinityConfig;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityQuickSand extends TileEntity implements ITickable {

    public int timer = 0;
    public boolean placed = false;

    public void setManuallyPlaced() {
        this.placed = true;
    }

    @Override
    public void update() {

        if (!placed && !world.isRemote && getWorld().getWorldTime() % 20 == 0) {

            if (timer >= InfinityConfig.Gauntlet.RealityStone.SandTimer) {
                timer = 0;
                world.setBlockState(pos, Blocks.SAND.getDefaultState());
            }
            timer++;
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.timer = compound.getInteger("timer");
        this.placed = compound.getBoolean("placed");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("timer", timer);
        compound.setBoolean("placed", placed);

        return compound;
    }
}
