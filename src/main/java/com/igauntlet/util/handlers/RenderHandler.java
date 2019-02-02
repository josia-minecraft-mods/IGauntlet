package com.igauntlet.util.handlers;

import com.igauntlet.common.entity.EntityLaser;
import com.igauntlet.client.render.RenderLaser;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, RenderLaser::new);
    }
}
