package com.jmteam.igauntlet.common.capability;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;


public interface IInfinityCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

    void setPosessing(boolean posessing);

    boolean isPosessing();

    void setPosessedEntity(Entity posessedEntity);

    Entity getPosessedEntity();

    void clearPosessing();

}
