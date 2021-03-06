package com.jmteam.igauntlet.common.init;

import com.jmteam.igauntlet.IGauntlet;
import com.jmteam.igauntlet.common.entity.EntityGauntlet;
import com.jmteam.igauntlet.util.registry.EntityBuilder;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfinityEntities {

    public static RegistryObject<EntityType<EntityGauntlet>> GAUNTLET = create("gauntlet").entity(EntityGauntlet::new, EntityClassification.MONSTER).size(1, 1).build();

    public static EntityBuilder create(String name) {
        EntityBuilder builder = EntityBuilder.create();
        builder.setName(name);
        builder.setId(new ResourceLocation(IGauntlet.MODID, name));
        return builder;
    }
}
