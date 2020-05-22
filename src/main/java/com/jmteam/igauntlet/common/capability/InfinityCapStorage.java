package com.jmteam.igauntlet.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class InfinityCapStorage implements Capability.IStorage<IInfinityCap> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IInfinityCap> capability, IInfinityCap instance, Direction side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IInfinityCap> capability, IInfinityCap instance, Direction side, INBT nbt) {
        instance.deserializeNBT(nbt instanceof CompoundNBT ? (CompoundNBT) nbt : new CompoundNBT());
    }
}
