package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.util.registry.EntityBuilder;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class InfinityEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, IGauntlet.MODID);

  //  public static RegistryObject<EntityType<EntityTest>> TEST;

    public static void init(IEventBus eventBus) {
        IGauntlet.LOGGER.info("Registering Entities");

       // TEST = create("test").entity(EntityTest::new, EntityClassification.MONSTER).size(0.8F, 1.8F).build();


        ENTITY_TYPES.register(eventBus);
    }

    public static EntityBuilder create(String name) {
        EntityBuilder builder = EntityBuilder.create();
        builder.setName(name);
        builder.setId(new ResourceLocation(IGauntlet.MODID, name));
        return builder;
    }
}
