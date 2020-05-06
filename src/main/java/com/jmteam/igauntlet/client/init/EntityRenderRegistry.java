package com.jmteam.igauntlet.client.init;

import com.jmteam.igauntlet.IGauntlet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRenderRegistry {

    @SuppressWarnings("unchecked")
    public static void registryEntityRenders() {
        IGauntlet.LOGGER.info("Registering Entity Renders");
        // registerRender(InfinityEntities.TEST, new RenderTest.RenderFactory());
    }

    public static <T extends Entity> void registerRender(RegistryObject<EntityType<T>> entityClass, IRenderFactory<? super T> renderFactory) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass.get(), renderFactory);
    }
}
