package com;

import com.proxy.IProxy;
import com.util.Reference;
import com.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
    public static IProxy proxy;
    public static SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
    public static Logger logger;

    @Mod.EventHandler
    public static void PreInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        RegistryHandler.preInitRegistries(event);
    }

    @Mod.EventHandler
    public static void Init(FMLInitializationEvent event) {
        RegistryHandler.initRegistries(event);
    }

    @Mod.EventHandler
    public static void PostInit(FMLPostInitializationEvent event) {

    }
}

