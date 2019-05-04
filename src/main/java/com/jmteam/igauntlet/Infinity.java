package com.jmteam.igauntlet;

import com.jmteam.igauntlet.util.InfinityRecipes;
import com.jmteam.igauntlet.network.NetworkHandler;
import com.jmteam.igauntlet.proxy.IProxy;
import com.jmteam.igauntlet.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Infinity.MODID, name = Infinity.NAME, version = Infinity.VERSION, updateJSON = Infinity.UPDATEURL)
public class Infinity {

    //TODO Rewrite the whole mod when the mod is fully released. Easier for debugging and stuff later

    @Mod.Instance
    public static Infinity instance;

    public static final String NAME = "IGauntlet Mod";
    public static final String VERSION = "3.5";
    public static final String MODID = "igauntlet";
    public static final String UPDATEURL = "https://raw.githubusercontent.com/josia-minecraft-mods/IGauntlet/master/update.json";

    @SidedProxy(clientSide = "com.jmteam.igauntlet.proxy.ClientProxy", serverSide = "com.jmteam.igauntlet.proxy.CommonProxy")
    public static IProxy proxy;
    public static Logger logger;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        RegistryHandler.OtherRegistries();
        NetworkHandler.init();
        RegistryHandler.preInitRegistries(event);
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event) {
        InfinityRecipes.init();
        proxy.init(event);
        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }
}

