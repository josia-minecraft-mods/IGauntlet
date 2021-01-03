package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.tileentity.TileEntityAshPile;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class InfinityTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, IGauntlet.MODID);

    public static final RegistryObject<TileEntityType<TileEntity>> ASH_PILE = addType("ash_pile", TileEntityAshPile::new, InfinityBlocks.ASH_PILE);
    public static final RegistryObject<TileEntityType<TileEntity>> QUICK_SAND = addType("quick_sand", TileEntityAshPile::new, InfinityBlocks.QUICK_SAND);
    public static final RegistryObject<TileEntityType<TileEntity>> TESSERACT = addType("tesseract", TileEntityAshPile::new, InfinityBlocks.TESSERACT);

    public static <T extends TileEntity> RegistryObject<TileEntityType<T>> addType(String name, Supplier<T> tileEntitySupplier, RegistryObject<? extends Block> block) {
        return TILE_ENTITY_TYPES.register(name, () -> TileEntityType.Builder.create(tileEntitySupplier, block.get()).build(null));
    }

}
