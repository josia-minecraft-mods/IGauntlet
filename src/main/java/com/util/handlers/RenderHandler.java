package com.util.handlers;

import com.common.entity.EntityLaser;
import com.client.render.RenderLaser;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, RenderLaser::new);
    }
}
