package com.jmteam.igauntlet.util.handlers;

import com.jmteam.igauntlet.Infinity;
import com.jmteam.igauntlet.common.commands.DimensionTeleport;
import com.jmteam.igauntlet.common.entity.EntityLaser;
import com.jmteam.igauntlet.common.entity.EntityPortal;
import com.jmteam.igauntlet.common.entity.EntitySquirrelGirl;
import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.world.InfinityBiomes;
import com.jmteam.igauntlet.world.InfinityDimensions;
import com.jmteam.igauntlet.world.WorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        InfinityItems.registerRenders();
        for (Block block : InfinityBlocks.BLOCKS) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
        }
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        TileRegister.PreInit();
        InfinityDimensions.registerDimensions();
    }

    public static void postInitRegistries(FMLPostInitializationEvent event) {
    }

    public static void OtherRegistries() {
        SoundsHandler.registerSounds();
        GameRegistry.registerWorldGenerator(new WorldGeneration(), 3);
        InfinityBiomes.registerBiomes();
    }

    public static void serverRegistries(FMLServerStartingEvent event) {
        event.registerServerCommand(new DimensionTeleport());
    }

    @SubscribeEvent
    public static void addEntities(RegistryEvent.Register<EntityEntry> e) {
        IForgeRegistry<EntityEntry> reg = e.getRegistry();
        reg.registerAll(EntityEntries.LASER, EntityEntries.PORTAL, EntityEntries.SQUIRREL_GIRL);
    }

    @GameRegistry.ObjectHolder(Infinity.MODID)
    public static class EntityEntries {
        public static final EntityEntry LASER = EntityEntryBuilder.create().entity(EntityLaser.class).id(new ResourceLocation(Infinity.MODID, "ray"), 0).name("ray").tracker(80, 3, true).build();
        public static final EntityEntry PORTAL = EntityEntryBuilder.create().entity(EntityPortal.class).id(new ResourceLocation(Infinity.MODID, "portal"), 1).name("portal").tracker(80, 3, false).build();
        public static final EntityEntry SQUIRREL_GIRL = EntityEntryBuilder.create().entity(EntitySquirrelGirl.class).id(new ResourceLocation(Infinity.MODID, "squirrelgirl"), 2).name("squirrel_girl").tracker(80, 3, true).build();
    }
}

