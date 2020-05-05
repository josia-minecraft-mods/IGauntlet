package com.jmteam.igauntlet.proxy;

import com.jmteam.igauntlet.client.init.InfinityKeyBinds;
import com.jmteam.igauntlet.client.init.InfinityRenders;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        InfinityRenders.registerEntityRenders();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        InfinityKeyBinds.init();
    }
}
