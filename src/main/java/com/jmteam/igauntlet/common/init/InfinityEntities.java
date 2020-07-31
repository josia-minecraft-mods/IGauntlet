package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.util.registry.EntityBuilder;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfinityEntities {

    public static List<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

    public static EntityType<EntityGauntlet> GAUNTLET;

    public static void init() {

        EntityType<?>[] entityTypes = new EntityType[]{
                GAUNTLET = create("gauntlet").entity(EntityGauntlet::new, EntityClassification.MONSTER).size(1, 1).build()
        };

        ENTITY_TYPES.addAll(Arrays.asList(entityTypes));
    }

    public static EntityBuilder create(String name) {
        EntityBuilder builder = EntityBuilder.create();
        builder.setName(name);
        builder.setId(new ResourceLocation(IGauntlet.MODID, name));
        return builder;
    }
}
