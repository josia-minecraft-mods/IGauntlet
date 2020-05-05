package com.jmteam.igauntlet.common.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapInfinityStorage implements Capability.IStorage<IInfinityCap> {

    @CapabilityInject(IInfinityCap.class)
    public static Capability<CapabilityInfinity> CAPABILITY = null;

    @Override
    public NBTBase writeNBT(Capability<IInfinityCap> capability, IInfinityCap instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IInfinityCap> capability, IInfinityCap instance, EnumFacing side, NBTBase nbt) {
        instance.deserializeNBT(nbt instanceof NBTTagCompound ? (NBTTagCompound) nbt : new NBTTagCompound());
    }

    public static class InfinityCapProvider implements ICapabilitySerializable<NBTTagCompound> {

        CapabilityInfinity cap;

        public InfinityCapProvider(EntityPlayer player) {
            this.cap = new CapabilityInfinity(player);
        }

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return capability == CAPABILITY;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            return capability == CAPABILITY ? (T) cap : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return (NBTTagCompound) CapInfinityStorage.CAPABILITY.getStorage().writeNBT(CapInfinityStorage.CAPABILITY, cap, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            CapInfinityStorage.CAPABILITY.getStorage().readNBT(CapInfinityStorage.CAPABILITY, cap, null, nbt);
        }
    }
}
