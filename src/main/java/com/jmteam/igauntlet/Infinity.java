package com.jmteam.igauntlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jmteam.igauntlet.common.capability.CapInfinityStorage;
import com.jmteam.igauntlet.common.capability.CapabilityInfinity;
import com.jmteam.igauntlet.common.capability.IInfinityCap;
import com.jmteam.igauntlet.common.commands.CommandCreateSchematic;
import com.jmteam.igauntlet.common.commands.CommandDimensionTeleport;
import com.jmteam.igauntlet.common.commands.CommandPasteSchematic;
import com.jmteam.igauntlet.common.init.*;
import com.jmteam.igauntlet.common.world.gen.WorldGeneration;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.proxy.IProxy;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Infinity.MODID, name = Infinity.NAME, version = Infinity.VERSION, dependencies = Infinity.DEPENDENCY, updateJSON = Infinity.UPDATEURL)
public class Infinity {

    //TODO Rewrite the whole mod when the mod is fully released. Easier for debugging and stuff later

    @Mod.Instance
    public static Infinity instance;

    public static final String NAME = "IGauntlet Mod";
    public static final String VERSION = "4.0";
    public static final String MODID = "igauntlet";
    public static final String DEPENDENCY = "required-after:forge@[14.23.2.2638,)";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/josia-minecraft-mods/IGauntlet/master/update.json";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Logger log;

    public static boolean isDevEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    @SidedProxy(clientSide = "com.jmteam.igauntlet.proxy.ClientProxy", serverSide = "com.jmteam.igauntlet.proxy.CommonProxy")
    public static IProxy proxy;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        log = event.getModLog();
        InfinitySounds.registerSounds();
        GameRegistry.registerWorldGenerator(new WorldGeneration(), 3);
        InfinityBiomes.registerBiomes();
        NetworkHandler.init();
        InfinityTileEntities.PreInit();
        InfinityDimensions.registerDimensions();
        CapabilityManager.INSTANCE.register(IInfinityCap.class, new CapInfinityStorage(), CapabilityInfinity::new);
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event) {
        proxy.init(event);
        InfinityRecipes.init();
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        if(isDevEnv) {
            event.registerServerCommand(new CommandDimensionTeleport());
            event.registerServerCommand(new CommandCreateSchematic());
            event.registerServerCommand(new CommandPasteSchematic());
        }
    }
}

