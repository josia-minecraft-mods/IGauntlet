package com.jmteam.igauntlet.proxy;

import com.jmteam.igauntlet.client.util.ModKeyBinds;
import com.jmteam.igauntlet.util.handlers.RenderHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderHandler.registerEntityRenders();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        ModKeyBinds.init();
    }
}
