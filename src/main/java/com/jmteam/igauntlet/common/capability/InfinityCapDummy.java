package com.jmteam.igauntlet.common.capability;

import net.minecraft.nbt.CompoundNBT;

public class InfinityCapDummy implements IInfinityCap {

    @Override
    public void update() {

    }

    @Override
    public void sync() {

    }

    @Override
    public void setSnapTimeout(long snapTimeout) {

    }

    @Override
    public long getSnapTimeout() {
        return 0;
    }

    @Override
    public boolean canSnap() {
        return false;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return new CompoundNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }
}
