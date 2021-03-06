package com.jmteam.igauntlet;

import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.capability.InfinityCapStorage;
import com.jmteam.igauntlet.common.events.ServerEvents;
import com.jmteam.igauntlet.common.init.*;
import com.jmteam.igauntlet.config.InfinityConfig;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.proxy.ClientProxy;
import com.jmteam.igauntlet.proxy.ServerProxy;
import com.jmteam.igauntlet.util.generation.OreGeneration;
import com.jmteam.igauntlet.util.registry.InfinityRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(IGauntlet.MODID)
public class IGauntlet {

    public static IGauntlet INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger();
    public static final ServerProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    public static final String MODID = "igauntlet";

    public IGauntlet() {
        INSTANCE = this;
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InfinityRegistry.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::doClientStuff);
        bothSideSetup(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ServerEvents());
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, InfinityConfig.CLIENT_SPEC);
    }

    private void bothSideSetup(IEventBus modEventBus) {

    }

    private void commonSetup(FMLCommonSetupEvent event) {
        PROXY.doServerStuff(event);
        NetworkHandler.register();
        OreGeneration.init();
        CapabilityManager.INSTANCE.register(IInfinityCap.class, new InfinityCapStorage(), CapabilityInfinity::new);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        PROXY.doClientStuff(event);
    }
}
