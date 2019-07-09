package com.jmteam.igauntlet.util.handlers.client;

import com.jmteam.igauntlet.client.render.RenderLaser;
import com.jmteam.igauntlet.client.render.RenderPortal;
import com.jmteam.igauntlet.client.render.RenderSquirrelGirl;
import com.jmteam.igauntlet.common.entity.EntityLaser;
import com.jmteam.igauntlet.common.entity.EntityPortal;
import com.jmteam.igauntlet.common.entity.EntitySquirrelGirl;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, RenderLaser::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPortal.class, RenderPortal::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySquirrelGirl.class, RenderSquirrelGirl::new);
    }
}
