package com;

import com.init.ModRecipes;
import com.network.NetworkHandler;
import com.proxy.IProxy;
import com.util.ModLog;
import com.util.Reference;
import com.util.handlers.RegistryHandler;
import com.world.ModWorldGen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, updateJSON = Reference.UPDATEURL)
public class Infinity {

    @Mod.Instance
    public static Infinity instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
    public static IProxy proxy;
    public static Logger logger;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        ModLog.Log("Pre-Init");
        proxy.preInit(event);
        GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
        NetworkHandler.init();
        RegistryHandler.preInitRegistries(event);
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModLog.Log("Init");
        ModRecipes.init();
        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {
        ModLog.Log("Post-Init");
    }
}

