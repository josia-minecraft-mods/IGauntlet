package com.igauntlet.util.handlers;

import com.igauntlet.commands.DimensionTeleport;
import com.igauntlet.init.ModBiomes;
import com.igauntlet.init.ModBlocks;
import com.igauntlet.init.ModItems;
import com.igauntlet.util.handlers.helpers.IHasModel;
import com.igauntlet.init.ModDimensions;
import com.igauntlet.world.ModWorldGen;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
    }


    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }

        for (Block block : ModBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel) block).registerModels();
            }
        }
    }

    public static void preInitRegistries(FMLPreInitializationEvent event) {
        TileRegister.PreInit();
        ModDimensions.registerDimensions();
    }

    public static void initRegistries(FMLInitializationEvent event) {
        SoundsHandler.registerSounds();
    }


    public static void postInitRegistries(FMLPostInitializationEvent event) {

    }


    public static void OtherRegistries() {
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        ModBiomes.registerBiomes();
    }

    public static void serverRegistries(FMLServerStartingEvent event) {
        event.registerServerCommand(new DimensionTeleport());
    }


}

