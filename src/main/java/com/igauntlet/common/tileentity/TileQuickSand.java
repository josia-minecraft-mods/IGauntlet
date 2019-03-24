package com.igauntlet.common.tileentity;

import com.igauntlet.init.InfinityConfig;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileQuickSand extends TileEntity implements ITickable {
    public int timer;

    @Override
    public void update() {
        if (timer < InfinityConfig.Gauntlet.RealityStone.SandTimer) {
            timer++;
        }

        if (timer == InfinityConfig.Gauntlet.RealityStone.SandTimer) {
            world.setBlockState(pos, Blocks.SAND.getDefaultState());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.timer = compound.getInteger("timer");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("timer", this.timer);
        return compound;
    }
}
