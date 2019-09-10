package com.jmteam.igauntlet.client.init;

import com.jmteam.igauntlet.client.render.entity.RenderLaser;
import com.jmteam.igauntlet.client.render.entity.RenderPortal;
import com.jmteam.igauntlet.client.render.entity.RenderSquirrelGirl;
import com.jmteam.igauntlet.common.entity.EntityLaser;
import com.jmteam.igauntlet.common.entity.EntityPortal;
import com.jmteam.igauntlet.common.entity.EntitySquirrelGirl;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class InfinityRenders {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, RenderLaser::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityPortal.class, RenderPortal::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySquirrelGirl.class, RenderSquirrelGirl::new);
    }
}
