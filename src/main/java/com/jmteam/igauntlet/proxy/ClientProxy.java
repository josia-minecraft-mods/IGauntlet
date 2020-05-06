package com.jmteam.igauntlet.proxy;

import com.jmteam.igauntlet.client.init.EntityRenderRegistry;
import com.jmteam.igauntlet.client.init.TileEntityRenderRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends ServerProxy {

    @Override
    public void doClientStuff(FMLClientSetupEvent event) {
        TileEntityRenderRegistry.init();
        EntityRenderRegistry.registryEntityRenders();
    }
}
