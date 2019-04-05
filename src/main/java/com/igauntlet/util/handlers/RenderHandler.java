package com.igauntlet.util.handlers;

import com.igauntlet.client.render.RenderLaser;
import com.igauntlet.client.render.RenderPortal;
import com.igauntlet.client.render.RenderSquirrelGirl;
import com.igauntlet.common.entity.EntityLaser;
import com.igauntlet.common.entity.EntityPortal;
import com.igauntlet.common.entity.EntitySquirrelGirl;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, RenderLaser::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPortal.class, RenderPortal::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySquirrelGirl.class, RenderSquirrelGirl::new);
    }
}
