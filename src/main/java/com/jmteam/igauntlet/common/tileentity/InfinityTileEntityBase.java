package com.jmteam.igauntlet.common.tileentity;

import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import com.jmteam.igauntlet.util.helpers.ReflectionHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class InfinityTileEntityBase extends TileEntity {

    public InfinityTileEntityBase() {
        super(null);
        ReflectionHelper.setValuePrivateDeclaredField(TileEntity.class.getDeclaredFields()[1].getName(), ReflectionHelper.getClassFromSuperClasses(this, TileEntity.class), this, InfinityTileEntities.getTypeFromClass(getClass()));
    }
}
