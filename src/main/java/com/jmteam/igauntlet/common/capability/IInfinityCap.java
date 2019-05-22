package com.jmteam.igauntlet.common.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IInfinityCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();
}
