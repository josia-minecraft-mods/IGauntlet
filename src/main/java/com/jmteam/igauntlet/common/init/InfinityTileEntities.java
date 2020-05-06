package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class InfinityTileEntities {

    public static Map<Class, TileEntityType> TILE_ENTITY_TYPES = new HashMap<>();

    public static <T extends TileEntity> TileEntityType<T> addType(String name, Supplier<TileEntity> tileEntitySupplier, Block... blocks) {
        TileEntityType.Builder<TileEntity> builder = TileEntityType.Builder.create(tileEntitySupplier, blocks);
        TileEntityType<TileEntity> tileEntityType = builder.build(null);
        tileEntityType.setRegistryName(IGauntlet.MODID, name);
        TILE_ENTITY_TYPES.put(tileEntitySupplier.get().getClass(), tileEntityType);

        return (TileEntityType<T>) tileEntityType;
    }

    // Getting a type from a TileEntity class
    public static <T extends TileEntity> TileEntityType<T> getTypeFromClass(Class clazz) {
        if(TILE_ENTITY_TYPES.containsKey(clazz)) {
            return  TILE_ENTITY_TYPES.get(clazz);
        }

        return null;
    }
}
