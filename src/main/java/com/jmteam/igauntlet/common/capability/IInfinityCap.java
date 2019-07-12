package com.jmteam.igauntlet.common.capability;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;


public interface IInfinityCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

    void setPosessing(boolean posessing);

    void setLastPos(BlockPos pos);

    BlockPos getLastPos();

    boolean isPosessing();

    void setPosessedEntity(Entity posessedEntity);

    Entity getPosessedEntity();

    void clearPosessing();

}
