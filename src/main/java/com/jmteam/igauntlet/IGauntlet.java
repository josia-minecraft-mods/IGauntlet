package com.jmteam.igauntlet;

import com.jmteam.igauntlet.common.init.InfinityBlocks;
import com.jmteam.igauntlet.common.init.InfinityEntities;
import com.jmteam.igauntlet.common.init.InfinityItems;
import com.jmteam.igauntlet.common.init.InfinityTileEntities;
import com.jmteam.igauntlet.proxy.ClientProxy;
import com.jmteam.igauntlet.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(IGauntlet.MODID)
public class IGauntlet {

    public static IGauntlet INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ServerProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final String MODID = "igauntlet";

    public IGauntlet() {
        INSTANCE = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        bothSideSetup(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void bothSideSetup(IEventBus modEventBus) {
        InfinityEntities.init(modEventBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PROXY.doServerStuff(event);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void registerTypes(RegistryEvent.Register<TileEntityType<?>> event) {
            LOGGER.info("Registering TileEntities");

            for (Map.Entry<Class, TileEntityType> entityTypeEntry : InfinityTileEntities.TILE_ENTITY_TYPES.entrySet()) {
                event.getRegistry().register(entityTypeEntry.getValue());
            }
        }

        @SubscribeEvent
        public static void onBlocksRegistry(RegistryEvent.Register<Block> blockRegistryEvent) {
            LOGGER.info("Registering Blocks");
            blockRegistryEvent.getRegistry().registerAll(InfinityBlocks.BLOCKS.toArray(new Block[0]));
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            LOGGER.info("Registering Items");
            event.getRegistry().registerAll(InfinityItems.ITEMS.toArray(new Item[InfinityItems.ITEMS.size()]));
        }
    }
}