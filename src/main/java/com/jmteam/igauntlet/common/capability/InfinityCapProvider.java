package com.jmteam.igauntlet.common.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InfinityCapProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IInfinityCap.class)
    public static Capability<IInfinityCap> CAPABILITY = null;
    private LazyOptional<IInfinityCap> instance = LazyOptional.of(CAPABILITY::getDefaultInstance);
    protected static InfinityCapDummy capDummy = new InfinityCapDummy();

    public InfinityCapProvider() {
    }

    public InfinityCapProvider(PlayerEntity playerEntity) {
        this.instance = LazyOptional.of(() -> new CapabilityInfinity(playerEntity));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) CAPABILITY.getStorage().writeNBT(CAPABILITY, instance.orElseThrow(IllegalArgumentException::new), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        CAPABILITY.getStorage().readNBT(CAPABILITY, instance.orElseThrow(IllegalArgumentException::new), null, nbt);
    }
}
