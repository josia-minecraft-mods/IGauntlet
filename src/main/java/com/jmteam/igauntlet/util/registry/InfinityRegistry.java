package com.jmteam.igauntlet.util.registry;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.init.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InfinityRegistry {

    // Registers
    public static final DeferredRegister<Block> BLOCK_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, IGauntlet.MODID);
    public static final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, IGauntlet.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE_REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, IGauntlet.MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, IGauntlet.MODID);
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, IGauntlet.MODID);

    // Initialize check
    private static boolean initalized;

    // Intialization
    public static InfinityBlocks BLOCKS;
    public static InfinityItems ITEMS;
    public static InfinityTileEntities TILE_ENTITY_TYPES;
    public static InfinityEntities ENTITY_TYPES;
    public static InfinitySounds SOUNDS;

    public static void register(IEventBus bus) {
       if(!initalized) {

           // Initialize
           BLOCKS = new InfinityBlocks();
           ITEMS = new InfinityItems();
           TILE_ENTITY_TYPES = new InfinityTileEntities();
           ENTITY_TYPES = new InfinityEntities();
           SOUNDS = new InfinitySounds();

           // Register
           BLOCK_REGISTRY.register(bus);
           ITEM_REGISTRY.register(bus);
           ENTITY_TYPE_REGISTRY.register(bus);
           TILE_ENTITY_TYPE_REGISTRY.register(bus);
           SOUND_REGISTRY.register(bus);

           initalized = true;
       }
    }
}
