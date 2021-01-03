package com.jmteam.igauntlet.client.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.client.render.entity.RenderEntityGauntlet;
import com.jmteam.igauntlet.common.init.InfinityEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRenderRegistry {

    @SuppressWarnings("unchecked")
    public static void registryEntityRenders() {
        IGauntlet.LOGGER.info("Registering Entity Renders");

        registerRender(InfinityEntities.GAUNTLET.get(), RenderEntityGauntlet::new);
    }

    public static <T extends Entity> void registerRender(EntityType<T> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}
