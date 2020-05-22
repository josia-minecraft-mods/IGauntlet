package com.jmteam.igauntlet.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IInfinityCap extends INBTSerializable<CompoundNBT> {

    void update();
    void sync();


    void setSnapTimeout(long snapTimeout);
    long getSnapTimeout();
    boolean canSnap();
}
