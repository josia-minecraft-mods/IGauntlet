package com.jmteam.igauntlet.util.registry;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.init.*;
import com.jmteam.igauntlet.common.item.InfinityItemBlock;
import com.jmteam.igauntlet.util.helpers.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InfinityRegistry {




    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, IGauntlet.MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IGauntlet.MODID);

    public static void register() {
       // InfinityBlocks.init(); // Either this or we initialize a with // new InfinityBlocks(); either way would work
        InfinityEntities.init();
        InfinitySounds.init();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        InfinityBlocks.BLOCKS.register(bus);
        InfinityItems.ITEMS.register(bus);
        ENTITY_TYPES.register(bus);
        InfinityTileEntities.TILE_ENTITY_TYPES.register(bus);
        SOUNDS.register(bus);
    }
}
